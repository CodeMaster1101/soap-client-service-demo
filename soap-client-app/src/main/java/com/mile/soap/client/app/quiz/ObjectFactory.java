//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.24 at 10:22:47 PM CEST 
//


package com.mile.soap.client.app.quiz;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mile.soap.client.app.quiz package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Answer_QNAME = new QName("http://www.mile.com/collection/management/soap/Quiz", "answer");
    private final static QName _Question_QNAME = new QName("http://www.mile.com/collection/management/soap/Quiz", "question");
    private final static QName _Category_QNAME = new QName("http://www.mile.com/collection/management/soap/Quiz", "category");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mile.soap.client.app.quiz
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link DeleteAnswerRequest }
     * 
     */
    public DeleteAnswerRequest createDeleteAnswerRequest() {
        return new DeleteAnswerRequest();
    }

    /**
     * Create an instance of {@link AnswersForQuestionRequest }
     * 
     */
    public AnswersForQuestionRequest createAnswersForQuestionRequest() {
        return new AnswersForQuestionRequest();
    }

    /**
     * Create an instance of {@link UploadQuestionRequest }
     * 
     */
    public UploadQuestionRequest createUploadQuestionRequest() {
        return new UploadQuestionRequest();
    }

    /**
     * Create an instance of {@link GetCategoriesRequest }
     * 
     */
    public GetCategoriesRequest createGetCategoriesRequest() {
        return new GetCategoriesRequest();
    }

    /**
     * Create an instance of {@link AnswersResponse }
     * 
     */
    public AnswersResponse createAnswersResponse() {
        return new AnswersResponse();
    }

    /**
     * Create an instance of {@link AffectedRowsResponse }
     * 
     */
    public AffectedRowsResponse createAffectedRowsResponse() {
        return new AffectedRowsResponse();
    }

    /**
     * Create an instance of {@link CategoriesResponse }
     * 
     */
    public CategoriesResponse createCategoriesResponse() {
        return new CategoriesResponse();
    }

    /**
     * Create an instance of {@link QuestionResponse }
     * 
     */
    public QuestionResponse createQuestionResponse() {
        return new QuestionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.mile.com/collection/management/soap/Quiz", name = "answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.mile.com/collection/management/soap/Quiz", name = "question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Category }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.mile.com/collection/management/soap/Quiz", name = "category")
    public JAXBElement<Category> createCategory(Category value) {
        return new JAXBElement<Category>(_Category_QNAME, Category.class, null, value);
    }

}