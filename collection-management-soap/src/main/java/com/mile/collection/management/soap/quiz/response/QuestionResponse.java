package com.mile.collection.management.soap.quiz.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mile.collection.management.soap.bottomUp.Question;

@XmlRootElement(name = "QuestionResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionResponse {

	@XmlElement(required = true, type = Question.class)
	private Question question;
	public QuestionResponse() {}
	public QuestionResponse(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}

}
