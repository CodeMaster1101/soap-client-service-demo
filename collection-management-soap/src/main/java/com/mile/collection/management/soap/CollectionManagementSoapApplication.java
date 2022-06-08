package com.mile.collection.management.soap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.Transactional;

import com.mile.collection.management.soap.repository.AnswerRepository;
import com.mile.collection.management.soap.repository.CategoryRepository;
import com.mile.collection.management.soap.repository.QuestionRepository;

@SpringBootApplication @EnableJms
public class CollectionManagementSoapApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CollectionManagementSoapApplication.class, args);
		
		
	}
	@Autowired AnswerRepository answerRepository;
	@Autowired CategoryRepository categoryRepository;
	@Autowired QuestionRepository questionRepository;

	@Transactional
	@PostConstruct
	private void init() {

//		//QUESTIONS
//		List<Question> questions = new ArrayList<>();
//		Question q = new Question();
//		q.setId(null);
//		q.setValue("XXX");
//		questions.add(q);
//		Question q1 = new Question();
//		q1.setId(null);
//		q1.setValue("XXX");		
//		questions.add(q1);
//		
//		List<Question> questionsTWO = new ArrayList<>();
//		Question q2 = new Question();
//		q2.setId(null);
//		q2.setValue("XXX");
//		questionsTWO.add(q2);
//		Question q3 = new Question();
//		q3.setId(null);
//		q3.setValue("XXX");
//		questionsTWO.add(q3);
//		
//		questionRepository.saveAll(questions.stream().collect(Collectors.toList()));
//		questionRepository.saveAll(questionsTWO.stream().collect(Collectors.toList()));
////		
////		//CATEGORIES
//		List<Category> categories = new ArrayList<>();
//		categories.add(new Category(null, "ZZZ", new ArrayList<>(), questionsTWO));
//		categories.add(new Category(null, "ZZZ", new ArrayList<>(), questionsTWO));
//		categories.add(new Category(null, "ZZZ", new ArrayList<>(), questions));
//
//		List<Category> categoriesTWO = new ArrayList<>();
//		categories.add(new Category(null, "ZZZ_2", new ArrayList<>(), new ArrayList<>()));
//		categories.add(new Category(null, "ZZZ_2", new ArrayList<>(), new ArrayList<>()));
//		categories.add(new Category(null, "ZZZ_2", new ArrayList<>(), new ArrayList<>()));
//		
////		
//		//ANSWERS
//		Set<Answer> answers = new HashSet<>();
//		answers.add(new Answer(null, "YYY", q, categories));
//		answers.add(new Answer(null, "YYY", q1, categories));
//		answers.add(new Answer(null, "YYY", q1, categoriesTWO));
////
////		
//		for (Category category : categories) {
//			for (Answer answer : answers) {
//				category.getAnswer().add(answer);
//			}
//		}
//		for (Category category : categoriesTWO) {
//			for (Answer answer : answers) {
//				category.getAnswer().add(answer);
//			}
//		}
//		
//		answerRepository.saveAll(answers.stream().collect(Collectors.toList()));
//		categoryRepository.save(categories.get(0));
//		categoryRepository.save(categories.get(1));



	}


}
