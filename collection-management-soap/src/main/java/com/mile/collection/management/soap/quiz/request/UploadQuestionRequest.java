package com.mile.collection.management.soap.quiz.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "uploadQuestionRequest")

@XmlAccessorType(XmlAccessType.FIELD)
public class UploadQuestionRequest {

	@XmlElement(required = true, type = String.class)
	private String val;
	public UploadQuestionRequest() {}
	public UploadQuestionRequest(String val) {
		super();
		this.val = val;
	}
	
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
}
