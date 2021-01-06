package com.example.boot.mq.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class TestQueueListener {
	
	private static final Logger logger = LoggerFactory.getLogger(TestQueueListener.class);
	@RabbitListener(queues = {"test-queue"})
	@RabbitHandler
	public void handleMessage(Channel channel,Message message) throws IOException {
		logger.info("message:{}",message);
		logger.info("channel:{}",channel);
//		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
