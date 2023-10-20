package ru.kata.spring.boot_security.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private LocalDateTime dateTime;

}
