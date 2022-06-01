package com.mile.collection.management.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.mile.collection.management.soap.quiz.AffectedRowsResponse;
import com.mile.collection.management.soap.quiz.AnswersForQuestionRequest;
import com.mile.collection.management.soap.quiz.AnswersResponse;
import com.mile.collection.management.soap.quiz.CategoriesResponse;
import com.mile.collection.management.soap.quiz.DeleteAnswerRequest;
import com.mile.collection.management.soap.quiz.GetCategoriesRequest;
import com.mile.collection.management.soap.quiz.UploadQuestionRequest;
import com.mile.collection.management.soap.service.QuizService;

@Endpoint
public class QuizEndPoint {

	private final static String NAMESPACE = "http://www.mile.com/collection/management/soap/Quiz";

	@Autowired QuizService service;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "AnswersForQuestionRequest" )
	public AnswersResponse answerBasedOnQuestion(@RequestPayload AnswersForQuestionRequest request) {
		return new AnswersResponse(service.getAnswersForQuestion(request.getId()));
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "UploadQuestionRequest" )
	public AffectedRowsResponse uploadNewQuestion(@RequestPayload UploadQuestionRequest request) {
		return new AffectedRowsResponse(service.uploadNewQuestion(request));
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "DeleteAnswerRequest" )
	public AffectedRowsResponse answerBasedOnQuestion(@RequestPayload DeleteAnswerRequest request) {
		return new AffectedRowsResponse(service.removeAnswer(request.getId()));
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetCategoriesRequest")
	public CategoriesResponse answerBasedOnQuestion(@RequestPayload GetCategoriesRequest request) {
		return new CategoriesResponse(service.getCategories());
	}
	
}
