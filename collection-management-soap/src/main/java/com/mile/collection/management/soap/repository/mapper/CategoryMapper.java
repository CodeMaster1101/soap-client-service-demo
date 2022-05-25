package com.mile.collection.management.soap.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.jdbc.core.RowMapper;

import com.mile.collection.management.soap.quiz.Answer;
import com.mile.collection.management.soap.quiz.Category;
import com.mile.collection.management.soap.quiz.Question;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class CategoryMapper implements RowMapper<Category>{

	MultiValuedMap<Long, Answer> answersForCategory = new ArrayListValuedHashMap<Long,Answer>();	
	MultiValuedMap<Long, Question> questionsForCategory =  new ArrayListValuedHashMap<Long,Question>();
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		long id = rs.getLong("id");
		List<Question> questions = new ArrayList<>();
		List<Answer> answers = new ArrayList<>();
		if(questionsForCategory.containsKey(id)) {
			questions = questionsForCategory.get(id).stream().collect(Collectors.toList());
		}
		if(answersForCategory.containsKey(id)) {
			answers = answersForCategory.get(id).stream().collect(Collectors.toList());
		}
		return new Category(id, rs.getString("val"),  answers, questions);
	}

}
