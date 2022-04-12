package com.example.demo.entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AddBookResponse {
	private String msg;
	private Books book;
}
