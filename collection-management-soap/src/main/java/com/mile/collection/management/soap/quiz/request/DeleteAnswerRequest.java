package com.mile.collection.management.soap.quiz.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deleteAnswerRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteAnswerRequest {
	
	@XmlElement(name = "id")
	protected Long id;

	public DeleteAnswerRequest() {}
	public DeleteAnswerRequest(long id) {
		this.id=id;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
