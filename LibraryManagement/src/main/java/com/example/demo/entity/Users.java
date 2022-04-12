package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class Users {
	
	

	
	public Users(String userId, String userName, String userClg, String userContact, Set<Books> bks) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userClg = userClg;
		this.userContact = userContact;
		this.bks = bks;
	}
	
	
	public Users(String userId, String userName, String userClg, String userContact) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userClg = userClg;
		this.userContact = userContact;
	}


	public Users() {
	}


	@Id
	@Column(name="userId")
	private String userId;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "userClg")
	private String userClg;
	
	@Column(name = "userContact")
	private String userContact;
	
	@OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinTable(name = "user_books", joinColumns = @JoinColumn(name = "userId"),
	inverseJoinColumns = @JoinColumn(name  = "bookId"))
	public Set<Books> bks = new HashSet<Books>();
	
	
}


//add contructor to increase coverage