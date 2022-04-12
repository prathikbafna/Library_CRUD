package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AddUserResponse;
import com.example.demo.entity.Users;
import com.example.demo.entity.getAllUserResponse;
import com.example.demo.services.userService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")


public class userController {
	
	
	
	@Autowired
	userService service;
	
	@PostMapping("/addUser")
	public ResponseEntity<AddUserResponse> addUser(@RequestBody Users user) {
		return  service.addUser(user);
		
		
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<getAllUserResponse> getAllUser() {
		return service.getAllUsers();
	}
	
	@GetMapping("/getUser/{id}")
	public Optional<Users> getUserById(@PathVariable (value = "id") String id){
		return service.getUserById(id);
		
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUserById(@PathVariable (value = "id") String id){
		return service.deleteUserById(id);
		
	}
	
	@PutMapping("/updateUser/{id}")
	public String updateUser(@PathVariable(value = "id")String id , @RequestBody Users user) {
		return service.updateUser(id, user);
	}
	
	@PostMapping("/buybook")
	public String buyBook(@RequestBody Users usr) {
		return service.buyBook(usr);
	}
	
}

//store the return type in test cases