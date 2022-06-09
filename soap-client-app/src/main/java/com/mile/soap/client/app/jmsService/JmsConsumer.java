package com.mile.soap.client.app.jmsService;

import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component @EnableJms
public class JmsConsumer {

	@JmsListener(destination = "quiz-queue")
	public void fetchAnswers(Object message) {
		LoggerFactory.getLogger(JmsConsumer.class).info("received:" + message.toString());
	}
	
}