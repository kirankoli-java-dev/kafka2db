package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

@Service
public class KafkaConsumer {

	@Autowired(required = true)
	private KafkaRepository kafkaRepository;

	final static Logger logger = Logger.getLogger(KafkaToDbApplication.class);

	@KafkaListener(topics = "${spring.topic.name}", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(Person kafka) {
		
		kafkaRepository.saveDetails(kafka);
		logger.info("Data inserted...");
	}
	}
