package com.example.sportsbetting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler for TeamNotFoundException.
 * Returns a 404 NOT FOUND status with the exception message.
 */
@RestControllerAdvice
public class TeamNotFoundAdvice {

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String teamNotFoundHandler(TeamNotFoundException ex) {
        // Return the exception message as the response body
        return ex.getMessage();
    }
}