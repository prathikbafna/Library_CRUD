package com.example.demo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AddBookResponse;
import com.example.demo.entity.Books;
import com.example.demo.repo.bookRepo;
import com.example.demo.services.bookService;

@Service
public class bookServiceImpl implements bookService{
	
	@Autowired
	bookRepo repository;
	
	@Autowired
	AddBookResponse addBookResponse;
	
	@Override
	public boolean CheckUserAlreadyExists(String id) {
		return repository.existsById(id);
	}
	
	
	@Override
	public Optional<Books> getUserById(String id){
		if(CheckUserAlreadyExists(id)) {
			return repository.findById(id);
		}
		return null;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<AddBookResponse> addBook(Books book){
		if(CheckUserAlreadyExists(book.getBookId())) {
			addBookResponse.setBook(book);
			addBookResponse.setMsg("User already exists by Id:" + book.getBookId());
			
			return new ResponseEntity<AddBookResponse>(addBookResponse,HttpStatus.NOT_ACCEPTABLE);
		}
		else {
		repository.save(book);
		addBookResponse.setBook(book);
		addBookResponse.setMsg("User added by Id:" + book.getBookId());
		
		return new ResponseEntity<AddBookResponse>(addBookResponse,HttpStatus.ACCEPTED);}
	}


	@Override
	public List<Books> getAllBooks() {
		return repository.findAll();
	}
	
	
	
	
}
