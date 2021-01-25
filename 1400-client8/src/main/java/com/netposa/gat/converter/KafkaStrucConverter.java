package com.netposa.gat.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netposa.gat.model.common.SubImageInfo;
import com.netposa.gat.model.common.SubImageInfoStruc;
import com.netposa.gat.model.face.Face;
import com.netposa.gat.model.face.FaceStruc.FaceObject;
import com.netposa.gat.model.motor.MotorVehicle;
import com.netposa.gat.model.motor.MotorVehicleStruc.MotorVehicleObject;
import com.netposa.gat.model.nomotor.NonMotorVehicle;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc.NonMotorVehicleObject;
import com.netposa.gat.model.person.Person;
import com.netposa.gat.model.person.PersonStruc.PersonObject;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaStrucConverter {
	
	public FaceObject getFaceObject(FaceObject faceobj,RegisterContext registContext){
		log.info("into convert  getFaceObject ...");
		try {
			List<Face> facess = new ArrayList<Face>();
			if(!StringUtils.isEmpty(registContext.getGreenList())){
				String[] greenDeviceID = registContext.getGreenList().split(",");
				List<String> greenDeviceIDs = Arrays.asList(greenDeviceID);
				for (Face face : faceobj.getFaceObject()) {
					log.info("face.getDeviceID() :" +face.getDeviceID());
					if(StringUtils.isEmpty(face.getSourceID())){
						log.info("face sourceID is null , DeviceID :{}",face.getDeviceID());
						continue;
					}
					if(greenDeviceIDs.contains(face.getDeviceID())){
						if(face.getDeviceID().equals("227")){
							face.setDeviceID("31010400001310932835");
						}
					}
					facess.add(face);
				}
				log.info("facess size()=>"+facess.size());
				log.info("facess is empty ...");
				if(facess.isEmpty()){
					return null;
				}
				faceobj.setFaceObject(facess);
			}
			for (Face face : faceobj.getFaceObject()) {
				log.info("下载图片face ："+face);
				SubImageInfoStruc imageInfoStruc  = face.getSubImageList();
				List<SubImageInfo> SubImageInfoObject = imageInfoStruc.getSubImageInfoObject();
				for (SubImageInfo subImageInfo : SubImageInfoObject) {
					//商汤要求
					subImageInfo.setDeviceID(face.getDeviceID());
					if("11".equals(subImageInfo.getType())){
						subImageInfo.setData(BaseUtils.getBase64Data(subImageInfo.getStoragePath()));
					}
				}
			}
		} catch (Exception e) {
			log.info("down load image fialed ! ");
		}
		return faceobj;
	}

	public PersonObject getPersonObject(PersonObject personObj,RegisterContext registContext){
		//根据DeviceID过滤
		List<Person> personss = new ArrayList<Person>();
		if(!StringUtils.isEmpty(registContext.getGreenList())){
			String[] greenDeviceID = registContext.getGreenList().split(",");
			List<String> greenDeviceIDs = Arrays.asList(greenDeviceID);
			List<Person> persons = personObj.getPersonObject();
			for (Person person : persons) {
				log.debug("person.getDeviceID() :" +person.getDeviceID());
				if(greenDeviceIDs.contains(person.getDeviceID())){
					personss.add(person);
				}
			}
			if(personss.isEmpty()){
				return null;
			}
			personObj.setPersonObject(personss);
		}
		return personObj;
	}

	public MotorVehicleObject getMotorVehicleObject(MotorVehicleObject motorVehicleObj,RegisterContext registContext){
		List<MotorVehicle> motorss = new ArrayList<MotorVehicle>();
		if(!StringUtils.isEmpty(registContext.getGreenList())){
			String[] greenDeviceID = registContext.getGreenList().split(",");
			List<String> greenDeviceIDs = Arrays.asList(greenDeviceID);
			List<MotorVehicle> motors = motorVehicleObj.getMotorVehicleObject();
			for (MotorVehicle motor : motors) {
				log.debug("person.getDeviceID() :" +motor.getDeviceID());
				if(greenDeviceIDs.contains(motor.getDeviceID())){
					motorss.add(motor);
				}
			}
			if(motorss.isEmpty()){
				return null;
			}
			motorVehicleObj.setMotorVehicleObject(motorss);
		}
		List<MotorVehicle> motors = motorVehicleObj.getMotorVehicleObject();
		for (MotorVehicle motorVehicle : motors) {
			motorVehicle.setVehicleLength("0");
			motorVehicle.setSpeed("0");
		}
		return motorVehicleObj;
	}
	public NonMotorVehicleObject getNonMotorVehicleObject(NonMotorVehicleObject nonMotorVehicleObj,RegisterContext registContext){
		//根据DeviceID过滤
		List<NonMotorVehicle> nomotorss = new ArrayList<NonMotorVehicle>();
		if(!StringUtils.isEmpty(registContext.getGreenList())){
			String[] greenDeviceID = registContext.getGreenList().split(",");
			List<String> greenDeviceIDs = Arrays.asList(greenDeviceID);
			List<NonMotorVehicle> nomotors = nonMotorVehicleObj.getNonMotorVehicleObject();
			for (NonMotorVehicle nomotor : nomotors) {
				log.debug("person.getDeviceID() :" +nomotor.getDeviceID());
				if(greenDeviceIDs.contains(nomotor.getDeviceID())){
					nomotorss.add(nomotor);
				}
			}
			if(nomotorss.isEmpty()){
				return null;
			}
			nonMotorVehicleObj.setNonMotorVehicleObject(nomotorss);
		}
		return nonMotorVehicleObj;
	}
}
