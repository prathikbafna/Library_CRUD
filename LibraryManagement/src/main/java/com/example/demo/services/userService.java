package com.example.demo.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.AddUserResponse;
import com.example.demo.entity.Users;
import com.example.demo.entity.getAllUserResponse;

public interface userService {
	ResponseEntity<AddUserResponse> addUser(Users user);
	ResponseEntity<getAllUserResponse> getAllUsers();
	Optional<Users> getUserById(String id);
	String deleteUserById(String id);
	String updateUser(String id,Users user);
	boolean CheckUserAlreadyExists(String id);
	String buyBook(Users usr);
}
