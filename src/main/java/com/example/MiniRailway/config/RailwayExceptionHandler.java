package com.example.MiniRailway.config;

import com.example.MiniRailway.exception.AlreadyExistsException;
import com.example.MiniRailway.exception.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RailwayExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = AlreadyExistsException.class)
    public String handleDataNotFoundException(AlreadyExistsException e,
                                              Model model){
        model.addAttribute("message", e.getMessage());
        return "auth";
    }
    @ExceptionHandler(value = NotFoundException.class)
    public String handleDataNotFoundException(NotFoundException e,
                                              Model model){
        model.addAttribute("message", e.getMessage());
        return "auth";
    }
}
