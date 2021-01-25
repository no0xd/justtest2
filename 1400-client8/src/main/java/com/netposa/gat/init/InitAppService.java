package com.netposa.gat.init;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netposa.gat.component.CacheService;
import com.netposa.gat.config.BeanConfig;
import com.netposa.gat.consumer.FaceKafkaConsumer;
import com.netposa.gat.consumer.NonMotorKafkaConsumer;
import com.netposa.gat.consumer.PersonKafkaConsumer;
import com.netposa.gat.consumer.VehicleKafkaConsumer;
import com.netposa.gat.model.kafka.KafkaConfig;
import com.netposa.gat.model.kafka.KafkaProperty;
import com.netposa.gat.model.regist.RegisterContext;
import com.netposa.gat.service.IRegistService;
import com.netposa.gat.utils.BaseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author netposa
 *
 */
@Slf4j
@Component
public class InitAppService {

	@Autowired
	public Gson gson;

	@Autowired
	public BeanConfig beanConfig;

	@Autowired
	InitGatContextService gatContextService;
	
	@Resource(name = "registServiceImpl")
	private IRegistService registServiceImpl;
	
	
	public void init(){
		log.info("Init load context.json....");
		initGatContext();
		autoRegistAndkeepAlive();
		initKafkaConsumer();
	}

	private void initKafkaConsumer(){
		JsonObject  gatContext = CacheService.getInstance().getGatContext();
		JsonElement element = gatContext.get("kafkaConfig");
		KafkaConfig config = gson.fromJson(element.toString(), KafkaConfig.class);
		List<KafkaProperty> kafka = config.getKafkaList();
		RegisterContext registerContext = CacheService.getInstance().getRegisterContext();
		String[] dataType = registerContext.getDataType().split(",");
		List<String> dataTypes = Arrays.asList(dataType);
		for (KafkaProperty kafkaProperty : kafka) {
			if("face".equals(kafkaProperty.getTopicType())&&dataTypes.contains("face")){
				FaceKafkaConsumer faceConsumer = new FaceKafkaConsumer(registerContext,kafkaProperty.getProperties());
				faceConsumer.start();
			}else if("person".equals(kafkaProperty.getTopicType())&&dataTypes.contains("person")){
				PersonKafkaConsumer personConsumer = new PersonKafkaConsumer(registerContext,kafkaProperty.getProperties());
				personConsumer.start();
			}else if("vehicle".equals(kafkaProperty.getTopicType())&&dataTypes.contains("vehicle")){
				VehicleKafkaConsumer vehicleConsumer = new VehicleKafkaConsumer(registerContext,kafkaProperty.getProperties());
				vehicleConsumer.start();
			}else if("nomotor".equals(kafkaProperty.getTopicType())&&dataTypes.contains("nomotor")){
				NonMotorKafkaConsumer noMotorConsumer = new NonMotorKafkaConsumer(registerContext,kafkaProperty.getProperties());
				noMotorConsumer.start();
			}else{
				log.info("topicType is not exist  : {}",kafkaProperty.getTopicType());
			}
		}
	}

	private void initGatContext(){
		gatContextService.init();
	}
	
	private void autoRegistAndkeepAlive(){
//		RegisterContext registerContext = CacheService.getInstance().getRegisterContext();
//		while(!registServiceImpl.regist(registerContext)){
//			BaseUtils.threadSleep(5000);
//			log.error("regist server failed ! server ip : {} , server port : {}",registerContext.getIP(),registerContext.getPort());
//		}
//		registServiceImpl.keepAlive(registerContext);
	}
}
