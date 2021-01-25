package com.netposa.gat.utils.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.netposa.gat.model.response.ResponseStatusObj;
import com.netposa.gat.model.response.ResponseStatusObj.ResponseStatusObject;


@Service
public class HttpResUtil {
	
	@Autowired
	public static Gson gson;
	
	public static String getStatus(String data, Gson gson) {
		ResponseStatusObj reponseObj = gson.fromJson(data, ResponseStatusObj.class);
		ResponseStatusObject responseStatusObject = reponseObj.getResponseStatusObject();
		String statusCode = responseStatusObject.getStatusCode();
		return statusCode;
	}
	
}
