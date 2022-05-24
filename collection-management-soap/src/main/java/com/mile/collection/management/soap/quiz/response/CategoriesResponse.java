package com.mile.collection.management.soap.quiz.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.mile.collection.management.soap.bottomUp.Category;

@XmlRootElement(name = "CategoryResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(Category.class)
public class CategoriesResponse {

	@XmlElement(name = "categories", required = true, type = ArrayList.class)
	private List<Category> categories = new ArrayList<>();
	
	public CategoriesResponse() {}
	public CategoriesResponse(List<Category> categories) {
		super();
		this.categories = categories;
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
