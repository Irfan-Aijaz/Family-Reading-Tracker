package com.techelevator.model;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String summary;
    private String genre;
    private Long pagesTotal;


    public Book() {}

    public Book(String isbn, String title, String author, Long pagesTotal) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pagesTotal = pagesTotal;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getPagesTotal() {
        return pagesTotal;
    }

    public void setPagesTotal(Long pagesTotal) {
        this.pagesTotal = pagesTotal;
    }
}
