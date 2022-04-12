package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AddBookResponse;
import com.example.demo.entity.Books;
import com.example.demo.services.bookService;

@RestController
@CrossOrigin("*")
@RequestMapping("/books")
public class bookController {
	@Autowired
	bookService service;
	
	@PostMapping("/addBook")
	public ResponseEntity<AddBookResponse> addUser(@RequestBody Books book) {
		return  service.addBook(book);
			
	}
	
	@GetMapping("/getAllBooks")
	public List<Books> getAllBooks() {
		return service.getAllBooks();
	}
	
	
}

// store the return type in test cases
