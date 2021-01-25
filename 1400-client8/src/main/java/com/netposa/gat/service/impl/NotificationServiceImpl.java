package com.netposa.gat.service.impl;

import java.net.URI;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netposa.gat.component.CacheService;
import com.netposa.gat.model.face.Face;
import com.netposa.gat.model.face.FaceStruc;
import com.netposa.gat.model.motor.MotorVehicleStruc;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc;
import com.netposa.gat.model.notification.SubscribeNotification;
import com.netposa.gat.model.notification.SubscribeNotificationStruc;
import com.netposa.gat.model.person.PersonStruc;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.model.response.ResponseStatus;
import com.netposa.gat.model.response.ResponseStatusObj;
import com.netposa.gat.model.response.ResponseStatusStruc;
import com.netposa.gat.model.subscribe.Subscribe;
import com.netposa.gat.service.INotificationService;
import com.netposa.gat.service.IRegistService;
import com.netposa.gat.utils.GatConstant;
import com.netposa.gat.utils.SubscribeDetailEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "notificationServiceImpl")
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	Gson gson;

	@Resource(name = "registServiceImpl")
	private IRegistService registServiceImpl;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public boolean NotificationFaceData(FaceStruc faceStruc,Subscribe subscribe){
		//封装通知信息
		try {
			SubscribeNotification notification = new SubscribeNotification();
			notification.setNotificationID(subscribe.getSubscribeID());
			notification.setSubscribeID(subscribe.getSubscribeID());
			notification.setTitle(subscribe.getTitle());
			notification.setTriggerTime(format.format(new Date()));
			notification.setInfoIDs(SubscribeDetailEnum.FaceInfo.getCode());
			notification.setFaceObjectList(faceStruc.getFaceListObject());
			//推送通知信息
			String responseString = sendNotificationData(notification, subscribe);
			log.info("----------- notify faceData :--------------------------------------------------------"+gson.toJson(responseString));
			if("0".equals(analyze(responseString))){
				log.info("FaceConsumer : notifFace >> the face data send success !");
				return true;
			}else{
				log.info("notify faceData failed !");
				return false;
			}
		} catch (Exception e) {
			log.info("notify faceData failed !,exception : {}",e);
			return false;
		} 
	}
	public boolean NotificationFaceData1(FaceStruc faceStruc,Subscribe subscribe){
		try {
			List<Face> faces = faceStruc.getFaceListObject().getFaceObject();

			for (Face face : faces) {
				List<Face> facess = new ArrayList<Face>();
				facess.add(face);
				FaceStruc.FaceObject faceObj = new FaceStruc.FaceObject();
				faceObj.setFaceObject(facess);
				FaceStruc faceStrucO = new FaceStruc();
				faceStrucO.setFaceListObject(faceObj);
				SubscribeNotification notification = new SubscribeNotification();
				notification.setNotificationID(subscribe.getSubscribeID());
				notification.setSubscribeID(subscribe.getSubscribeID());
				notification.setTitle(subscribe.getTitle());
				notification.setTriggerTime(format.format(new Date()));
				notification.setInfoIDs(SubscribeDetailEnum.FaceInfo.getCode());
				notification.setFaceObjectList(faceStrucO.getFaceListObject());
				String responseString = sendNotificationData(notification, subscribe);
				log.debug("----------- notify faceData :--------------------------------------------------------"+gson.toJson(responseString));
				if("0".equals(analyze(responseString))){
					log.info("FaceConsumer : notifFace >> the face data send success !");
				}else{
					log.info("notify faceData failed !");
				}
			}
		} catch (Exception e) {
			log.info("notify faceData failed !,exception : {}",e);
			return false;
		} 
		return true;
	}

	public boolean NotificationPersonData(PersonStruc personStruc,Subscribe subscribe){
		//封装通知信息
		try {
			SubscribeNotification notification = new SubscribeNotification();
			notification.setNotificationID(subscribe.getSubscribeID());
			notification.setSubscribeID(subscribe.getSubscribeID());
			notification.setTitle(subscribe.getTitle());
			notification.setTriggerTime(format.format(new Date()));
			notification.setInfoIDs(SubscribeDetailEnum.BodyInfo.getCode());
			notification.setPersonObjectList(personStruc.getPersonListObject());
			//推送通知信息
			String responseString = sendNotificationData(notification, subscribe);
			log.debug("----------- notify personData :--------------------------------------------------------"+gson.toJson(responseString));
			if("0".equals(analyze(responseString))){
				log.info("PsersonConsumer : notifPserson >> the face data send success !");
				return true;
			}else{
				log.info("notify PsersonData failed !");
				return false;
			}
		} catch (Exception e) {
			log.info("notify personData failed !,exception : {}",e);
			return false;
		} 
	}

	public boolean NotificationVehicleData(MotorVehicleStruc vehicleStruc,Subscribe subscribe){
		//封装通知信息
		try {
			SubscribeNotification notification = new SubscribeNotification();
			notification.setNotificationID(subscribe.getSubscribeID());
			notification.setSubscribeID(subscribe.getSubscribeID());
			notification.setTitle(subscribe.getTitle());
			notification.setTriggerTime(format.format(new Date()));
			notification.setInfoIDs(SubscribeDetailEnum.VehicleInfo.getCode());
			notification.setMotorVehicleObjectList(vehicleStruc.getMotorVehicleListObject());
			//推送通知信息
			String responseString = sendNotificationData(notification, subscribe);
			log.debug("----------- notify vehicleData :--------------------------------------------------------"+gson.toJson(responseString));
			if("0".equals(analyze(responseString))){
				log.info("VehicleConsumer : notifvehicle >> the face data send success !");
				return true;
			}else{
				log.info("notify vehicleData failed !");
				return false;
			}
		} catch (Exception e) {
			log.info("notify vehicleData failed ! exception : {}",e);
			return false;
		} 
	}

	public boolean NotificationNomotorData(NonMotorVehicleStruc noMotorStruc,Subscribe subscribe){
		//封装通知信息
		SubscribeNotification notification = new SubscribeNotification();
		notification.setNotificationID(subscribe.getSubscribeID());
		notification.setSubscribeID(subscribe.getSubscribeID());
		notification.setTitle(subscribe.getTitle());
		notification.setTriggerTime(format.format(new Date()));
		notification.setInfoIDs(SubscribeDetailEnum.NonMotorInfo.getCode());
		notification.setNonMotorVehicleObjectList(noMotorStruc.getNonMotorVehicleListObject());
		//推送通知信息
		try {
			String responseString = sendNotificationData(notification, subscribe);
			log.debug("----------- notify fnomotorData :--------------------------------------------------------"+gson.toJson(responseString));
			if("0".equals(analyze(responseString))){
				log.info("noMotorConsumer : notifynoMotor >> the face data send success !");
				return true;
			}else{
				log.info("notify vnoMotorData failed !");
				return false;
			}
		} catch (Exception e) {
			log.info("notify nomotorData failed !,exception : {}",e);
			return false;
		} 
	}

	/**
	 * 发送通知消息给订阅者
	 * @param restTemplate
	 * @param notification
	 * @param subscribe
	 * @return
	 */
	public String sendNotificationData(SubscribeNotification notification, Subscribe subscribe) throws Exception{
		List<SubscribeNotification> notificationList = new ArrayList<>();
		notificationList.add(notification);
		SubscribeNotificationStruc.SubscribeNotificationObject SubscribeNotificationListObject = new SubscribeNotificationStruc.SubscribeNotificationObject();
		SubscribeNotificationListObject.setSubscribeNotificationObject(notificationList);
		SubscribeNotificationStruc subscribeNotificationStruc = new SubscribeNotificationStruc();
		subscribeNotificationStruc.setSubscribeNotificationListObject(SubscribeNotificationListObject);

		//推送通知信息
		String sendUrl = subscribe.getReceiveAddr();
		log.info("sendUrl >> " + sendUrl);
		CloseableHttpClient client = null;
		HttpClientBuilder clieantBuilder = HttpClients.custom();
		client = clieantBuilder.build();
		HttpPost post = new HttpPost();
		post.setURI(new URI(sendUrl));
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		String data = gson.toJson(subscribeNotificationStruc);
		CloseableHttpResponse response = postUrlRequest(client,post,data);
		org.apache.http.HttpEntity responseEntity = response.getEntity();
		String content = EntityUtils.toString(responseEntity);
		return content;
	}
	private  CloseableHttpResponse postUrlRequest(CloseableHttpClient client, HttpPost post,String data) throws  Exception{
		RegisterContext context = CacheService.getInstance().getRegisterContext();
		post.setHeader(org.apache.http.HttpHeaders.CONTENT_TYPE,GatConstant.CONTENTTYPE);
		post.setHeader(GatConstant.USERIDENTIFY, context.getDeviceID());

		log.debug("Header deviceId is ->{}", context.getDeviceID());
		log.debug("Send notify Data is ->{}",data);
		StringEntity entity = new StringEntity(data, Charset.forName("UTF-8"));
		entity.setContentEncoding("UTF-8");
		// 发送Json格式的数据请求
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		return response;
	}

	private String analyze(String responseStr){
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
	
}
