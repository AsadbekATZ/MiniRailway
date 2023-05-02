package com.example.MiniRailway.controller;

import com.example.MiniRailway.domain.dto.TrainDto;
import com.example.MiniRailway.service.train.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TrainService trainService;
    @PostMapping(value = "/create-train")
    public String createTrains(@ModelAttribute TrainDto trainDto, Model model){
        trainService.save(trainDto);
        model.addAttribute("allTrains", trainService.getAll());
        model.addAttribute("getArrivalTime", new HashMap<>());
        model.addAttribute("message", "Train successfully added!");
        return "admin-trains";
    }

}
