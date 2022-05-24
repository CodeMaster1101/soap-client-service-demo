package com.mile.soap.client.app.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.mile.soap.client.app.quiz.AffectedRowsResponse;
import com.mile.soap.client.app.quiz.AnswersForQuestionRequest;
import com.mile.soap.client.app.quiz.AnswersResponse;
import com.mile.soap.client.app.quiz.CategoriesResponse;
import com.mile.soap.client.app.quiz.DeleteAnswerRequest;
import com.mile.soap.client.app.quiz.GetCategoriesRequest;
import com.mile.soap.client.app.quiz.UploadQuestionRequest;

@Service
public class SoapClient {
	
	@Autowired Jaxb2Marshaller marshaller;
	@Autowired WebServiceTemplate template;
	
	public CategoriesResponse getCategories() {
		return  (CategoriesResponse) template.marshalSendAndReceive("http://localhost:8083/ws", new GetCategoriesRequest());
	}

	public AnswersResponse getAnswersForQuestion(long id) {
		AnswersForQuestionRequest request = new AnswersForQuestionRequest();
		request.setId(id);
		return (AnswersResponse) template.marshalSendAndReceive("http://localhost:8083/ws", request);
	}

	public AffectedRowsResponse deleteAnswerById(long id) {
		DeleteAnswerRequest request = new DeleteAnswerRequest();
		request.setId(id);
		return (AffectedRowsResponse) template.marshalSendAndReceive("http://localhost:8083/ws", request);
	}

	public AffectedRowsResponse uploadNewQuestion(UploadQuestionRequest q) {
		return (AffectedRowsResponse) template.marshalSendAndReceive("http://localhost:8083/ws", q);
	}
	
}