package com.example.MiniRailway.config;

import com.example.MiniRailway.exception.WrongCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LibraryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = WrongCredentialsException.class)
    public ResponseEntity<String> handleDataNotFoundException(WrongCredentialsException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
