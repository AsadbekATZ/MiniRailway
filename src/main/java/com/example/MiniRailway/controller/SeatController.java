package com.example.MiniRailway.controller;

import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.service.seat.SeatService;
import com.example.MiniRailway.service.train.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.MiniRailway.controller.AuthController.currentUser;

@Controller
@RequestMapping(value = "/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;
    private final TrainService trainService;

    @GetMapping(value = "/view-seat/{seatId}")
    public String viewSeat(@PathVariable(value = "seatId")UUID seatId,
                           Model model){
        SeatEntity seat = seatService.getById(seatId);
        model.addAttribute("seat", seat);
        model.addAttribute("currentUser", currentUser);
        return "book-seat";
    }

    @PostMapping(value = "/book-seat")
    public String buyTicket(@RequestParam(name = "fullName") String passengerName,
                            @RequestParam(name = "seatId") UUID seatId,
                            Model model){
        seatService.bookSeat(currentUser, passengerName, seatId);
        SeatEntity seat = seatService.getById(seatId);
        TrainEntity train = trainService.getById(seat.getTrain().getId());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("train",seat.getTrain());
        model.addAttribute("availableSeats",seatService.emptySeats(train.getId()));
        model.addAttribute("getArrivalTime",trainService.getArrivalTime(train.getEndPoint()).get(train.getId()));
        model.addAttribute("message", "Ticket successfully bought!");
        return "train-seats";
    }
}
