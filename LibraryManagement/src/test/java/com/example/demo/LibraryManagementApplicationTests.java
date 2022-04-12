package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.AddBookResponse;
import com.example.demo.entity.AddUserResponse;
import com.example.demo.entity.Books;
import com.example.demo.entity.Users;
import com.example.demo.entity.getAllUserResponse;
import com.example.demo.repo.bookRepo;
import com.example.demo.repo.userRepo;
import com.example.demo.services.bookService;
import com.example.demo.services.userService;



@SpringBootTest
class LibraryManagementApplicationTests {
	
	@Autowired
	userRepo repository;
	
	@Autowired
	userService u_service;
	
	@Autowired
	bookService b_service;
	
	
	@MockBean
	userRepo u_repo;
	
//	@MockBean
//	bookRepo b_repo;
	
	
	@MockBean
	AddUserResponse addUserResponse;
	
	@MockBean
	AddBookResponse addBookResponse;
	
	@MockBean
	getAllUserResponse getAllResponse;
	
	@MockBean
	bookRepo bookrepository;
	
	
	public Users getUser() {
		Users usr = new Users();
		usr.setUserId("UT12");
		usr.setUserClg("PES");
		usr.setUserName("Prathik");
		usr.setUserContact("9148489627");
		return usr;
	}
 
	@Test
	public void addUserTest() {
		Users usr = getUser();
		System.out.println(usr);
		Mockito.when(u_service.CheckUserAlreadyExists(usr.getUserId())).thenReturn(false);
		ResponseEntity<?> res = u_service.addUser(usr);
		System.out.println(res);
		assertEquals(res.getStatusCode(),HttpStatus.ACCEPTED);
		
		//Negative case
		Users usr1 = getUser();
		when(u_service.CheckUserAlreadyExists(usr1.getUserId())).thenReturn(true);
		ResponseEntity<AddUserResponse> res1 = u_service.addUser(usr);
		assertEquals(res1.getStatusCode(),HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@Test
	public void deleteUserTest() {
		
		when(u_service.CheckUserAlreadyExists("UT123")).thenReturn(true);
		String res = u_service.deleteUserById("UT123");
		System.out.println(res);
		assertEquals(res, "Successfully deleted");
		
		//Negative case
		when(u_service.CheckUserAlreadyExists("UT123")).thenReturn(false);
		String res1 = u_service.deleteUserById("UT123");
		System.out.println(res1);
		assertNotEquals(res1, "Successfully deleted");
		
		
		
		
	}
	
	@Test
	public void getUsersTest() {
		Users usr = getUser();
		List<Users> l = new ArrayList<Users>();
		l.add(usr);
		when(u_repo.findAll()).thenReturn(l);
		ResponseEntity<?> res = u_service.getAllUsers();
		System.out.println(res);
		assertEquals(res.getStatusCode(),HttpStatus.OK);
		
		
		//Negative case
		List<Users> l2 = new ArrayList<Users>();
		when(u_repo.findAll()).thenReturn(l2);
		ResponseEntity<?> res2 = u_service.getAllUsers();
		System.out.println(res2);
		assertEquals(res2.getStatusCode(),HttpStatus.CONTINUE);
		
	}
	
	@Test
	public void getUserByIdTest() {
		when(u_service.CheckUserAlreadyExists("UT123")).thenReturn(true);
		Optional<Users> res = u_service.getUserById("UT123");
		assertNotNull(res);
		
		//Negative case
		when(u_service.CheckUserAlreadyExists("UT123")).thenReturn(false);
		Optional<Users> res1 = u_service.getUserById("UT123");
		assertNull(res1);
		
	}
	
	@Test
	public void testUpdateUser() {
		Users x = getUser();;
		x.setUserClg("VIT");
		String res = u_service.updateUser("UT12", x);
		assertEquals("Success",res);
	
	}
	
	
	@Test
	public void testbuybook() {
		
		Users usr = new Users("UT12","PES","raj","9148489627");
		u_service.addUser(usr);
		
		Books bk = new Books();
		bk.setBookAuthor("Ravi Malik");
		bk.setBookId("TB12345");
		bk.setBookName("2 People");
		bk.setBookAvbl(true);
		
		Set<Books> myBooks = new HashSet<Books>();
		myBooks.add(bk);
		Users usr1 = new Users("UT12","PES","raj","9148489627",myBooks);
		when(u_service.CheckUserAlreadyExists("UT12")).thenReturn(true);
		Optional<Users> res = u_service.getUserById("UT12");
		when(u_service.getUserById("UT12")).thenReturn(res);
		String res1 = u_service.buyBook(usr1);
		assertEquals(res1,"Success");
		
		
		
	}
	
	public Books getBook() {
		Books bk = new Books();
		bk.setBookAuthor("Ravi Malik");
		bk.setBookId("TB123");
		bk.setBookName("2 People");
		bk.setBookAvbl(true);
		return bk;
	}
	
	
	@Test
	public void addBookTest() {
		Books bks = getBook();
		System.out.println(bks);
		Mockito.when(b_service.CheckUserAlreadyExists(bks.getBookId())).thenReturn(false);
		ResponseEntity<?> res = b_service.addBook(bks);
		System.out.println(res);
		assertEquals(res.getStatusCode(),HttpStatus.ACCEPTED);
		
		//Negative case
		Books bks1 = getBook();
		when(b_service.CheckUserAlreadyExists(bks1.getBookId())).thenReturn(true);
		ResponseEntity<?> res1 = b_service.addBook(bks);
		assertEquals(res1.getStatusCode(),HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	
	@Test
	public void getBookByIdTest() {
		when(b_service.CheckUserAlreadyExists("TB123")).thenReturn(true);
		Optional<?> res = b_service.getUserById("TB123");
		assertNotNull(res);
		
		//Negative case
		when(b_service.CheckUserAlreadyExists("TB123")).thenReturn(false);
		Optional<?> res1 = b_service.getUserById("TB123");
		assertNull(res1);
		
	}
	
	
	@Test
	public void getBooksTest() {
		
		List<?> res = b_service.getAllBooks();
		System.out.println(res);
		assertNotNull(res);
		
	}
	
	
//	@Test
//	@Order(1)
//	public void testCreateUser() {
//		Users usr = new Users();
//		usr.setUserId("UT123");
//		usr.setUserClg("PES");
//		usr.setUserName("Prathik");
//		usr.setUserContact("9148489627");
//		repository.save(usr);
//		assertNotNull(repository.findById(usr.getUserId()));
//		
//	}
//	
//
//	
//	
//	@Test
//	@Order(2)
//	public void testGetAllUser() {
////		List<Users> U = repository.findAll();
////		assertThat(U).size().isGreaterThan(0);
//		List<Users> U = u_service.getAllUsers().getBody().getUsers();
//		assertThat(U).size().isGreaterThan(0);
//		
//	}
//	
//	@Test
//	@Order(3)
//	public void testGetSingleUser() {
//		Users u = repository.findById("UT123").get();
//		assertEquals("Prathik",u.getUserName());
//		
//	}
//	
//	@Test
//	@Order(4)
//	public void testUpdateUser() {
//		Users u = repository.findById("UT123").get();
//		u.setUserClg("VIT");
//		repository.save(u);
//		assertNotEquals("PES",repository.findById("UT123").get().getUserClg());
//		
//	}
//	
//	@Test
//	@Order(5)
//	public void testDeleteUser() {
//		repository.deleteById("UT123");
//		assertThat(repository.existsById("UT123")).isFalse();
//	}
//	
//	
//	
//	@Test
//	@Order(6)
//	public void testCreateBook() {
//		Books bk = new Books();
//		bk.setBookAuthor("Ravi Malik");
//		bk.setBookId("TB123");
//		bk.setBookName("2 People");
//		bk.setBookAvbl(true);
//		b_repository.save(bk);
//		assertNotNull(repository.findById(bk.getBookId()));
//		
//	}
//	
//	
//	@Test
//	@Order(7)
//	public void testGetAllBook() {
//		List<Books> U = b_repository.findAll();
//		assertThat(U).size().isGreaterThan(0);
//		
//	}
//	
//	@Test
//	@Order(8)
//	public void testGetSingleBook() {
//		Books u = b_repository.findById("TB123").get();
//		assertEquals("2 People",u.getBookName());
//		
//	}
//	
//	@Test
//	@Order(9)
//	public void testUpdateBook() {
//		Books u = b_service.getUserById("TB123").get();
//		u.setBookAuthor("PB Jain");
//		b_repository.save(u);
//		assertNotEquals("Ravi Malik",b_repository.findById("TB123").get().getBookAuthor());
//		
//	}
//	
//	@Test
//	@Order(10)
//	public void testDeleteBook() {
//		b_repository.deleteById("TB123");
//		assertThat(b_repository.existsById("TB123")).isFalse();
//	}
//	
//	@Test
//	@Order(11)
//	public void testCreateDuplicate() {
//		Users usr = new Users();
//		usr.setUserId("UT123");
//		usr.setUserClg("PES");
//		usr.setUserName("Prathik");
//		usr.setUserContact("9148489627");
//		assertThat(repository.save(usr)).isNotNull();
//		
//	}
//	
//	@Test
//	@Order(12)
//	public void createBookConstr() {
//		Books bk = new Books("N123","Name","Author",true);
//		assertEquals(bk.getBookAuthor(),"Author");
//	}
//	
//	@Test()
//	@Order(13)
//	public void checkUserExists() {
//		assertThat(u_service.CheckUserAlreadyExists("UT123")).isTrue();
//	}
//	
//	@Test()
//	@Order(14)
//	public void testGetAllUserInbuilt() {
//		List<Users> U = repository.findAll();
//		assertThat(U).size().isGreaterThan(0);
//		}
//	
//	@Test()
//	@Order(15)
//	public void name() {
//		Users usr = new Users();
//		usr.setUserId("UT1234");
//		usr.setUserClg("PES");
//		usr.setUserName("Rajesh");
//		usr.setUserContact("9148489627");
//		u_service.addUser(usr);
//		
//		Users u = repository.findById("UT1234").get();
//		u.setUserClg("VIT");
//		u_service.updateUser(u.getUserId(), u);
//		
//		
//		
//		assertNotEquals("PES",u_service.getUserById("UT1234").get().getUserClg());
//	}
//	
//	
//	@Test
//	@Order(16)
//	public void testDeleteUserIn() {
//		u_service.deleteUserById("UT1234");
//		assertThat(repository.existsById("UT1234")).isFalse();
//	}
//	
//	@Test()
//	@Order(17)
//	public void checkbookExists() {
//		Books bk = new Books();
//		bk.setBookAuthor("Ravi Malik");
//		bk.setBookId("TB123");
//		bk.setBookName("2 People");
//		bk.setBookAvbl(true);
//		b_service.addBook(bk);
//		
//		assertThat(b_service.CheckUserAlreadyExists("TB123")).isTrue();
//	}
//	
//	
//	
//	@Test
//	@Order(18)
//	public void testbuybook() {
//		
//		Users usr = new Users("UT1234","PES","raj","9148489627");
//		u_service.addUser(usr);
//		
//		Books bk = new Books();
//		bk.setBookAuthor("Ravi Malik");
//		bk.setBookId("TB12345");
//		bk.setBookName("2 People");
//		bk.setBookAvbl(true);
//		
//		Set<Books> myBooks = new HashSet<Books>();
//		myBooks.add(bk);
//		Users usr1 = new Users("UT1234","PES","raj","9148489627",myBooks);
//		
//		String res = u_controller.buyBook(usr1);
//		assertEquals(res,"Success");
//		
//		
//		
//	}
//	
//	
//	@Test
//	@Order(19)
//	public void testReturnType() {
//		Users usr = new Users("UT1235","PES","raj","9148489627");
//		ResponseEntity<AddUserResponse> x = u_controller.addUser(usr);
//		ResponseEntity<getAllUserResponse> allUsers = u_controller.getAllUser();
//		Optional<Users> u1 = u_controller.getUserById("UT1235");
//		Users usr2 = new Users("UT1235","BBB","rajesh","9148489627");
//		String uu = u_controller.updateUser("UT1235", usr2);
//		String du = u_controller.deleteUserById("UT1235");
//		
//		
//		
//		
//		Books bk = new Books();
//		bk.setBookAuthor("Ravi Malik");
//		bk.setBookId("TB12345");
//		bk.setBookName("2 People");
//		bk.setBookAvbl(true);
//		b_controller.addUser(bk);
//		b_controller.getAllBooks();
//
//		
//	}
	
	
	
}
