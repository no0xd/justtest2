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

import com.netposa.gat.model.face.FaceStruc;
import com.netposa.gat.model.face.FaceStruc.FaceObject;
import com.netposa.gat.model.iod.IodFaceStruc;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.model.subscribe.Subscribe;
import com.netposa.gat.utils.BaseUtils;
import com.netposa.gat.utils.SubscribeDetailEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author netposa
 *
 */

@Slf4j
@Service
public class FaceKafkaConsumer extends AbsCommonConsumer {

	private Properties properties;

	private RegisterContext registContext;

	private List<FaceStruc> faceStrucs = new ArrayList<FaceStruc>();

	public FaceKafkaConsumer() {};
	
	public FaceKafkaConsumer(RegisterContext registerContext, Properties properties) {
		this.properties = properties;
		properties.put("session.timeout.ms", "40000");
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
					BaseUtils.threadSleep(200);
					continue;
				}
				faceStrucs.clear();
				for (ConsumerRecord<String, String> record : records) {
					log.info("recored value=>"+record.value().toString());
					try {
						FaceStruc faceStruc = createDataInfo(record.value().toString());
						if (ObjectUtils.isEmpty(faceStruc)) {
							log.info("=========== createfaceStruc : null==================");
							continue;
						}
						faceStrucs.add(faceStruc);
					} catch (Exception e) {
						log.error("create faceStruc fail , exception : {} ", e);
					}
				}

				if (faceStrucs.isEmpty()) {
					log.info("faceStrucs list is impty !");
					continue;
				}
				if (!isSubscribeModel()) {
					log.info("==============直推模式开启！===============");
					pushData();
				} else {
					log.info("==============订阅模式开启！===============");
					notificationData();
				}
			} catch (Exception ex) {
				BaseUtils.threadSleep(1000);
				if (consumer != null && _waitTime >= 60) {
					consumer.close();
					consumer = null;
					consumer = new KafkaConsumer<>(properties);
					consumer.subscribe(Arrays.asList(properties.getProperty("topic")));
					_waitTime = 0;
				}
				_waitTime++;
			}
		}
	}

	protected FaceStruc createDataInfo(String data) {
		log.info("into createDataInfo"+data);
		IodFaceStruc iodFace = gson.fromJson(data, IodFaceStruc.class);
		log.info("iodFace"+iodFace);
		// proxyManufacturer为null或者""时表示全部推送
		log.info("getProxyManufacturer"+registContext.getProxyManufacturer());
		if (StringUtils.isEmpty(registContext.getProxyManufacturer())) {
			
			FaceObject faceObj = converter.getFaceObject(iodFace.getFaceListObject(), registContext);
			if (ObjectUtils.isEmpty(faceObj)) {
				log.info("faceObj is null ...");
				return null;
			}
			
			log.info("create faceObj ok ,faceObj : {}", gson.toJson(faceObj));
			FaceStruc faceStruc = new FaceStruc();
			faceStruc.setFaceListObject(faceObj);
			log.info("return faceStruc");
			return faceStruc;
		}

		List<String> filterManufacturers = Arrays.asList(registContext.getProxyManufacturer().split(","));
		// 根据配置的厂商名过滤
		if (!filterManufacturers.contains(iodFace.getProxyManufacturer())) {
			log.info("根据配置的厂商名过滤  ====>return null ...");
			return null;
		}

		//
		log.info("into converter ...");
		FaceObject faceObj = converter.getFaceObject(iodFace.getFaceListObject(), registContext);
		if (ObjectUtils.isEmpty(faceObj)) {
			log.info("   faceObj is null  ====>    return null ...");
			return null;
		}
		log.info("create faceObj ok ,faceObj : {}", gson.toJson(faceObj));
		FaceStruc faceStruc = new FaceStruc();
		faceStruc.setFaceListObject(faceObj);
		return faceStruc;

	}

	@Override
	protected <T> boolean notificationData() {
		log.info("========================开始通知人脸数据======================  ");
		log.info("into notificationData for face ...");
		List<Subscribe> subscribes = _subscribeService.getSubscribeByStatus(0);
		List<Subscribe> subs = new ArrayList<Subscribe>();
		for (Subscribe subscribe : subscribes) {
			String subscribeDetail = subscribe.getSubscribeDetail();
			if (SubscribeDetailEnum.FaceInfo.getCode().equals(subscribeDetail)) {
				subs.add(subscribe);
			}
		}
		for (Subscribe subscribe : subs) {
			if (subscribe.getReceiveAddr().contains(registContext.getIP())) {
				for (FaceStruc faceStruc : faceStrucs) {
					while (!_notificationService.NotificationFaceData(faceStruc, subscribe)) {
						log.error("FaceConsumer : notifyFace >> the face data send failed !");
						BaseUtils.threadSleep(1000);
					}
				}
			}
		}
		return true;
	}

	@Override
	protected <T> boolean pushData() {
		// while(!_pushService.pushFaceData(faceStrucs,registContext)){
		// log.error("FaceConsumer : pushFace >> the face data send failed ! ");
		// BaseUtils.threadSleep(3000);
		// }
		return _pushService.pushFaceData(faceStrucs, registContext);
	}

	public synchronized Properties getProperties() {
		return properties;
	}

	public synchronized void setProperties(Properties properties) {
		this.properties = properties;
	}

	public synchronized RegisterContext getRegistContext() {
		return registContext;
	}

	public synchronized void setRegistContext(RegisterContext registContext) {
		this.registContext = registContext;
	}

}
