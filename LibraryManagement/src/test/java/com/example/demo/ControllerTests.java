package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controller.userController;
import com.example.demo.entity.Users;
import com.example.demo.repo.userRepo;
import com.example.demo.services.userService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan(basePackages = "com.example.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerTests.class})
public class ControllerTests {
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	userService u_service;
	
	@Mock
	userRepo u_repo;
	
	@InjectMocks
	userController u_controller;
	
	@BeforeEach
	private void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(u_controller).build();
	}
	
	public Users getUser() {
		Users usr = new Users();
		usr.setUserId("UT12");
		usr.setUserClg("PES");
		usr.setUserName("Prathik");
		usr.setUserContact("9148489627");
		return usr;
	}
	
	@Test
	public void addUserTest() throws Exception {
		
		Users usr = getUser();
		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(usr);
		
		when(u_service.CheckUserAlreadyExists(usr.getUserId())).thenReturn(false);
		
		this.mockMvc.perform(post("/user/addUser")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isAccepted())
		.andDo(print());
		
		
		when(u_service.CheckUserAlreadyExists(usr.getUserId())).thenReturn(true);
		
//		this.mockMvc.perform(post("/user/addUser")
//				.content(jsonBody)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isNotAcceptable()
//		.andDo(print());
		 
		
	}
	
	@Test
	public void getUserById() throws Exception {
		
		Optional<Users> usr = Optional.of(new Users());
		usr.get().setUserId("UT12");
		usr.get().setUserClg("PES");
		usr.get().setUserName("Prathik");
		usr.get().setUserContact("9148489627");
		when(u_service.CheckUserAlreadyExists(usr.get().getUserId())).thenReturn(true);
		when(u_repo.findById(usr.get().getUserId())).thenReturn(usr);
		this.mockMvc.perform(get("/user/getUser/{id",usr.get().getUserId()))
		.andDo(print());
		
		
	}
	
	
}
