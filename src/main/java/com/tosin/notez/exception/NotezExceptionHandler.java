package com.tosin.notez.exception;


import com.tosin.notez.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class NotezExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception exception) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal Server Error";

        if (exception instanceof NotezException) {

            status = ((NotezException) exception).getHttpStatus();
            message = exception.getMessage();
        }

        if (exception instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
            message = "Access Denied";
        }

        Response<String> response = Response.<String>builder()
                .status(false)
                .body(message).build();
        return ResponseEntity.status(status).body(response);
    }

}
