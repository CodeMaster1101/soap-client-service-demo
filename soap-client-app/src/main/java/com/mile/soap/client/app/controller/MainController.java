package com.mile.soap.client.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mile.soap.client.app.client.SoapClient;
import com.mile.soap.client.app.quiz.AffectedRowsResponse;
import com.mile.soap.client.app.quiz.AnswersResponse;
import com.mile.soap.client.app.quiz.CategoriesResponse;
import com.mile.soap.client.app.quiz.UploadQuestionRequest;

@RestController
public class MainController {

	@Autowired SoapClient soapClient;
	
	@GetMapping(path = "/getCategories")
	public CategoriesResponse getCategories() {
		return soapClient.getCategories(); 
	}
	@GetMapping(path = "/getAnswersForQuestion")
	public AnswersResponse getAbswersBasedOnQuestion(@RequestParam long id) {
		return soapClient.getAnswersForQuestion(id); 
	}
	@DeleteMapping(path = "/deleteAnswerById")
	public AffectedRowsResponse deleteAnswerById(@RequestParam long id) {
		return soapClient.deleteAnswerById(id); 
	}
	@PostMapping(path = "uploadQuestion")
	public AffectedRowsResponse uploadQuestion(@RequestBody UploadQuestionRequest q) {
		return soapClient.uploadNewQuestion(q);
	}
	
	
}
