package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Book Already Exists.")
public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
    public BookAlreadyExistsException(){ }
}
