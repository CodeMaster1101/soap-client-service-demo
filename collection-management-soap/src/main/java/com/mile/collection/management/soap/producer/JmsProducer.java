package com.mile.collection.management.soap.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {
	
	private JmsTemplate template;
	
	@Autowired
	public JmsProducer(JmsTemplate template) {
			this.template = template;
	}
	
	public void sendMsgtoDestination(Object response) {
		template.convertAndSend("quiz_queue",response);
	}
}
