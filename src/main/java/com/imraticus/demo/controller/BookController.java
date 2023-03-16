package com.imraticus.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imraticus.demo.model.Book;
import com.imraticus.demo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;

	@PostMapping("/add")
	public Book  createNewBook(@RequestBody Book book) {
		
		return bookService.createNewBook(book);
		
	}
	@PutMapping("/update/{id}")
	public Book  createNewBook(@RequestBody Book book,@PathVariable int  id) {
		
		return bookService.updateBook(book,id);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String   createNewBook(@PathVariable int  id) {
		
		bookService.deleteBook(id);
		
		return "successfully deleted book"+id;
		
	}
	
	@GetMapping("/all")
	public List<Book>   findAllBooks() {
		
		return bookService.getAllBooks();
		
	}
	
	
	@GetMapping("/find/{bookId}")
	public Book   findAllBookById(@PathVariable int bookId) {
		
		return bookService.getBookById(bookId);
		
	}
	
	@GetMapping("/findByAuthor/{author}")
	public Book   findAllBookById(@PathVariable String author) {
		
		return bookService.getBookByAuthor(author);
		
	}
	
	
	@GetMapping("/findByName/{name}")
	public Book   findAllBookByName(@PathVariable String name) {
		
		return bookService.getBookByName(name);
		
	}
}
