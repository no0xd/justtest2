package com.netposa.gat.config;

import java.util.Properties;

import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.JsonParser;
import com.netposa.gat.consumer.FaceKafkaConsumer;

@Configuration
public class BeanConfig {

	@Bean
	public SchedulerFactory createSchedulerFactory(){
		SchedulerFactory SchedulerFactory = new StdSchedulerFactory();
		return SchedulerFactory;
	}
	
	@Bean
	public JsonParser createJsonParser(){
		return new JsonParser();
	}
	
}
