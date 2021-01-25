package com.netposa.gat.consumer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netposa.gat.component.CacheService;
import com.netposa.gat.converter.KafkaStrucConverter;
import com.netposa.gat.service.INotificationService;
import com.netposa.gat.service.IPushDataService;
import com.netposa.gat.service.IRegistService;
import com.netposa.gat.service.ISubscribeService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public abstract class AbsCommonConsumer extends Thread {
	
	@Autowired
	public Gson bootGson;
	
	@Autowired
	IPushDataService PushDataServiceImpl;
	
	@Autowired
	KafkaStrucConverter bootConverter;
	
	@Resource(name = "notificationServiceImpl")
	INotificationService notificationServiceImpl;
	
	@Resource(name = "subscribeServiceImpl")
	ISubscribeService subscribeServiceImpl;
	
	@Autowired
	IRegistService registServiceImpl;
	
	static Gson gson;
	
	static IPushDataService _pushService;
	
	static INotificationService _notificationService;
	
	static KafkaStrucConverter converter;
	
	static ISubscribeService _subscribeService;
	
	static IRegistService _registServiceImpl;
	
	protected KafkaConsumer<String, String> consumer;
	
	@PostConstruct
	void init(){
		gson = 	bootGson;
		converter = bootConverter;
		_pushService = PushDataServiceImpl;
		_notificationService = notificationServiceImpl;
		_subscribeService = subscribeServiceImpl;
		_registServiceImpl = registServiceImpl;
	}
	
	/**
	 * 订阅通知模式
	 * @param strucs
	 * @return
	 */
	protected abstract <T> boolean notificationData();
	
	/**
	 * 直推模式
	 * @param strucs
	 * @return
	 */
	protected abstract <T> boolean pushData();
	
	/**
	 * 模式 Y/N
	 * @return
	 */
	protected boolean isSubscribeModel(){
		JsonObject  jsonObject = CacheService.getInstance().getGatContext();
		JsonElement element = jsonObject.get("isSubscribe");
		log.info("isPushModel :"+element.toString());
		return "true".equals(element.toString()) ? true : false; 
	}
}
