package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;
@Repository
public interface userRepo extends JpaRepository<Users, String> {

}
