package com.mile.collection.management.soap.endpoint;

import static org.mockito.Mockito.when;
import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.validPayload;
import static org.springframework.ws.test.server.ResponseMatchers.soapEnvelope;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import com.mile.collection.management.soap.quiz.Answer;
import com.mile.collection.management.soap.quiz.Question;
import com.mile.collection.management.soap.repository.AnswerRepository;
import com.mile.collection.management.soap.repository.CategoryRepository;
import com.mile.collection.management.soap.repository.QuestionRepository;
import com.mile.collection.management.soap.repository.SequenceRepository;
import com.mile.collection.management.soap.service.QuizService;

@WebServiceServerTest
class QuizEndPointTest {

	@Autowired 
	private MockWebServiceClient client;

	@MockBean
	private QuizService quizService;
	@MockBean
	private AnswerRepository answerRepository;
	@MockBean
	private QuestionRepository questionRepository;
	@MockBean
	private CategoryRepository categoryRepository;
	@MockBean
	private SequenceRepository sequenceRepository;

	@Test
	void testAnswersForQuestionRequest() throws IOException {
		Question q = new Question(10l, "XXX", new ArrayList<>(), new ArrayList<>());
		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(16l, "YYY", q, new ArrayList<>()));
		q.setAnswer(answers);

		when(questionRepository.findById(10)).thenReturn(q);
		when(answerRepository.answersBasedOnQuestion(q.getId())).thenReturn(answers);


		Source request = new StringSource(""
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:quiz=\"http://www.mile.com/collection/management/soap/Quiz\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <quiz:AnswersForQuestionRequest>\r\n"
				+ "         <quiz:id>10</quiz:id>\r\n"
				+ "      </quiz:AnswersForQuestionRequest>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>");

//		Source response = new StringSource("");

		client.sendRequest(withSoapEnvelope(request))
		.andExpect(noFault())
		.andExpect(validPayload(new ClassPathResource("/schemas/QuizSchema/quiz.xsd")))
	//	.andExpect(soapEnvelope(response))
		;
	}


	@Test
	void testUploadNewQuestion() throws IOException {
		Question q = new Question(10l, "XXX", new ArrayList<>(), new ArrayList<>());
		when(questionRepository.save(q)).thenReturn(1);
		Source request = new StringSource("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:quiz=\"http://www.mile.com/collection/management/soap/Quiz\">\r\n"
				+ "		   <soapenv:Header/>\r\n"
				+ "		   <soapenv:Body>\r\n"
				+ "		      <quiz:UploadQuestionRequest>\r\n"
				+ "		         <!--Optional:-->\r\n"
				+ "		         <quiz:val>?</quiz:val>\r\n"
				+ "		      </quiz:UploadQuestionRequest>\r\n"
				+ "		   </soapenv:Body>\r\n"
				+ "		</soapenv:Envelope>");		

		Source response = new StringSource("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "   <SOAP-ENV:Header/>\r\n"
				+ "   <SOAP-ENV:Body>\r\n"
				+ "      <ns2:AffectedRowsResponse xmlns:ns2=\"http://www.mile.com/collection/management/soap/Quiz\">\r\n"
				+ "         <ns2:result>0</ns2:result>\r\n"
				+ "      </ns2:AffectedRowsResponse>\r\n"
				+ "   </SOAP-ENV:Body>\r\n"
				+ "</SOAP-ENV:Envelope>");

		client.sendRequest(withSoapEnvelope(request))
		.andExpect(noFault())
		.andExpect(validPayload(new ClassPathResource("/schemas/QuizSchema/quiz.xsd")))
		.andExpect(soapEnvelope(response));

	}	


	@Test
	void testDeleteAnswerRequest() throws IOException {

		when(answerRepository.delete(12)).thenReturn(1);
		Source request = new StringSource("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:quiz=\"http://www.mile.com/collection/management/soap/Quiz\">\r\n"
				+ "		   <soapenv:Header/>\r\n"
				+ "		   <soapenv:Body>\r\n"
				+ "		      <quiz:DeleteAnswerRequest>\r\n"
				+ "		         <quiz:id>12</quiz:id>\r\n"
				+ "		      </quiz:DeleteAnswerRequest>\r\n"
				+ "		   </soapenv:Body>\r\n"
				+ "		</soapenv:Envelope>");		

		Source response = new StringSource("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "   <SOAP-ENV:Header/>\r\n"
				+ "   <SOAP-ENV:Body>\r\n"
				+ "      <ns2:AffectedRowsResponse xmlns:ns2=\"http://www.mile.com/collection/management/soap/Quiz\">\r\n"
				+ "         <ns2:result>0</ns2:result>\r\n"
				+ "      </ns2:AffectedRowsResponse>\r\n"
				+ "   </SOAP-ENV:Body>\r\n"
				+ "</SOAP-ENV:Envelope>");

		client.sendRequest(withSoapEnvelope(request))
		.andExpect(noFault())
		.andExpect(validPayload(new ClassPathResource("/schemas/QuizSchema/quiz.xsd")))
		.andExpect(soapEnvelope(response));
	}

	@Test
	void testGetCategoriesRequest() throws IOException {
		when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
		Source request = new StringSource("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:quiz=\"http://www.mile.com/collection/management/soap/Quiz\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <quiz:GetCategoriesRequest/>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>");

		Source response = new StringSource("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "   <SOAP-ENV:Header/>\r\n"
				+ "   <SOAP-ENV:Body>\r\n"
				+ "      <ns2:CategoriesResponse xmlns:ns2=\"http://www.mile.com/collection/management/soap/Quiz\"/>\r\n"
				+ "   </SOAP-ENV:Body>\r\n"
				+ "</SOAP-ENV:Envelope>");

		client.sendRequest(withSoapEnvelope(request))
		.andExpect(noFault())
		.andExpect(validPayload(new ClassPathResource("/schemas/QuizSchema/quiz.xsd")))
		.andExpect(soapEnvelope(response));
	}

}
