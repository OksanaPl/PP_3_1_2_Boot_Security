package ru.kata.spring.boot_security.demo.exceptions;

public class CannotExecuteActionException extends RuntimeException {
    public CannotExecuteActionException(String message) {
        super(message);
    }
}
