package com.mile.collection.management.soap.quiz.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.mile.collection.management.soap.bottomUp.Answer;

@XmlRootElement(name = "AnswersResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(Answer.class)
public class AnswersResponse {

	@XmlElement(type = ArrayList.class, required = true)
	private List<Answer> answers = new ArrayList<>(); 
	
	public AnswersResponse() {}
	public AnswersResponse(Set<Answer> answersForQuestion) {
		answersForQuestion.stream().forEach(a -> answers.add(a));
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		return " [answers=" + answers + "]";
	}
	

}
