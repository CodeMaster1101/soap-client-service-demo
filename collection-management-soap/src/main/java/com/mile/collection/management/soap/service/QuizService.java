package com.mile.collection.management.soap.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mile.collection.management.soap.bottomUp.Answer;
import com.mile.collection.management.soap.bottomUp.Category;
import com.mile.collection.management.soap.bottomUp.Question;
import com.mile.collection.management.soap.quiz.request.UploadQuestionRequest;
import com.mile.collection.management.soap.repository.AnswerRepository;
import com.mile.collection.management.soap.repository.CategoryRepository;
import com.mile.collection.management.soap.repository.QuestionRepository;

@Service
public class QuizService {

	@Autowired AnswerRepository answerRepository;
	@Autowired QuestionRepository questionRepository;
	@Autowired CategoryRepository categoryRepository;
	
	//DONE
	public Set<Answer> getAnswersForQuestion(long id) {
		return answerRepository.answersBasedOnQuestion(id).stream().collect(Collectors.toSet());
	}

	
	public int uploadNewQuestion(UploadQuestionRequest question) {
		return questionRepository.save(new Question(null, question.getVal(), new HashSet<>(), new HashSet<>()));
		
	}
	
	public int removeAnswer(long id) {
		return answerRepository.delete(id);
	}
	
	public List<Category> getCategories() {
		return categoryRepository.findAll();			
	}
	
}
