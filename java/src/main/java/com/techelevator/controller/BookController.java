package com.techelevator.controller;

import com.techelevator.dao.BookDao;
import com.techelevator.model.Book;
import com.techelevator.model.BookAlreadyExistsException;
import com.techelevator.model.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
public class BookController {

    private BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create_book", method = RequestMethod.POST)
    public void createBook(@Valid @RequestBody Book newBook) {
        try {
            Book book = bookDao.findByIsbn(newBook.getIsbn());
            throw new BookAlreadyExistsException();
        } catch (BookAlreadyExistsException e) {
            bookDao.createBook(newBook.getTitle(),newBook.getAuthor(),newBook.getIsbn());
        }
    }
}
