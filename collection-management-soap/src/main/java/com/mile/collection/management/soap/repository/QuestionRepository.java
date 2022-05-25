package com.mile.collection.management.soap.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mile.collection.management.soap.quiz.Category;
import com.mile.collection.management.soap.quiz.Question;
import com.mile.collection.management.soap.repository.mapper.QuestionRowMapper;

import lombok.Getter;

@Repository
@Getter
public class QuestionRepository {

	@Autowired SequenceRepository sequenceRepository;

	@Autowired 
	@Qualifier(value = "mariaJdbcTemplate")
	JdbcTemplate template;

	@Transactional
	public int save(Question o) {
		
		if(o.getId() == null) {
			o.setId(sequenceRepository.getCounter());
			sequenceRepository.increment();
		}
		
		int result = template.update("INSERT INTO quiz.question (id, val) values(?,?)", new Object[] {
				o.getId(), o.getValue()
		});
		
		if(o.getCategory().size() > 0) {
			Category[] categories = o.getCategory().toArray(new Category[o.getCategory().size()]);
			for(int i = 0; i < o.getCategory().size();i++) {
				template.update("INSERT INTO quiz.questions_category (question_id,category_id) values(?,?)", 
						o.getId(), categories[i].getId());
			}
		}
		return result;
		
	}

	@Transactional
	public int[] saveAll(List<Question> questions) {
		
		return template.batchUpdate("INSERT INTO quiz.question (id, val) VALUES(?,?)", 
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						if(questions.get(i).getId() != null)
							ps.setLong(1, questions.get(i).getId());
						else {
							long id = sequenceRepository.getCounter();
							questions.get(i).setId(id);
							ps.setLong(1, id);
							sequenceRepository.increment();
						}
						ps.setString(2, questions.get(i).getValue());	
					}
					
					@Override
					public int getBatchSize() {
						return questions.size();
					}
				});
	}
	
	public Question findQuestionByValue(String value) {
		return template.queryForObject("SELECT * FROM quiz.question WHERE val = ?", Question.class, value);
	}
	
	public int update(Question o) {
		 return template.update("UPDATE quiz.question SET id = ?, val = ?", new Object[] {
				 findQuestionByValue(o.getValue()).getId(), o.getValue()});
	}
	
	public Question findById(long id) {
		return template.queryForObject("SELECT * FROM quiz.question WHERE id=?", new QuestionRowMapper(), id);
	}

	//PRIORITISED = FALSE
	@Transactional
	public int delete(long id) {
		return template.update("Delete FROM quiz.question WHERE id = ?", id);
	}

	public List<Question> findAll() {
		return template.query("SELECT * FROM question", new QuestionRowMapper());
	}

	public List<Question> findQuestionsByCategory() {
		return null;
	}
}
