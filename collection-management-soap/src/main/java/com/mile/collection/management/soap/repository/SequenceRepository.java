package com.mile.collection.management.soap.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceRepository {

	@Autowired
	@Qualifier(value = "mariaJdbcTemplate")
	JdbcTemplate template;
	private Long counter;

	public Long getCounter() {
		counter = template.queryForObject("SELECT * FROM quiz.seq", Long.class);
		return counter;
	}

	public void increment() {
		this.counter++;
		template.update("UPDATE quiz.seq SET counter = ?", this.counter);
	}
}
