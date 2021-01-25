package com.netposa.gat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netposa.gat.component.CacheService;
import com.netposa.gat.model.face.Face;
import com.netposa.gat.model.face.FaceStruc;
import com.netposa.gat.model.face.FaceStruc.FaceObject;
import com.netposa.gat.model.motor.MotorVehicle;
import com.netposa.gat.model.motor.MotorVehicleStruc;
import com.netposa.gat.model.motor.MotorVehicleStruc.MotorVehicleObject;
import com.netposa.gat.model.nomotor.NonMotorVehicle;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc.NonMotorVehicleObject;
import com.netposa.gat.model.person.Person;
import com.netposa.gat.model.person.PersonStruc;
import com.netposa.gat.model.person.PersonStruc.PersonObject;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.model.response.ResponseStatus;
import com.netposa.gat.model.response.ResponseStatusObj;
import com.netposa.gat.model.response.ResponseStatusStruc;
import com.netposa.gat.service.IPushDataService;
import com.netposa.gat.service.IRegistService;
import com.netposa.gat.utils.GatConstant;
import com.netposa.gat.utils.http.HttpUtil;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class PushDataServiceImpl implements IPushDataService {

	@Resource
	RestTemplateBuilder builder;

	@Resource(name = "registServiceImpl")
	private IRegistService registServiceImpl;
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	@Override
	public boolean pushFaceData(List<FaceStruc> faceStrucs,RegisterContext registContext) {
		try {
			List<Face> faceTotal = new ArrayList<Face>();
			for (FaceStruc faceStruc : faceStrucs) {
				List<Face> faces = faceStruc.getFaceListObject().getFaceObject();
				//为shotTime赋值
				for(int i=0;i<faces.size();i++){
					faces.get(i).setShotTime(faces.get(i).getFaceAppearTime());
				}
				log.info("face========已赋值shotTime======  ");
				faceTotal.addAll(faces);
			}
			FaceStruc struc = new FaceStruc();
			FaceObject faceObj = new FaceObject();
			faceObj.setFaceObject(faceTotal);
			struc.setFaceListObject(faceObj);
			
			String url = String.format("http://%s:%s%s", registContext.getIP(),registContext.getPort(),GatConstant.FACESTURL);
			log.info("======开始发送数据===== : url : {}",url);
			log.debug("======开始发送数据===== : {}",gson.toJson(struc));

			HttpEntity<String> httpEntity;
			ResponseEntity<String> responseEntity;
			try {
				httpEntity = new HttpEntity<>(gson.toJson(struc),HttpUtil.getHttpHeaders(registContext));
				responseEntity = HttpUtil.getRestTemplate(builder).postForEntity(url,httpEntity,String.class);
				log.info("收到的回复 ："+responseEntity.getBody());
				if("0".equals(analyze(responseEntity.getBody()))){
					log.info("FaceConsumer : pushFace >> the face data send success !");
					return true;
				}
			} catch (Exception e) {
				log.error("FaceConsumer : pushFace >> the face data send failed, url : {} , exception : {}",url,e);
				if (e.getMessage().contains("401")) {
					RegisterContext registerContext = CacheService.getInstance().getRegisterContext();
					registServiceImpl.regist(registerContext);
				}
			}
		} catch (Exception e) {
			log.error("FaceConsumer : pushFace >> the face data send  exception : ",e);
		}finally{
			faceStrucs.clear();
			faceStrucs = null;
		}
		return false;
	}

	@Override
	public boolean pushPersonData(List<PersonStruc> personStrucs,RegisterContext registContext) {
		try {
			List<Person> personTotal = new ArrayList<Person>();
			for (PersonStruc personStruc : personStrucs) {
				List<Person> persons = personStruc.getPersonListObject().getPersonObject();
				//为shotTime赋值
				for(int i=0;i<persons.size();i++){
					persons.get(i).setShotTime(persons.get(i).getPersonAppearTime());
				}
				log.info("person========已赋值shotTime====== : ");
				personTotal.addAll(persons);
			}
			PersonStruc struc = new PersonStruc();
			PersonObject personObj = new PersonObject();
			personObj.setPersonObject(personTotal);
			struc.setPersonListObject(personObj);
			
			String url = String.format("http://%s:%s%s", registContext.getIP(),registContext.getPort(),GatConstant.PERSONSTURL);
			log.info("========推送url====== : {}",url);
			log.debug("======开始发送数据===== : {}",gson.toJson(struc));
			HttpEntity<String> httpEntity;
			ResponseEntity<String> responseEntity;
			try {
				httpEntity = new HttpEntity<>(gson.toJson(struc),HttpUtil.getHttpHeaders(registContext));
				responseEntity = HttpUtil.getRestTemplate(builder).postForEntity(url,httpEntity,String.class);
				log.info("收到的回复 ："+responseEntity.getBody());
				if("0".equals(analyze(responseEntity.getBody()))){
					log.info("PersonConsumer : pushPerson >> the face data send success !");
					return true;
				}
			} catch (Exception e) {
				log.error("PersonConsumer : pushPerson >> the face data send failed, url : {} , exception : {}",url,e);
				if (e.getMessage().contains("401")) {
					RegisterContext registerContext = CacheService.getInstance().getRegisterContext();
					registServiceImpl.regist(registerContext);
				}
			}
		} catch (Exception e) {
			log.error("PersonConsumer : pushPerson >> the person data send  exception :",e);
		}finally{
			personStrucs.clear();
			personStrucs = null;
		}

		return false;
	}

	@Override
	public boolean pushVehicleData(List<MotorVehicleStruc> vehicleStrucs,RegisterContext registContext) {
		try {
			List<MotorVehicle> vehicleTotal = new ArrayList<MotorVehicle>();
			for (MotorVehicleStruc motorStruc : vehicleStrucs) {
				List<MotorVehicle> vehicles = motorStruc.getMotorVehicleListObject().getMotorVehicleObject();
				vehicleTotal.addAll(vehicles);
			}
			MotorVehicleStruc struc = new MotorVehicleStruc();
			MotorVehicleObject vehicleObj = new MotorVehicleObject();
			vehicleObj.setMotorVehicleObject(vehicleTotal);
			struc.setMotorVehicleListObject(vehicleObj);

			String url = String.format("http://%s:%s%s", registContext.getIP(),registContext.getPort(),GatConstant.VEHICLETURL);
			log.info("========推送url====== : {}",url);
			log.debug("======开始发送数据===== : {}",gson.toJson(struc));
			HttpEntity<String> httpEntity;
			ResponseEntity<String> responseEntity;
			try {
				httpEntity = new HttpEntity<>(gson.toJson(struc),HttpUtil.getHttpHeaders(registContext));
				responseEntity = HttpUtil.getRestTemplate(builder).postForEntity(url,httpEntity,String.class);
				log.info("收到的回复 ："+responseEntity.getBody());
				if("0".equals(analyze(responseEntity.getBody()))){
					log.info("VehicleConsumer : pushVehicle >> the face data send success !");
					return true;
				}
			} catch (Exception e) {
				log.error("VehicleConsumer : pushVehicle >> the face data send failed, url : {} , exception : {}",url,e);
				if (e.getMessage().contains("401")) {
					RegisterContext registerContext = CacheService.getInstance().getRegisterContext();
					registServiceImpl.regist(registerContext);
				}
			}
		} catch (Exception e) {
			log.error("VehicleConsumer : pushVehicle >> the vehicle data send  exception :",e);
		}finally{
			vehicleStrucs.clear();
			vehicleStrucs = null;
		}
		return false;
	}

	@Override
	public boolean pushNonMotorData(List<NonMotorVehicleStruc> nonMotorStrucs,RegisterContext registContext) {
		try {
			List<NonMotorVehicle> noMotorTotal = new ArrayList<NonMotorVehicle>();
			for (NonMotorVehicleStruc nonMotorStruc : nonMotorStrucs) {
				List<NonMotorVehicle> noMotor = nonMotorStruc.getNonMotorVehicleListObject().getNonMotorVehicleObject();
				noMotorTotal.addAll(noMotor);
			}
			NonMotorVehicleStruc struc = new NonMotorVehicleStruc();
			NonMotorVehicleObject noMotorObj = new NonMotorVehicleObject();
			noMotorObj.setNonMotorVehicleObject(noMotorTotal);
			struc.setNonMotorVehicleListObject(noMotorObj);
			
			String url = String.format("http://%s:%s%s", registContext.getIP(),registContext.getPort(),GatConstant.NOMOTORSTURL);
			log.info("========推送url====== : {}",url);
			log.debug("======开始发送数据===== : {}",gson.toJson(struc));
			HttpEntity<String> httpEntity;
			ResponseEntity<String> responseEntity;
			try {
				httpEntity = new HttpEntity<>(gson.toJson(struc),HttpUtil.getHttpHeaders(registContext));
				responseEntity = HttpUtil.getRestTemplate(builder).postForEntity(url,httpEntity,String.class);
				log.info("收到的回复 ："+responseEntity.getBody());
				if("0".equals(analyze(responseEntity.getBody()))){
					log.info("NonMotorConsumer : pushNoMotor >> the face data send success !");
					return true;
				}
			} catch (Exception e) {
				log.error("NonMotorConsumer : pushNoMotor >> the face data send failed, url : {} , exception : {}",url,e);
				if (e.getMessage().contains("401")) {
					RegisterContext registerContext = CacheService.getInstance().getRegisterContext();
					registServiceImpl.regist(registerContext);
				}
			}
		} catch (Exception e) {
			log.error("NonMotorConsumer : pushNoMotor >> the noMotor data send  exception :",e);
		}finally{
			nonMotorStrucs.clear();
			nonMotorStrucs = null;
		}
		return false;
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
