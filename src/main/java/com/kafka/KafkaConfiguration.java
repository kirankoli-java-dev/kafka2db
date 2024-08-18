package com.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.HashMap;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;



import java.util.List;

/**
 * @author kiran koli
 *
 * 
 */
@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Value("${localhost}")
	private String localhost;

	@Value("${security_protocol}")
	private String security_protocol;

	@Value("${ssl_truststore_location}")
	private String ssl_truststore_location;

	@Value("${ssl_truststore_password}")
	private String ssl_truststore_password;

	@Value("${ssl_keystore_location}")
	private String ssl_keystore_location;

	@Value("${ssl_keystore_password}")
	private String ssl_keystore_password;

	@Value("${ssl_key_password}")
	private String ssl_key_password;

	@Value("${is_secure_kafka}")
	private String is_secure_kafka;


	@Value("${enable_auto_commit_config}")
	private String enable_auto_commit_config;

	final Logger logger = Logger.getLogger(KafkaConfiguration.class);

	@Bean
	public ConsumerFactory<String, Person> userConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		logger.info("inside ConsumerFactory");
		try {
			if (is_secure_kafka.equals("Y")) {
				config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, localhost);
				config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
//				config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
				config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enable_auto_commit_config);
//				config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, auto_commit_interval_ms_config);
//	       		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
				config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
				config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
				config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
				config.put("security.protocol", security_protocol);
				config.put("ssl.keystore.location", ssl_keystore_location);
				config.put("ssl.keystore.password", ssl_keystore_password);
				config.put("ssl.truststore.location", ssl_truststore_location);
				config.put("ssl.truststore.password", ssl_truststore_password);
				config.put("ssl.key.password", ssl_key_password);
			} else if (is_secure_kafka.equals("N")) {
				config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, localhost);
				config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
//		   		config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
				config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enable_auto_commit_config);
//				config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, auto_commit_interval_ms_config);
//	       		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
				config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
				config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
				config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

			}

			return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
					new JsonDeserializer<>(Person.class));

		} catch (Exception e) {
			logger.info("ERROR IN userConsumerFactory " + e);
			return null;
		}
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Person> userKafkaListenerFactory() {
		logger.info("inside ConcurrentKafkaListenerContainerFactory");
		try {
			ConcurrentKafkaListenerContainerFactory<String, Person> factory = new ConcurrentKafkaListenerContainerFactory<>();
			factory.setConsumerFactory(userConsumerFactory());
			
			return factory;
		} catch (Exception e) {
			logger.info("ERROR IN userKafkaListenerFactory " + e);
			return null;
		}
	}

}
