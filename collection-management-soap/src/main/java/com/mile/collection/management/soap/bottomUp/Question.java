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


@Table(value = "Question")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "question", propOrder = {
    "answers",
    "categories",
    "id",
    "value"
})
@Component
public class Question {
	
    @Id 
    @XmlElement(required = true)
	private Long id;
    @XmlElement(required = true)
	private String value;
    @XmlElement(nillable = true)
	private Set<Answer> answers = new HashSet<>();
    @XmlElement(nillable = true)
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