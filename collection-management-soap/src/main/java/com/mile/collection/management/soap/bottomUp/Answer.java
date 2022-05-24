package com.mile.collection.management.soap.bottomUp;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

@Table(value = "Answer")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "answer", propOrder = {
    "categories",
    "id",
    "question",
    "value"
})
@Component
public class Answer {

	@Id
	@XmlElement(required = true)
	protected Long id;
	@XmlElement(required = true)
	protected String value;
	@XmlElement(required = true)
	protected Question question;
    @XmlElement(nillable = true)
	protected Set<Category> categories = new HashSet<>();

	public Answer() {}
	public Answer(Long id, String value, Question question, Set<Category> categories) {
		super();
		this.id = id;
		this.value = value;
		this.question = question;
		this.categories = categories;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", value=" + value + ", question=" + question + ", categories=" + categories + "]";
	}



}