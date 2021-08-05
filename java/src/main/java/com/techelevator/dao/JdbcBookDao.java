package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;

@Service
public class JdbcBookDao implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBookDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }

    @Override
    public boolean createBook(String title, String author, String isbn) {
        boolean bookCreated = false;

        String insertBook = "insert into books (title, author, isbn) values (?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "isbn";
        bookCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertBook, new String[]{isbn});
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, isbn);
            return ps;
        }
        , keyHolder) == 1;

        return bookCreated;



    }
}
