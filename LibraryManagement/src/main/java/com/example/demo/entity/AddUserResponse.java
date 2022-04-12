package com.example.demo.entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AddUserResponse {
	private String msg;
	private String id;
}
