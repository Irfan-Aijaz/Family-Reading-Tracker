package com.techelevator.controller;

import com.techelevator.dao.BookDao;
import com.techelevator.model.Book;
import com.techelevator.model.BookAlreadyExistsException;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@RestController
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
            bookDao.createBook(newBook.getTitle(),newBook.getAuthor(),newBook.getIsbn(), newBook.getPagesTotal());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/get_all_books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

//    @RequestMapping(value = "/retrieve_books_progress/{id}", method = RequestMethod.GET)
//<<<<<<< HEAD
//    public List<String> userBooks(@PathVariable Long id) {

//=======
//    public List<Book> userBooks(@PathVariable Long id) {
//>>>>>>> 9c230da5679e3af404b32b418a90d3ad9b0bcd92
//        return userDao.getUsernamesByFamilyId(id);
//
//    }

}
