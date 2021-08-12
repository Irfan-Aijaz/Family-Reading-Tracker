package com.techelevator.controller;

import com.techelevator.dao.BookDao;
import com.techelevator.model.Book;
import com.techelevator.model.BookAlreadyExistsException;
import com.techelevator.model.UserBook;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value = "/get_all_books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }


    @RequestMapping(value = "/delete_book", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String isbn) {

    }


    @RequestMapping(value = "/retrieve_books_in_progress/{user_id}", method = RequestMethod.GET)
    public List<UserBook> userBooksInProgress(@PathVariable("user_id") Long userId) {
        return bookDao.findAllUserBooksInProgress(userId);
    }

    @RequestMapping(value = "/retrieve_books_completed/{user_id}", method = RequestMethod.GET)
    public List<UserBook> userBooksCompleted(@PathVariable("user_id") Long userId) {
        return bookDao.findAllUserBooksCompleted(userId);
    }

    @RequestMapping(value = "/restart_book", method = RequestMethod.PUT)
    public boolean restartBook(@RequestBody UserBook userBook) {
        return bookDao.restartBook(userBook);
    }

}
