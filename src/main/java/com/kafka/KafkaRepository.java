package com.kafka;

import java.sql.SQLException;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class KafkaRepository {

	final Logger logger = Logger.getLogger(KafkaRepository.class);

	@PersistenceContext
	private EntityManager entyEntityManager;

	@Value("${sql}")
	private String sql;

	@SuppressWarnings("unchecked")
	public void saveDetails(Person kafka) {

		try {
			javax.persistence.Query query = entyEntityManager.createNativeQuery(sql);
			query.setParameter(1, kafka.getId()).setParameter(1, kafka.getId()).setParameter(2, kafka.getName())
					.setParameter(3, kafka.getBirthdate());

			query.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}
}
