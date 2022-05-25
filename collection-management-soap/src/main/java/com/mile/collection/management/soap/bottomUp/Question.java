package com.mile.collection.management.soap.bottomUp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;


@Table(value = "Question")
@Component
public class Question {
	
    @Id
	private Long id;
	private String value;
	private Set<Answer> answers = new HashSet<>();
	private Set<Category> categories = new HashSet<>();

	public Question() {}
	public Question(Long id, String value, Set<Answer> answers, Set<Category> categories) {
		super();
		this.id = id;
		this.value = value;
		this.answers = answers;
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
	public Set<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", value=" + value + ", answers=" + answers + ", categories=" + categories + "]";
	}

}