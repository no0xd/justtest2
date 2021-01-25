package com.netposa.gat.model.kafka;

import java.util.Properties;

import lombok.Data;
@Data
public class KafkaProperty {
	
	private String topicType;
	
	private Properties properties;
	
}
