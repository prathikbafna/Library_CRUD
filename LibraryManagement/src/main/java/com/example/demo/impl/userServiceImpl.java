package com.example.demo.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AddUserResponse;
import com.example.demo.entity.Books;
import com.example.demo.entity.Users;
import com.example.demo.entity.getAllUserResponse;
import com.example.demo.repo.bookRepo;
import com.example.demo.repo.userRepo;
import com.example.demo.services.userService;

@Service
public class userServiceImpl implements userService{
	
	@Autowired
	userRepo repository;
	
	@Autowired
	AddUserResponse addUserResponse;
	
	@Autowired
	getAllUserResponse getAllResponse;
	
	@Autowired
	bookRepo bookrepository;
	
	@Override
	public  ResponseEntity<AddUserResponse> addUser(Users user) {
		if(CheckUserAlreadyExists(user.getUserId())) {
			
			addUserResponse.setId(user.getUserId());
			addUserResponse.setMsg("User already exists by Id:" + user.getUserId());
			
			ResponseEntity<AddUserResponse> res = new ResponseEntity<AddUserResponse>(addUserResponse,HttpStatus.NOT_ACCEPTABLE);
			System.out.println(res);
			return res;
		}
		else {
		repository.save(user);
		addUserResponse.setId(user.getUserId());
		addUserResponse.setMsg("User added by Id:" + user.getUserId());
		
		ResponseEntity<AddUserResponse> res = new ResponseEntity<AddUserResponse>(addUserResponse,HttpStatus.ACCEPTED);
		System.out.println(res);
		return res;}
	}
	@Override
	public ResponseEntity<getAllUserResponse> getAllUsers() {
		// TODO Auto-generated method stub
		if (repository.findAll().isEmpty()) {
			getAllResponse.setMsg("No users to List");
			return new ResponseEntity<getAllUserResponse>(getAllResponse, HttpStatus.CONTINUE);
		}
		else {
		getAllResponse.setMsg("User List");
		getAllResponse.setUsers(repository.findAll());
		return new ResponseEntity<getAllUserResponse>(getAllResponse, HttpStatus.OK);
		
	}
	}
	
	@Override
	public Optional<Users> getUserById(String id){
		if(CheckUserAlreadyExists(id)) {
			return repository.findById(id);
		}
		return null;
		
	
		
	}
	@Override
	public String deleteUserById(String id) {
		if(CheckUserAlreadyExists(id)) {
		repository.deleteById(id);
		return "Successfully deleted";
		}
		else {
			return "User doesn't exist by Id " + id;
		}
	}
	
	
	@Override
	public String updateUser(String id ,Users user) {
		
		user.setUserId(id);
		repository.save(user);
		return "Success";
		
		
	}
	
	@Override
	public boolean CheckUserAlreadyExists(String id) {
		
		return repository.existsById(id);
		
	}
 
	@Override
	public String buyBook(Users usr) {
		Set<Books> myBooks = usr.getBks();
		//Users x = repository.getById(usr.getUserId());
		Optional<Users> x = getUserById(usr.getUserId());
		if(x.isPresent()) {
			for(Books item:myBooks) {
				x.get().bks.add(item);
			}
			repository.save(x.get());
		}
		
		return "Success";
	}


}


