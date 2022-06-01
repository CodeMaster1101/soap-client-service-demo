package com.mile.collection.management.soap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mile.collection.management.soap.quiz.Answer;
import com.mile.collection.management.soap.quiz.Category;
import com.mile.collection.management.soap.quiz.Question;
import com.mile.collection.management.soap.quiz.UploadQuestionRequest;
import com.mile.collection.management.soap.repository.AnswerRepository;
import com.mile.collection.management.soap.repository.CategoryRepository;
import com.mile.collection.management.soap.repository.QuestionRepository;

@Service
public class QuizService {

	@Autowired AnswerRepository answerRepository;
	@Autowired QuestionRepository questionRepository;
	@Autowired CategoryRepository categoryRepository;
	
	//DONE
	public List<Answer> getAnswersForQuestion(long id) {
		return answerRepository.answersBasedOnQuestion(id);
	}
	
	public int uploadNewQuestion(UploadQuestionRequest question) {
		return questionRepository.save(new Question(null, question.getVal(), new ArrayList<>(), new ArrayList<>()));	
	}
	
	public int removeAnswer(long id) {
		return answerRepository.delete(id);
	}
	
	public List<Category> getCategories() {
		return categoryRepository.findAll();			
	}
	
}
