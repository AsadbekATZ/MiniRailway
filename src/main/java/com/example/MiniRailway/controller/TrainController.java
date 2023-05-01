package com.example.MiniRailway.controller;

import com.example.MiniRailway.domain.dto.TrainDto;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.service.train.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/trains")
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;

    @GetMapping("/all")
    public String allTrains(Model model) {
        model.addAttribute("allTrains", trainService.getAll());
        return "trains";
    }

    @PostMapping(value = "/create-trains")
    public String createTrains(@ModelAttribute TrainDto trainDto, Model model){
        trainService.save(trainDto);
        model.addAttribute("message", "User successfully added!");
        return "trains";
    }

    @GetMapping(value = "/train-edit/{trainId}")
    public String details(@PathVariable(value = "trainId") UUID trainId,Model model){
        model.addAttribute("train",trainService.getById(trainId));
        return "train-edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam(name = "name") String name,
                       @RequestParam(name = "price") Double price,
                       @RequestParam(name="id") UUID trainId,
                       Model model){
        trainService.update(name, price, trainId);
        model.addAttribute("train",trainService.getById(trainId));
        return "train-edit";
    }

    @PostMapping(value = "delete")
    public String delete(@ModelAttribute TrainEntity train, Model model){
        trainService.delete(train);
        model.addAttribute("allTrains", trainService.getAll());
        return "trains";
    }

}
