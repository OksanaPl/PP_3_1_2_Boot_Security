package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kata.spring.boot_security.demo.Exception.CannotExecuteActionException;
import ru.kata.spring.boot_security.demo.Exception.ErrorResponse;
import ru.kata.spring.boot_security.demo.Exception.UserNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "User : " + e.getMessage() + " not found!",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(CannotExecuteActionException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
