package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.BookAlreadyExistsException;
import com.techelevator.model.User;
import com.techelevator.model.UserBook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcBookDao implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBookDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }

    @Override
    public boolean createBook(String title, String author, String isbn, Long pagesTotal) {
        boolean bookCreated = false;

        String insertBook = "INSERT INTO books (title, author, isbn, pages_total) VALUES (?,?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "isbn";
        bookCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertBook, new String[]{id_column});
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, isbn);
            ps.setLong(4,pagesTotal);
            return ps;
        }
        , keyHolder) == 1;

        return bookCreated;
    }

    @Override
    public boolean deleteBook(String isbn) {
        boolean delete = false;


        return delete;
    }

    @Override
    public List<UserBook> findAllUserBooksInProgress(Long userId) {
        List<UserBook> listOfUserBooks = new ArrayList<>();
        String sql = "SELECT books.title, user_id, user_book.isbn, pages_read, completed " +
                "FROM user_book " +
                "INNER JOIN books ON user_book.isbn = books.isbn " +
                "WHERE user_id = ? AND completed = false";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            UserBook userBook = mapRowToUserBook(results);
            listOfUserBooks.add(userBook);
        }


        return listOfUserBooks;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Book book = mapRowToBook(results);
            books.add(book);
        }

        return books;
    }

    @Override
    public Book findByIsbn(String isbn) throws BookAlreadyExistsException {
        for (Book book : this.findAll()) {
            if( book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new BookAlreadyExistsException("Book " + isbn + " already exists.");
    }

    private Book mapRowToBook(SqlRowSet bk) {
        Book book = new Book();
        book.setIsbn(bk.getString("isbn"));
        book.setTitle(bk.getString("title"));
        book.setAuthor(bk.getString("author"));
        book.setPagesTotal(bk.getLong("pages_total"));

        return book;
    }

    private UserBook mapRowToUserBook(SqlRowSet userBook) {
        UserBook currentUserBook = new UserBook();
        currentUserBook.setTitle(userBook.getString("title"));
        currentUserBook.setUserId(userBook.getLong("user_id"));
        currentUserBook.setIsbn(userBook.getString("isbn"));
        currentUserBook.setPagesRead(userBook.getInt("pages_read"));
        currentUserBook.setCompleted(userBook.getBoolean("completed"));

        return currentUserBook;
    }
}
