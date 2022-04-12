package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "books")
public class Books {

	

	public Books() {}

		
	public Books(String bookId, String bookName, String bookAuthor, Boolean bookAvbl) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		BookAvbl = bookAvbl;
	}


	@Id
	@Column(name = "bookId")
	private String bookId;
	
	@Column(name = "bookName")
	private String bookName;
	
	@Column(name = "bookAuthor")
	private String bookAuthor;
	
	@Column(name = "bookAvbl")
	private Boolean BookAvbl;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "user_id")
	private Users usr;

}
