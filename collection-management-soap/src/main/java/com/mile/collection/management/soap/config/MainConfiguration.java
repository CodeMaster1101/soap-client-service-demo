package com.mile.collection.management.soap.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MainConfiguration {


	//mariaDb connectivity

	@Bean(name = "mariaDb")
	@ConfigurationProperties(prefix = "spring.ds-maria")
	public DataSource mariaDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mariaJdbcTemplate", autowireCandidate = true)
	JdbcTemplate template(@Qualifier(value = "mariaDb") DataSource mariaDb) {
		return new JdbcTemplate(mariaDb);
	}

}
