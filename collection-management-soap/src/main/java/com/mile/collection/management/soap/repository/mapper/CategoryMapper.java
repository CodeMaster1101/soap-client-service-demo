package com.mile.collection.management.soap.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.jdbc.core.RowMapper;

import com.mile.collection.management.soap.bottomUp.Answer;
import com.mile.collection.management.soap.bottomUp.Category;
import com.mile.collection.management.soap.bottomUp.Question;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class CategoryMapper implements RowMapper<Category>{

	MultiValuedMap<Long, Answer> answersForCategory = new ArrayListValuedHashMap<Long,Answer>();	
	MultiValuedMap<Long, Question> questionsForCategory =  new ArrayListValuedHashMap<Long,Question>();
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		long id = rs.getLong("id");
		Set<Question> questions = new HashSet<>();
		Set<Answer> answers = new HashSet<>();
		if(questionsForCategory.containsKey(id)) {
			questions = questionsForCategory.get(id).stream().collect(Collectors.toSet());
		}
		if(answersForCategory.containsKey(id)) {
			answers = answersForCategory.get(id).stream().collect(Collectors.toSet());
		}
		return new Category(id, rs.getString("val"),  answers, questions);
	}

}
