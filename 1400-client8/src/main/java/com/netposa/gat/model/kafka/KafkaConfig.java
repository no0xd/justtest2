package com.netposa.gat.model.kafka;

import java.util.List;

import lombok.Data;

@Data
public class KafkaConfig {
	
	private List<KafkaProperty> kafkaList;
	
}
