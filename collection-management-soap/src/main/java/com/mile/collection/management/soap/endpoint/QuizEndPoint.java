package com.mile.collection.management.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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

	@Autowired JmsTemplate jmsTemplate;
	@Autowired QuizService service;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "AnswersForQuestionRequest" )
	public AnswersResponse answerBasedOnQuestion(@RequestPayload AnswersForQuestionRequest request) {
		AnswersResponse response = new AnswersResponse(service.getAnswersForQuestion(request.getId()));
		try {
			sendMsgtoDestination(response.toString());		
			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "UploadQuestionRequest" )
	public AffectedRowsResponse uploadNewQuestion(@RequestPayload UploadQuestionRequest request) {
		AffectedRowsResponse response = new AffectedRowsResponse(service.uploadNewQuestion(request));
		try {
			sendMsgtoDestination(response.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "DeleteAnswerRequest" )
	public AffectedRowsResponse answerBasedOnQuestion(@RequestPayload DeleteAnswerRequest request) {
		AffectedRowsResponse response = new AffectedRowsResponse(service.removeAnswer(request.getId()));
		try {
			sendMsgtoDestination(response.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;

	}

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetCategoriesRequest")
	public CategoriesResponse answerBasedOnQuestion( @RequestPayload GetCategoriesRequest request) {
		CategoriesResponse response = new CategoriesResponse(service.getCategories());
		try {
			sendMsgtoDestination(response.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	private void sendMsgtoDestination(String response) {
		jmsTemplate.convertAndSend("quiz_queue",response);
	}

}
