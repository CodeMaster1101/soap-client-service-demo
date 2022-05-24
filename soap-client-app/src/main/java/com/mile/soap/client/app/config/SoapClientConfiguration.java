package com.mile.soap.client.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapClientConfiguration {

	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.mile.soap.client.app.quiz");
		return marshaller;
	}
	@Bean
	WebServiceTemplate template() {
		return new WebServiceTemplate(jaxb2Marshaller());
	}
}
