package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kiran koli
 *
 * 
 */
@SpringBootApplication
public class KafkaToDbApplication implements CommandLineRunner {

	@Value("${log}")
	private String log;

	final Logger logger = Logger.getLogger(KafkaToDbApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(KafkaToDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PropertyConfigurator.configure(log);
		logger.info("logger enable");
		
//		KafkaRepository kafkaRepository =new KafkaRepository();
		
		
	}

}
