package com.example.demo.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class getAllUserResponse {
	private List<Users> users;
	private String msg;

}
