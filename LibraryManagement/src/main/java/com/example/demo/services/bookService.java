package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.AddBookResponse;
import com.example.demo.entity.Books;

public interface bookService {
	ResponseEntity<AddBookResponse> addBook(Books book);

	boolean CheckUserAlreadyExists(String id);

	Optional<Books> getUserById(String id);

	List<Books> getAllBooks();
}
