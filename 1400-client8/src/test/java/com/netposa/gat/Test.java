package com.netposa.gat;

import com.google.gson.Gson;
import com.netposa.gat.model.response.ResponseStatusObj;
import com.netposa.gat.model.response.ResponseStatusObj.ResponseStatusObject;
import com.netposa.gat.utils.FileUtil;
import com.netposa.gat.utils.http.HttpResUtil;
import com.netposa.gat.utils.http.HttpUtil;


public class Test {
	public static void main(String[] args) {
		String json = "D:\\test\\res.json.txt";
		String data = FileUtil.readJsonFile(json);
//		System.out.println("data :"+data);
		Gson gson = new Gson();
//		JsonObject  jsonObject = gson.fromJson(data, JsonObject.class);
////		JsonElement element = jsonObject.get("registContextList");
//		 JsonArray jsonArray = jsonObject.getAsJsonArray("registContextList");
////		ArrayList<RegisterContext> context = gson.fromJson(element.toString(), ArrayList.class);
//		
//	        List<RegisterContext> list = new ArrayList<RegisterContext>();
//	        for (JsonElement jsonElement : jsonArray) {
//	        	RegisterContext t = gson.fromJson(jsonElement,RegisterContext.class); // TypeToken
//	            list.add(t);
//	        }
//		for (RegisterContext registerContext : list) {
//			System.out.println(registerContext);
//			
//		}
//		FaceObject obj = gson.fromJson(data, FaceObject.class);
//		IodFaceStruc iodFace = gson.fromJson(data, IodFaceStruc.class);
//		IodFaceStruc iodFace = JSON.parseObject(data, IodFaceStruc.class);
//		Face face = gson.fromJson(data, Face.class);
//		System.out.println(gson.toJson(iodFace));
		
		//String data={"ResponseStatusObject":{"Id":"11010600125031000005","RequestURL":"/VIID/System/Register","StatusCode":"0","StatusString":"OK","LocalTime":"20180818113709"}};
//		System.out.println(data);
//		getStatus(data, gson);
//		
//		String status = HttpResUtil.getStatus(data,gson);
//		System.out.println(status);
		
		String analyzeRegResponseContent = HttpUtil.analyzeRegResponseContent(data);
		System.out.println(analyzeRegResponseContent);
	}

	private static void getStatus(String data, Gson gson) {
		ResponseStatusObj reponseObj = gson.fromJson(data, ResponseStatusObj.class);
		ResponseStatusObject responseStatusObject = reponseObj.getResponseStatusObject();
		String statusCode = responseStatusObject.getStatusCode();
		System.out.println(statusCode);
	}
}
