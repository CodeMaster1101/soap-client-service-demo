package com.mile.collection.management.soap.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.mile.collection.management.soap.bottomUp.Answer;
import com.mile.collection.management.soap.bottomUp.Category;
import com.mile.collection.management.soap.bottomUp.Question;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class AnswerRowMapper implements RowMapper<Answer> {

	private Set<Category> categories = new HashSet<>();
	private Question question;
	
	public AnswerRowMapper(Question question) {
		this.question = question;
	}
	public AnswerRowMapper(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Answer answer = new Answer();
		answer.setId(rs.getLong("id"));
		answer.setValue(rs.getString("val"));
		answer.setQuestion(question);
		answer.setCategories(categories);
		return answer;
	}

}
