package com.netposa.gat.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.netposa.gat.model.iod.IodMotorVehicleStruc;
import com.netposa.gat.model.motor.MotorVehicleStruc;
import com.netposa.gat.model.motor.MotorVehicleStruc.MotorVehicleObject;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.utils.BaseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author netposa
 *
 */
@Slf4j
@Service
public class VehicleKafkaConsumer  extends AbsCommonConsumer{
	
	private Properties properties;
	
	private RegisterContext registContext;
	
	private List<MotorVehicleStruc> vehicleStrucs = new ArrayList<MotorVehicleStruc>();
	
	public VehicleKafkaConsumer(){};
	
	public VehicleKafkaConsumer(RegisterContext registerContext,Properties properties){
		this.properties = properties;
		this.registContext = registerContext;
		consumer = new KafkaConsumer<>(this.properties);
		consumer.subscribe(Arrays.asList(properties.getProperty("topic")));
	}
	
	@Override
	public void run() {
		consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Arrays.asList(properties.getProperty("topic")));
		int _waitTime = 0;
		boolean running = true;
		while (running) {
			try {
				ConsumerRecords<String, String> records = consumer.poll(100);
				log.info("kafka cusumer size : {}", records.count());
				if (records.count() <= 0) {
					continue ;
				}
				vehicleStrucs.clear();
				for (ConsumerRecord<String, String> record : records) {
					try {
						MotorVehicleStruc vehicleStruc = createDataInfo(record.value().toString());
						if (vehicleStruc == null) {
							continue;
						}
						vehicleStrucs.add(vehicleStruc);
					} catch (Exception e) {
						log.error("create vehicleStruc fail , exception : {} ", e);
					}
				}

				if (vehicleStrucs.isEmpty()) {
					continue;
				}
				if(!isSubscribeModel()){
					pushData();
				}else{
					notificationData();
				}
			} catch (Exception ex) {
				BaseUtils.threadSleep(1000);
				if(consumer!=null && _waitTime>=60){
					consumer.close();
					consumer = null;
					consumer = new KafkaConsumer<>(properties);
					consumer.subscribe(Arrays.asList(properties.getProperty("topic")));
					_waitTime=0;
				}
				_waitTime++;
			}
		}
	}

	@Override
	protected boolean notificationData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean pushData() {
//		while(_pushService.pushVehicleData(vehicleStrucs,registContext)){
//			log.error("VehicleConsumer : pushvehicle >> the vehicle data send failed ! ");
//			BaseUtils.threadSleep(3000);
//		}
		return _pushService.pushVehicleData(vehicleStrucs,registContext);
	}

	protected  MotorVehicleStruc createDataInfo(String data) {
		IodMotorVehicleStruc iodVehicle = gson.fromJson(data, IodMotorVehicleStruc.class);
		if (StringUtils.isEmpty(registContext.getProxyManufacturer())) {
			MotorVehicleObject VehicleObj = converter.getMotorVehicleObject(iodVehicle.getMotorVehicleListObject(),registContext);
			if(ObjectUtils.isEmpty(VehicleObj)){
				return null;
			}
			MotorVehicleStruc VehicleStruc = new MotorVehicleStruc();
			VehicleStruc.setMotorVehicleListObject(VehicleObj);
			return VehicleStruc;
		}
		List<String> filterManufacturers = Arrays.asList(registContext.getProxyManufacturer().split(","));
		//根据配置的厂商名过滤
			
			if(!filterManufacturers.contains(iodVehicle.getProxyManufacturer())){
				return null;
			}
			MotorVehicleObject VehicleObj = converter.getMotorVehicleObject(iodVehicle.getMotorVehicleListObject(),registContext);
			if(ObjectUtils.isEmpty(VehicleObj)){
				return null;
			}
			MotorVehicleStruc VehicleStruc = new MotorVehicleStruc();
			VehicleStruc.setMotorVehicleListObject(VehicleObj);
			return VehicleStruc;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public RegisterContext getRegistContext() {
		return registContext;
	}

	public void setRegistContext(RegisterContext registContext) {
		this.registContext = registContext;
	}

}
