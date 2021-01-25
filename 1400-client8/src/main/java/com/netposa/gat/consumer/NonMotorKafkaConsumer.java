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

import com.netposa.gat.model.iod.IodNonMotorVehicleStruc;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc.NonMotorVehicleObject;
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
public class NonMotorKafkaConsumer extends AbsCommonConsumer {
	
	private Properties properties;
	
	private RegisterContext registContext;
	
	private List<NonMotorVehicleStruc> nonMotorVehicleStrucs = new ArrayList<NonMotorVehicleStruc>();
	
	public NonMotorKafkaConsumer(){};
	
	public NonMotorKafkaConsumer(RegisterContext registerContext,Properties properties){
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
				nonMotorVehicleStrucs.clear();
				for (ConsumerRecord<String, String> record : records) {
					try {
						NonMotorVehicleStruc nonMotorVehicleStruc = createDataInfo(record.value().toString());
						if (nonMotorVehicleStruc == null) {
							continue;
						}
						nonMotorVehicleStrucs.add(nonMotorVehicleStruc);
					} catch (Exception e) {
						log.error("create nonMotorVehicleStruc fail , exception : {} ", e);
					}
				}

				if (nonMotorVehicleStrucs.isEmpty()) {
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
	protected <T> boolean notificationData() {
		return false;
	}

	@Override
	protected <T> boolean pushData() {
		while(_pushService.pushNonMotorData(nonMotorVehicleStrucs,registContext)){
			log.error("NoMotorConsumer : pushNoMotor >> the nomotor data send failed ! ");
			BaseUtils.threadSleep(3000);
		}
		return true;
	}

	protected <T> NonMotorVehicleStruc createDataInfo(String data) {
		IodNonMotorVehicleStruc iodNomoterVehicle = gson.fromJson(data, IodNonMotorVehicleStruc.class);
		// proxyManufacturer为null或者""时表示全部推送
		if (StringUtils.isEmpty(registContext.getProxyManufacturer())) {
			NonMotorVehicleObject noMotorVehicleObj = converter.getNonMotorVehicleObject(iodNomoterVehicle.getNonMotorVehicleListObject(),registContext);
			if(ObjectUtils.isEmpty(noMotorVehicleObj)){
				return null;
			}
				NonMotorVehicleStruc noMotorVehicleStruc = new NonMotorVehicleStruc();
				noMotorVehicleStruc.setNonMotorVehicleListObject(noMotorVehicleObj);
				return noMotorVehicleStruc;
		}
		
		List<String> filterManufacturers = Arrays.asList(registContext.getProxyManufacturer().split(","));
		//根据配置的厂商名过滤
	
			
			if(!filterManufacturers.contains(iodNomoterVehicle.getProxyManufacturer())){
				return null;
			}
			
			NonMotorVehicleObject noMotorVehicleObj = converter.getNonMotorVehicleObject(iodNomoterVehicle.getNonMotorVehicleListObject(),registContext);
			if(ObjectUtils.isEmpty(noMotorVehicleObj)){
				return null;
			}
			NonMotorVehicleStruc noMotorVehicleStruc = new NonMotorVehicleStruc();
			noMotorVehicleStruc.setNonMotorVehicleListObject(noMotorVehicleObj);
			return noMotorVehicleStruc;
		
		
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
