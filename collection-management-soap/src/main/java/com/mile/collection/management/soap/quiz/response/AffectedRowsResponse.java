package com.mile.collection.management.soap.quiz.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AffectedRowsResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class AffectedRowsResponse {

	private int result;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public AffectedRowsResponse(int result) {
		super();
		this.result = result;
	}
	
	public AffectedRowsResponse() {}
}
