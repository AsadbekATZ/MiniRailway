package com.example.MiniRailway.config;

import com.example.MiniRailway.exception.AlreadyExistsException;
import com.example.MiniRailway.exception.NotFoundException;
import com.example.MiniRailway.exception.WrongSearchException;
import com.example.MiniRailway.service.train.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

import static com.example.MiniRailway.controller.AuthController.currentUser;

@ControllerAdvice
@RequiredArgsConstructor
public class RailwayExceptionHandler extends ResponseEntityExceptionHandler {
    private final TrainService trainService;
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
    @ExceptionHandler(value = WrongSearchException.class)
    public String handleWrongSearchException(WrongSearchException e,
                                              Model model){
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("availableSeats",trainService.emptyTrainSeats());
        model.addAttribute("allTrains", trainService.getAll());
        model.addAttribute("getArrivalTime", new HashMap<>());
        model.addAttribute("message", "Wrong search input please try again!");
        return "user-menu";
    }
}
