package com.netposa.gat.utils.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class APEsUtil {
	public static String testRequest() {

		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = null;
		String body = null;
		// 创建一个httpclient对象

		try {
			httpClient = HttpClients.createDefault();

			// 创建一个GET对象

			// String urlStr=new PropUtils().getValue("url");
			String urlStr = "http://11.21.88.32:19101/VIID/APEs";
			System.out.println(urlStr);
			HttpGet get = new HttpGet(urlStr);
			// HttpPost post= new HttpPost(urlStr);

			get.setHeader(HttpHeaders.CONTENT_TYPE, "application/VIID+JSON");
			// post.setHeader(HttpHeaders.CONTENT_TYPE,"application/json");

			// 执行请求

			response = httpClient.execute(get);
			// CloseableHttpResponse response = httpClient.execute(post);

			// 取响应的结果

			int statusCode = response.getStatusLine().getStatusCode();
			log.info("request APEs statusCode:" + statusCode);

			HttpEntity entity = response.getEntity();

			body = EntityUtils.toString(entity, "utf-8");

			log.info("request APEs body:" + body);
		} catch (Exception e) {
			log.info("request APEs exception" + e.getMessage());
		} finally {
			// 关闭httpclient
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return body;
	}
}
