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

import static com.example.MiniRailway.controller.AuthController.currentUser;

@Controller
@RequestMapping("/trains")
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;

    private final SeatService seatService;

    @GetMapping("/all")
    public String allTrains(Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("availableSeats",trainService.emptyTrainSeats());
        model.addAttribute("allTrains", trainService.getAll());
        model.addAttribute("getArrivalTime", new HashMap<>());
        return "user-menu";
    }

    @GetMapping(value = "/train-edit/{trainId}")
    public String details(@PathVariable(value = "trainId") UUID trainId,Model model){
        TrainEntity train = trainService.getById(trainId);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("train",train);
        model.addAttribute("availableSeats",seatService.emptySeats(train.getId()));
        model.addAttribute("getArrivalTime",trainService.getArrivalTime(train.getEndPoint()).get(trainId));
        return "train-seats";
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
