package com.mile.collection.management.soap.bottomUp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

@Table(value = "Answer")
@Component
public class Answer {

	@Id
	protected Long id;
	protected String value;
	protected Question question;
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