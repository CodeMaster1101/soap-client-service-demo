package com.mile.collection.management.soap.bottomUp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;


@Table(value = "Category")
@Component
public class Category {

    @Id
  
	private Long id;
	private String value;
	private Set<Answer>answers = new HashSet<>(); 
	private Set<Question> questions = new HashSet<>();

	public Category() {}

	public Category(Long id, String value, Set<Answer> answers, Set<Question> questions) {
		super();
		this.id = id;
		this.value = value;
		this.answers = answers;
		this.questions = questions;
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

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", value=" + value + ", answers=" + answers + ", questions=" + questions + "]";
	}

}