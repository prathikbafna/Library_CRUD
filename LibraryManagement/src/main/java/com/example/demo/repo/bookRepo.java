package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Books;

public interface bookRepo extends JpaRepository<Books, String> {

}
