package com.netposa.gat.service.impl;

import java.net.URI;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.netposa.gat.model.regist.Keepalive;
import com.netposa.gat.model.regist.Keepalive.KeepaliveObject;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.utils.GatConstant;
import com.netposa.gat.utils.http.HttpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;



@Data
@Service
@Slf4j
public class KeepAliveTaskImpl extends TimerTask  {
	
	private RegisterContext registContext;

	@Autowired
	private Gson gsonBoot;
	
	static Gson gson ;
	
	@PostConstruct
	private void init(){
		gson = gsonBoot;
	}
	@Override
	public void run() {
		//获取连接
		CloseableHttpClient client = null;
		try {
			HttpClientBuilder builder = HttpClients.custom();
			client = builder.build();
			HttpPost post=new HttpPost();
			post.setURI(new URI("http://"+registContext.getIP()+":"+registContext.getPort()+GatConstant.KEEPALIVE));
			//请求保活
			CloseableHttpResponse response = postUrlKeepalive(client, post,null);
			if(response==null){
				log.error("the  keepAlive response is null !");
				return ;
			}
			org.apache.http.HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			if("0".equals(HttpUtil.analyzeRegResponseContent(content))){
				log.info("======== Keepalive message send success ! ========");
			}
		} catch (Exception e) {
			log.info("send keepalive message exception :{}",e);
		}finally{
			if(client!=null){
				HttpUtil.closeHttpClient(client);
			}
		}
	}

	private CloseableHttpResponse postUrlKeepalive(CloseableHttpClient client, HttpPost post,String nonce) {
		CloseableHttpResponse response = null;
		try {
			post.setHeader(HttpHeaders.CONTENT_TYPE,GatConstant.CONTENTTYPE);
			post.setHeader(GatConstant.USERIDENTIFY,registContext.getDeviceID());
			Keepalive keepalive = new Keepalive();
			KeepaliveObject obj = new KeepaliveObject();
			obj.setDeviceID(registContext.getDeviceID());
			keepalive.setKeepaliveObject(obj);
			String body = gson.toJson(keepalive);
			log.info("======== send keepalive body :  "+body+"  ========");
			post.setEntity(new StringEntity(body));
			response = client.execute(post);
		} catch (Exception e) {
			log.error("the  send keepAlive  request failed ! exception :{}",e);
		}
		return response;
	}
}
