package com.netposa.gat.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.netposa.gat.model.iod.IodPersonStruc;
import com.netposa.gat.model.person.PersonStruc;
import com.netposa.gat.model.person.PersonStruc.PersonObject;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.utils.BaseUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author netposa
 *
 */
@Slf4j
@Component
public class PersonKafkaConsumer extends AbsCommonConsumer {
	
	private Properties properties;
	
	private RegisterContext registContext;
	
	private List<PersonStruc> personStrucs = new ArrayList<PersonStruc>();
	
	public PersonKafkaConsumer (){};
	
	public PersonKafkaConsumer(RegisterContext registerContext,Properties properties){
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
				personStrucs.clear();
				for (ConsumerRecord<String, String> record : records) {
					try {
						PersonStruc personStruc = createDataInfo(record.value().toString());
						if (personStruc == null) {
							continue;
						}
						personStrucs.add(personStruc);
					} catch (Exception e) {
						log.error("create personStruc fail , exception : {} ", e);
					}
				}

				if (personStrucs.isEmpty()) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected <T> boolean pushData() {
		while(_pushService.pushPersonData(personStrucs,registContext)){
			log.error("PersonConsumer : pushPerson >> the person data send failed ! ");
			BaseUtils.threadSleep(3000);
		}
		return true;
	}

	protected PersonStruc createDataInfo(String data) {
		IodPersonStruc iodPerson = gson.fromJson(data, IodPersonStruc.class);
		// proxyManufacturer为null或者""时表示全部推送
		if (StringUtils.isEmpty(registContext.getProxyManufacturer())) {
			PersonObject personObj = converter.getPersonObject(iodPerson.getPersonListObject(),registContext);
			if(ObjectUtils.isEmpty(personObj)){
				return null;
			}
			PersonStruc personStruc = new PersonStruc();
			personStruc.setPersonListObject(personObj);
			return personStruc;
		}
		List<String> filterManufacturers = Arrays.asList(registContext.getProxyManufacturer().split(","));
		//根据配置的厂商名过滤
			
		if(!filterManufacturers.contains(iodPerson.getProxyManufacturer())){
			return null;
		}
			//ilterManufacturers为null时表示全部推送
			PersonObject personObj = converter.getPersonObject(iodPerson.getPersonListObject(),registContext);
			if(ObjectUtils.isEmpty(personObj)){
				return null;
			}
			PersonStruc personStruc = new PersonStruc();
			personStruc.setPersonListObject(personObj);
			return personStruc;
		
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
