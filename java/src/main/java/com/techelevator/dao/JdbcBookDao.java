package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.BookAlreadyExistsException;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        String insertBook = "insert into books (title, author, isbn, pages_total) values (?,?,?,?);";

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
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "select * from books";

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
}
