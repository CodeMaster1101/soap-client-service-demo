package com.mile.soap.client.app.jmsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSMessageConsumer.class);
	@JmsListener(destination = "quiz-queue")
	public void fetchAnswers(String message) {
		LOGGER.info("received:" + message);
	}
	
}
