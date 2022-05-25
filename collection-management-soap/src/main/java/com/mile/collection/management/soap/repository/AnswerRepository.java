package com.mile.collection.management.soap.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mile.collection.management.soap.quiz.Answer;
import com.mile.collection.management.soap.quiz.Category;
import com.mile.collection.management.soap.repository.mapper.AnswerRowMapper;

@Repository
public class AnswerRepository {

	@Autowired 
	@Qualifier(value = "mariaJdbcTemplate")
	JdbcTemplate template;

	@Autowired QuestionRepository questionRepository;
	@Autowired SequenceRepository sequenceRepository;

	@Transactional
	public int save(Answer o) {
		if(o.getId() == null) {
			o.setId(sequenceRepository.getCounter());
			sequenceRepository.increment();
		}
		//idOverlapping(o.getId(), null, counter); -> add 2nd argument as findById(sequence);
		if(o.getCategory().size() > 0) {
			Category[] categories = o.getCategory().toArray(new Category[o.getCategory().size()]);
			for(int i = 0; i < o.getCategory().size();i++) {
				template.update("INSERT INTO quiz.answers_category (answer_id,category_id) values(?,?)", 
						o.getId(), categories[i].getId());
			}
		}
		return template.update("INSERT INTO quiz.answer (id, val, question_id) values(?,?,?)", new Object[] {
				o.getId(), o.getValue(),  o.getQuestion().getId()
		});
	}

	@Transactional
	public int[] saveAll(List<Answer> answers) {
		return template.batchUpdate("INSERT INTO quiz.answer (id, val, question_id) VALUES(?,?,?)", 
				new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				if(answers.get(i).getId() != null)
					ps.setLong(1, answers.get(i).getId());
				else {
					long id = sequenceRepository.getCounter();
					answers.get(i).setId(id);
					ps.setLong(1, id);
					sequenceRepository.increment();
				}
				ps.setString(2, answers.get(i).getValue());
				ps.setLong(3, answers.get(i).getQuestion().getId());	
			}

			@Override
			public int getBatchSize() {
				return answers.size();
			}
		});
	}

	public Answer findAnswerByValue(String value) {
		return template.queryForObject("SELECT * FROM quiz.answer WHERE val = ?", Answer.class, value);
	}
	
	public int update(Answer o) {
		return template.update("UPDATE quiz.answer SET id = ?, val = ?, question_id = ?", new Object[] {
				findAnswerByValue(o.getValue()).getId(), o.getValue(), o.getQuestion().getId()});
	}
	
	public List<Answer> answersBasedOnQuestion(long id){
		return template.query("SELECT * FROM quiz.answer WHERE question_id=?", new AnswerRowMapper(new HashSet<>(), questionRepository.findById(id)), id);
	}
	
	public Answer findAnswerById(long id) {
		return template.queryForObject("SELECT * FROM quiz.answer WHERE id=?", new RowMapper<Answer>() {

			@Override
			public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Answer a = new Answer();
				a.setId(rs.getLong("id"));
				a.setValue(rs.getString("val"));
				a.setQuestion(questionRepository.findById(rs.getLong("question_id")));
				return a;
			}

		}, id);
	}

	@Transactional
	public int delete(long id) {
		template.update("DELETE FROM quiz.answers_category WHERE answer_id = ?", id);
		return template.update("Delete FROM quiz.answer WHERE id = ?", id);
	}
	public List<Answer> findAll() {
		return template.query("SELECT * FROM quiz.answer", new RowMapper<Answer>() {

			@Override
			public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Answer a = new Answer();
				a.setId(rs.getLong("id"));
				a.setValue(rs.getString("val"));
				return a;
			}

		});
	}

	//	private long idOverlapping(Long id, Long id2, long sequence_counter) {
	//		if(id == id2) {
	//			sequence_counter++;
	//			return idOverlapping(id, id2, sequence_counter);
	//		}
	//		return sequence_counter;
	//	}

}
