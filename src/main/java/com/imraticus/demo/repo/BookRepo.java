package com.imraticus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imraticus.demo.model.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

	Book findByAuthor(String author);

	Book findByName(String name);

}
