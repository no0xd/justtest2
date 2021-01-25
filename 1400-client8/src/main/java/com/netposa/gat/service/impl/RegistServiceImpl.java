package com.netposa.gat.service.impl;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Timer;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.netposa.gat.mapper.RegisterMapper;
import com.netposa.gat.model.regist.Register;
import com.netposa.gat.model.regist.Register.RegisterObject;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.service.IRegistService;
import com.netposa.gat.utils.GatConstant;
import com.netposa.gat.utils.HeaderUtils;
import com.netposa.gat.utils.MD5Utils;
import com.netposa.gat.utils.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistServiceImpl implements IRegistService {

	@Autowired
	RegisterMapper bootMapper;

	@Resource
	RestTemplateBuilder builder;

	
	@Autowired
	private Gson gson=new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	@Autowired
    HeaderUtils headerUtils;
	
	static RegisterMapper mapper;

	@PostConstruct
	private void init(){
		mapper = bootMapper;
	}
	
	public void batchRegist(List<RegisterContext> registContexts){
		for (RegisterContext registerContext : registContexts) {
			regist(registerContext);
		}
	}
	
	@Override
	public void batchKeepAlive(List<RegisterContext> registContexts) {
		for (RegisterContext registerContext : registContexts) {
			keepAlive(registerContext);
		}
	}
	
	@Override
	public boolean isRegist(RegisterContext registerObj){
		String isRegist = mapper.isRegist(registerObj.getIP());
		return "R".equals(isRegist) ? true : false;
	}
	
	/**
	 * 30s保活一次
	 */
	@Override
	public void keepAlive(RegisterContext registcontext) {
		Timer timer = new Timer(); 
		try {
			KeepAliveTaskImpl task = new KeepAliveTaskImpl();
			task.setRegistContext(registcontext);
			timer.schedule(task,0,registcontext.getKeepAliveTime()); 
		} catch (Exception e) {
			log.info("KeepAlive failed , ip : {} , port : {}, exception : {}",registcontext.getIP(),registcontext.getPort(),e);
		}
	}
	@Override
	public boolean regist(RegisterContext registerContext) {
		String nonce = null ,qop = null,realm = null;
		String opaque=null;
		CloseableHttpClient client = null;
		try {
			HttpClientBuilder clieantBuilder = HttpClients.custom();
			client = clieantBuilder.build();
			HttpPost post = new HttpPost();
			post.setURI(new URI("http://"+registerContext.getIP()+":"+registerContext.getPort()+GatConstant.REGISTURL));

			//第一次请求
			CloseableHttpResponse response = postUrlFirst(client, post,null,registerContext);
			if(null==response){
				log.error("the first regist response is null !");
				return false;
			}
			log.info("返回的状态是："+response.getStatusLine().getStatusCode());
			//判断设备是否在线，200-在线，直接发送数据；401-没在线，注册
			if (response.getStatusLine().getStatusCode()==200) {
				return true;
			}
			Header[] headers = response.getHeaders(GatConstant.AUTHORIZATION);
			String authorization = headers[0].getValue();
			log.info("第一次返回的header :"+authorization);
			
			//获取返回值
			JsonObject jsonObject = headerUtils.parseHeader(authorization);
			if (jsonObject != null) {
				nonce = jsonObject.get("nonce").getAsString();
				qop = jsonObject.get("qop").getAsString();
				realm = jsonObject.get("realm").getAsString();
				if(jsonObject.get("opaque")!=null)
				opaque=jsonObject.get("opaque").getAsString();
			}
			nonce = nonce.replaceAll(":", "=");
			String[] responseAguments = new String[4];
			responseAguments[0]=nonce;
			responseAguments[1]=qop;
			responseAguments[2]=realm;
			if(opaque != null)  //常宁与韬安对接有此字段，海康等无字段
			responseAguments[3]=opaque;
				
			//第二次请求
			CloseableHttpResponse responseTwo = postUrlSecond(client,post,responseAguments,registerContext);
			if(responseTwo==null){
				log.error("the second regist response is null !");
				return false;
			}
			
			HttpEntity responseEntity = responseTwo.getEntity();
			String content = EntityUtils.toString(responseEntity);
			log.info("第二次请求返回的的content :"+content);
			if("0".equals(HttpUtil.analyzeRegResponseContent(content))){
				log.info("regist server success !");
				registerContext.setStatus("R");
				return true;
			}
		} catch (Exception e) {
			log.error("regist server exception : ",e);
			return false;
		}finally{
			if(client!=null){
				HttpUtil.closeHttpClient(client);
			}
		}
		return  false;
	}


	/**
	 * 发送第一次请求
	 * @param client
	 * @param post
	 * @param nonce
	 * @param registerObj
	 * @return
	 */
	private CloseableHttpResponse postUrlFirst(CloseableHttpClient client, HttpPost post,String nonce,RegisterContext registerObj){
		CloseableHttpResponse response = null;
		try {
			post.setHeader(GatConstant.USERIDENTIFY,registerObj.getDeviceID());
			post.setHeader(HttpHeaders.CONTENT_TYPE,GatConstant.CONTENTTYPE);
			Register register = new Register();
			RegisterObject obj = new RegisterObject();
			obj.setDeviceID(registerObj.getDeviceID());
			register.setRegisterObject(obj);
			String data = gson.toJson(register);
			StringEntity entity = new StringEntity(data, Charset.forName("UTF-8"));
			entity.setContentEncoding("UTF-8");
			post.setEntity(entity);
			response = client.execute(post);
		} catch (Exception e) {
			log.error("the first send  request failed !",e);
		}
		return response;
	}
	/**
	 * 发送第二次请求
	 * @param client
	 * @param post
	 * @param nonce
	 * @param registerObj
	 * @return
	 */
	private CloseableHttpResponse postUrlSecond(CloseableHttpClient client, HttpPost post,String[] nonce,RegisterContext registerObj) throws Exception {
		CloseableHttpResponse responseBody = null;
		try {
			//计算response
			String HA1=MD5Utils.authHA1(registerObj.getUserName(), nonce[2],registerObj.getPassword());
			log.info("HA1 :"+HA1);
			String HA2 = MD5Utils.authHA2("POST",GatConstant.REGISTURL);
			log.info("HA2 :"+HA2);
			String nonceCount = "00000001";
			String clientNonce = MD5Utils.randomNonce();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(HA1);
			stringBuffer.append(":");
			stringBuffer.append(nonce[0]);
			stringBuffer.append(":");
			stringBuffer.append(nonceCount);
			stringBuffer.append(":");
			stringBuffer.append(clientNonce);
			stringBuffer.append(":");
			stringBuffer.append(nonce[1]);
			stringBuffer.append(":");
			stringBuffer.append(HA2);
			log.debug("加密前的responseString : {}",stringBuffer.toString());
			String response = DigestUtils.md5Hex(stringBuffer.toString());
			log.debug("加密后的response : {}",response);

			//构建返回的认证头
			StringBuffer sb = new StringBuffer();
			sb.append("Digest ");
			sb.append("username=\""+registerObj.getUserName()+"\", ");
			sb.append("realm=\""+nonce[2]+"\", ");
			sb.append("nonce=\"" +nonce[0] + "\", ");
			sb.append("uri=\""+GatConstant.REGISTURL+"\", ");
			sb.append("response=\"" + response + "\", ");
			sb.append("algorithm=\"MD5\", ");
			sb.append("qop=\""+nonce[1]+"\", ");
			//hik要求去掉""
			sb.append("nc="+nonceCount+", ");
			if(nonce[3]!=null)	//韬安要求有此字段，海康等厂家无此字段
			sb.append("opaque=\""+nonce[3]+"\",");//新增
			sb.append("cnonce=\"" + clientNonce + "\"");
			
			log.info("第二次请求header : {}",sb.toString());
			post.setHeader(HttpHeaders.CONTENT_TYPE,GatConstant.CONTENTTYPE);
			post.setHeader(GatConstant.USERIDENTIFY,registerObj.getDeviceID());
			post.setHeader(HttpHeaders.AUTHORIZATION, sb.toString());

			Register register = new Register();
			RegisterObject obj = new RegisterObject();
			obj.setDeviceID(registerObj.getDeviceID());
			register.setRegisterObject(obj);
			String data = gson.toJson(register);
			StringEntity entity = new StringEntity(data, Charset.forName("UTF-8"));
			entity.setContentEncoding("UTF-8");

			// 发送Json格式的数据请求
			post.setEntity(entity);
			responseBody = client.execute(post);
		} catch (Exception e) {
			log.error("the second send request failed !");
		}
		return responseBody;
	}
}
