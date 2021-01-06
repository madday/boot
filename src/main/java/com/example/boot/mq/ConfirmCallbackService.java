package com.example.boot.mq;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmCallbackService implements RabbitTemplate.ConfirmCallback {

	private static final Logger logger = LoggerFactory.getLogger(ConfirmCallbackService.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
    }
	
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
			logger.error("消息发送异常!");
		} else {
			logger.info("收到确认，correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
		}
	}

	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
}