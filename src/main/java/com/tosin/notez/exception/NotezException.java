package com.tosin.notez.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotezException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;


    public NotezException(String message, HttpStatus httpStatus) {

        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }


}
