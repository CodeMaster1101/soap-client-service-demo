package com.mile.collection.management.soap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mile.collection.management.soap.bottomUp.Answer;
import com.mile.collection.management.soap.bottomUp.Category;
import com.mile.collection.management.soap.bottomUp.Question;
import com.mile.collection.management.soap.repository.mapper.CategoryMapper;

import lombok.Getter;

@Repository
@Getter
public class CategoryRepository {

	@Autowired SequenceRepository sequenceRepository;
	@Autowired AnswerRepository answerRepository;
	@Autowired QuestionRepository questionRepository;
	@Autowired NavigationRepo navigationRepo;
	@Autowired 
	@Qualifier(value = "mariaJdbcTemplate")
	JdbcTemplate template;

	@Transactional
	public int save(Category o) {

		if(o.getId() == null) {
			o.setId(sequenceRepository.getCounter());
			sequenceRepository.increment();
		}
		
		int insertFirst = template.update("INSERT INTO quiz.category (id, val) values(?,?)", new Object[] {
				o.getId(), o.getValue()
		});
		
		if(o.getQuestions().size() > 0) {
			Question[] questions = o.getQuestions().toArray(new Question[o.getQuestions().size()]);
			for(int i = 0; i < o.getQuestions().size();i++) {
				template.update("INSERT INTO quiz.questions_category (category_id,question_id) values(?,?)", 
						o.getId(), questions[i].getId());
			}
		}
		
		if(o.getAnswers().size() > 0) {
			Answer[] answers = o.getAnswers().toArray(new Answer[o.getAnswers().size()]);
			for(int i = 0; i < o.getAnswers().size();i++) {
				template.update("INSERT INTO quiz.answers_category (category_id,answer_id) values(?,?)", 
						o.getId(), answers[i].getId());
			}
		}	
		
		return insertFirst;
	}
	
	public int update(Category o) {
		 return template.update("UPDATE quiz.category SET id = ?, val = ?", new Object[] {
				 findQuestionByValue(o.getValue()).getId(), o.getValue()});
	}
	
	public Category findQuestionByValue(String value) {
		return template.queryForObject("SELECT * FROM quiz.category WHERE val = ?", Category.class, value);
	}
	
	public List<Category> findAll(){
		return template.query("SELECT * FROM quiz.category", 
				new CategoryMapper(navigationRepo.getAllAnswersWithCategoryId(navigationRepo.mapKeysCategoryAnswer()), 
						navigationRepo.getAllQuestionsWithCategoryId(navigationRepo.mapKeysCategoryQuestion())));
	}


	
}
