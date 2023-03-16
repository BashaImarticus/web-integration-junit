package com.imraticus.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.imraticus.demo.model.Book;
import com.imraticus.demo.repo.BookRepo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class IntegrtionDemoApplicationTests {

	static RestTemplate restTemplate;
	
	@LocalServerPort
	int port;
	
	String baseURL= "http://localhost";
	
	@Autowired
	BookRepo bookRepo;
	
	@BeforeAll
	static public void setUp() {
		
		restTemplate = new RestTemplate();
		
	}
	
	@BeforeEach
	public void initSetUpForURL() {
		
		baseURL = baseURL.concat(":").concat(""+port).concat("/book");
//		baseURL = baseURL+":"+port+"/book";
	}

	@Test
	@Sql(statements="delete from book",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void checkNewBookRecord() {
		
		Book book = new Book("C++","Ramya",800);
		
		Book newBook = restTemplate.postForObject(baseURL+"/add", book, Book.class);
		
		assertEquals("Ramya", newBook.getAuthor());
		
	}
	
	@Test
	@Sql(statements="delete from book",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements="insert into book (id,name,author,price) values(123,'Alphabets','Vamsi',5000)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements="delete from book",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void checkFindAll() {
		
		List<Book> stuList = restTemplate.getForObject(baseURL+"/all", List.class);
		
		assertEquals(1, stuList.size());
	}
	
	@Test
	@Sql(statements="delete from book",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements="insert into book (id,name,author,price) values(123,'Alphabets','Vamsi',5000)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	//@Sql(statements="delete from book",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void updateBook() {
		
		Book book = new Book("C++","Ramya",800);
		
		restTemplate.put(baseURL+"/update/123", book);
		
		assertEquals("C++", bookRepo.findById(123).get().getName());
		
	}
	
	
}
