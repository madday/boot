package com.example.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RabbitConfig {

	@Autowired
	private CachingConnectionFactory factory;
	private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

	@Bean
	RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			if (!ack) {
				logger.error("消息发送异常!");
			} else {
				logger.info("收到确认，correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
			}
		});
		return rabbitTemplate;
	}
}
