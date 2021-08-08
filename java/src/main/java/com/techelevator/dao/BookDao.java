package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.UserBook;

import java.util.List;

public interface BookDao {

    boolean createBook(String title, String author, String isbn, Long pagesTotal);

    Book findByIsbn(String isbn);

    List<Book> findAll();

    List<UserBook> findAllUserBooksInProgress(Long id);

    boolean deleteBook(String isbn);

}
