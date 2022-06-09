package com.mile.collection.management.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.mile.collection.management.soap.producer.JmsProducer;
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

	@Autowired JmsProducer producer;
	@Autowired QuizService service;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "AnswersForQuestionRequest" )
	public AnswersResponse answerBasedOnQuestion(@RequestPayload AnswersForQuestionRequest request) {
		AnswersResponse response = new AnswersResponse(service.getAnswersForQuestion(request.getId()));
		producer.sendMsgtoDestination(response);
		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "UploadQuestionRequest" )
	public AffectedRowsResponse uploadNewQuestion(@RequestPayload UploadQuestionRequest request) {
		AffectedRowsResponse response = new AffectedRowsResponse(service.uploadNewQuestion(request));
		producer.sendMsgtoDestination(response);
		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "DeleteAnswerRequest" )
	public AffectedRowsResponse answerBasedOnQuestion(@RequestPayload DeleteAnswerRequest request) {
		AffectedRowsResponse response = new AffectedRowsResponse(service.removeAnswer(request.getId()));
		producer.sendMsgtoDestination(response);
		return response;

	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetCategoriesRequest")
	public CategoriesResponse answerBasedOnQuestion( @RequestPayload GetCategoriesRequest request) {
		CategoriesResponse response = new CategoriesResponse(service.getCategories());
		producer.sendMsgtoDestination(response);
		return response;
	}

}
