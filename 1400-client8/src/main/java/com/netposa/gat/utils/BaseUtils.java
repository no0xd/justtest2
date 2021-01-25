package com.netposa.gat.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author netposa
 *
 */
@Slf4j
public class BaseUtils {
	public static final String ORGANIZATIONCODE = "411201000000";

	public static final String SUBTYPECODE = "04";

	public static void threadSleep(int value){
		try{
			Thread.sleep(value);
		} catch (Exception e) { 

		} 
	}

	/**
	 * 生成通知唯一标识
	 * @return
	 */
	public static String getNotificationID(){
		//机构编码	（1-12 公安机关机构代码） + 子类型编码（13-14	表示类型）+ 时间编码（15-28） + 序号	29-33
		String TriggerTime = DateUtils.Convert2String(new Date(),DateUtils.DateFormatNoSpan);
		String serialNumber = getRandom();
		String notificationID =  String.format("%s%s%s%s", ORGANIZATIONCODE,SUBTYPECODE,TriggerTime,serialNumber);
		return notificationID;
	}

	public static String getRandom(){
		double value = Math.random()*99999;
		return String.format("%05d", (int)value);
	}

	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

	/**
	 * 根据imageUrl获取base64图片数据
	 * @param imageUrl
	 * @param deviceId
	 * @return
	 */
	public static String getBase64Data(String imageUrl){
		log.info("KafkaStrucConverter : imageConvert >>　vehicle imageUrl is " + imageUrl);
		byte[] imageInfo = null;
		try {
			imageInfo= createImage(imageUrl);
		} catch (Exception e) {
			log.info("KafkaStrucConverter : imageConvert >>vehicle imageInfo download error ,exception :{}",e);
		}
		if(imageInfo == null){
			log.warn("KafkaStrucConverter : imageConvert >>vehicle imageInfo is null ");
			return null;
		}
		log.info("download image success !");
		String base64Img = Base64.encodeBase64String(imageInfo);
		return base64Img;
	}

	private static  byte[] createImage(String imgurl) {
		byte[] bytes = null;
		try {
			URL url = new URL(imgurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(1000);  
			conn.setConnectTimeout(1000);  
			InputStream inputStream = conn.getInputStream(); // 通过输入流获得图片数据
			bytes = readInputStream(inputStream); // 获得图片的二进制数据
		} catch (Exception e) {
			log.error("Failed to download image : {} , exceprion : {}" , imgurl, e);
		}
		return bytes;
	}

	private static  byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		buffer = null;
		bos.close();
		return bos.toByteArray();
	}
}
