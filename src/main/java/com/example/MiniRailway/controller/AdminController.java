package com.example.MiniRailway.controller;

import com.example.MiniRailway.domain.dto.TrainDto;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.service.seat.SeatService;
import com.example.MiniRailway.service.train.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TrainService trainService;
    private final SeatService seatService;
    @PostMapping(value = "/create-train")
    public String createTrains(@ModelAttribute TrainDto trainDto, Model model){
        trainService.save(trainDto);
        model.addAttribute("allTrains", trainService.getAll());
        model.addAttribute("getArrivalTime", new HashMap<>());
        model.addAttribute("availableSeats",trainService.emptyTrainSeats());
        model.addAttribute("message", "Train successfully added!");
        return "admin-trains";
    }
    @GetMapping(value = "edit-train/{trainId}")
    public String editTrainGet(@PathVariable(name = "trainId")UUID trainId,
                               Model model){
        TrainEntity train = trainService.getById(trainId);
        model.addAttribute("train",train);
        model.addAttribute("availableSeats",seatService.emptySeats(train.getId()));
        model.addAttribute("getArrivalTime",trainService.getArrivalTime(train.getEndPoint()).get(trainId));
        return "edit-train";
    }

}
