package com.netposa.gat.utils.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.model.response.ResponseStatus;
import com.netposa.gat.model.response.ResponseStatusObj;
import com.netposa.gat.model.response.ResponseStatusObj.ResponseStatusObject;
import com.netposa.gat.model.response.ResponseStatusStruc;
import com.netposa.gat.utils.GatConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HttpUtil {

	@Autowired
	public static Gson gson;
	static {
		 gson = new Gson();
	}

	public static  String getResponseCode(ResponseEntity<String> responseStruc){
		String responseBody = responseStruc.getBody();
		ResponseStatusObj responseStatus  = gson.fromJson(responseBody, ResponseStatusObj.class);
		String code = responseStatus.getResponseStatusObject().getStatusCode();
		return code;
	}
	public static HttpHeaders getHttpHeaders(RegisterContext registerContext){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(GatConstant.USERIDENTIFY,registerContext.getDeviceID());
		httpHeaders.add(HttpHeaders.CONTENT_TYPE,GatConstant.CONTENTTYPE);
		return httpHeaders;
	}
	
	public static String analyzeRegResponseContent(String responseStr){
		log.info("analyeRegRes responseStr"+responseStr);
		ResponseStatusObj reponseObj = gson.fromJson(responseStr, ResponseStatusObj.class);
		ResponseStatusObject responseStatusObject = reponseObj.getResponseStatusObject();
		String statusCode = responseStatusObject.getStatusCode();
		return statusCode;
	}
	
	public static String analyzeResponseContent(String responseStr){
		log.info("responseStr"+responseStr);
		ResponseStatusObj reponseObj = gson.fromJson(responseStr, ResponseStatusObj.class);
		if(ObjectUtils.isEmpty(reponseObj)||StringUtils.isEmpty(reponseObj.getResponseStatusObject())){
			ResponseStatusStruc responseStruc = gson.fromJson(responseStr, ResponseStatusStruc.class);
			if(ObjectUtils.isEmpty(responseStruc)||ObjectUtils.isEmpty(responseStruc.getResponseStatusListObject())){
				ResponseStatus reponseStatus = gson.fromJson(responseStr, ResponseStatus.class);
				return reponseStatus.getStatusCode();
			}
			return responseStruc.getResponseStatusListObject().getResponseStatusObject().get(0).getStatusCode();
		}
		return reponseObj.getResponseStatusObject().getStatusCode();
	}
	
	public static void closeHttpClient(CloseableHttpClient client){
		try {
			client.close();
		} catch (Exception e) {
			
		}
	}

	@Bean
	public static  RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
