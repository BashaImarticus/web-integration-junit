package com.imraticus.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imraticus.demo.model.Book;
import com.imraticus.demo.repo.BookRepo;

@Service
public class BookService {

	@Autowired
	BookRepo bookRepo;

	public Book createNewBook(Book book) {
		
		return bookRepo.save(book);
	}

	public Book updateBook(Book book,int id) {
	
		Book oldBook = bookRepo.findById(id).get();
		if(oldBook != null) {
			book.setId(id);
			return bookRepo.save(book);	
		}
		else {
			return new Book();
		}
		
		
	
	}

	public void deleteBook(int id) {
	
		bookRepo.deleteById(id);
	}

	public List<Book> getAllBooks() {
	
		return bookRepo.findAll();
	
	}

	public Book getBookById(int bookId) {
		
		return bookRepo.findById(bookId).orElse(new Book());
	}

	public Book getBookByName(String name) {
	
		return bookRepo.findByName(name);
	}

	public Book getBookByAuthor(String author) {
		return bookRepo.findByAuthor(author);
	}
}
