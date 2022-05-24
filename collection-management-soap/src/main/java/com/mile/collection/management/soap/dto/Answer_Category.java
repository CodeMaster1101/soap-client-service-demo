package com.mile.collection.management.soap.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Answer_Category {
	private Long answer_id, category_id;
}
