package com.techelevator.dao;

import com.techelevator.model.Book;

import java.util.List;

public interface BookDao {

    boolean createBook(String title, String author, String isbn, Long pagesTotal);

    Book findByIsbn(String isbn);

    List<Book> findAll();

}
