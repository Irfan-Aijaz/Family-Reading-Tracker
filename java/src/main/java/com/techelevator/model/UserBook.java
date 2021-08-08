package com.techelevator.model;

public class UserBook {

    private String title;
    private Long userId;
    private String isbn;
    private int pagesRead;
    private boolean completed;

    public UserBook() {
    }

    public UserBook(String title, Long userId, String isbn, int pagesRead, boolean completed) {
        this.title = title;
        this.userId = userId;
        this.isbn = isbn;
        this.pagesRead = pagesRead;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
