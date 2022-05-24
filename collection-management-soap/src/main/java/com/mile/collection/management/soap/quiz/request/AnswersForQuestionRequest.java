package com.mile.collection.management.soap.quiz.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AnswersForQuestionRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswersForQuestionRequest {

	@XmlElement(required = true, type = Long.class)
	protected long id;

	public AnswersForQuestionRequest() {}
	public AnswersForQuestionRequest(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
