package com.techelevator.dao;

public interface BookDao {

    boolean createBook(String title, String author, String isbn);
}
