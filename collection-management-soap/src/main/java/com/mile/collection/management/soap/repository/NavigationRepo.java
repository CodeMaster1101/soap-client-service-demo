package com.mile.collection.management.soap.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mile.collection.management.soap.dto.Answer_Category;
import com.mile.collection.management.soap.dto.Question_Category;
import com.mile.collection.management.soap.quiz.Answer;
import com.mile.collection.management.soap.quiz.Question;
import com.mile.collection.management.soap.repository.mapper.QuestionRowMapper;

@Repository
public class NavigationRepo {

	@Autowired 
	@Qualifier(value = "mariaJdbcTemplate")
	JdbcTemplate template;

	public MultiValuedMap<Long,Answer> getAllAnswersWithCategoryId(List<Answer_Category> answers) {
		MultiValuedMap<Long, Answer> answersForCategory =  new ArrayListValuedHashMap<Long,Answer>();
		for (int i = 0; i < answers.size(); i++) {
			answersForCategory.put(answers.get(i).getCategory_id() ,template.queryForObject("SELECT * FROM quiz.answer WHERE id = ?", new RowMapper<Answer>() {
				@Override
				public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new Answer(rs.getLong("id"), rs.getString("val"), new Question(), new ArrayList<>());
				}
			},answers.get(i).getAnswer_id()));
		}
		return answersForCategory;
	}
	public MultiValuedMap<Long, Question> getAllQuestionsWithCategoryId(List<Question_Category> questions) {
		MultiValuedMap<Long,Question> questionsForCategory =  new ArrayListValuedHashMap<Long, Question>();
		for (int i = 0; i < questions.size(); i++) {
			questionsForCategory.put(questions.get(i).getCategory_id(), template.queryForObject("SELECT * FROM quiz.question WHERE id = ?", new QuestionRowMapper(),
					questions.get(i).getQuestion_id()));  
		}
		return questionsForCategory;
	}

	public List<Answer_Category> mapKeysCategoryAnswer(){
		return template.query("SELECT * FROM answers_category ", 
				new RowMapper<Answer_Category>() {
					@Override
					public Answer_Category mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Answer_Category(rs.getLong("answer_id"), rs.getLong("category_id"));
					}
				});
	}

	public List<Question_Category> mapKeysCategoryQuestion(){
		return template.query("SELECT * FROM questions_category ", 
				new RowMapper<Question_Category>() {
					@Override
					public Question_Category mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Question_Category(rs.getLong("question_id"), rs.getLong("category_id"));
					}
				});
	}
}
