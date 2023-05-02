package com.example.MiniRailway.controller;

import com.example.MiniRailway.domain.dto.TicketDto;
import com.example.MiniRailway.domain.entity.seat.SeatEntity;
import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import com.example.MiniRailway.domain.entity.train.TrainEntity;
import com.example.MiniRailway.service.seat.SeatService;
import com.example.MiniRailway.service.ticket.TicketService;
import com.example.MiniRailway.service.train.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.MiniRailway.controller.AuthController.currentUser;

@Controller
@RequestMapping(value = "/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final SeatService seatService;
    private final TrainService trainService;

    @GetMapping(value = "/view-seat/{seatId}")
    public String viewSeat(@PathVariable(value = "seatId")UUID seatId,
                           Model model){
        SeatEntity seat = seatService.getById(seatId);
        model.addAttribute("seat", seat);
        model.addAttribute("currentUser", currentUser);
        return "buy-tickets";
    }

    @PostMapping(value = "/buy-ticket")
    public String buyTicket(@RequestParam(name = "fullName") String passengerName,
                            @RequestParam(name = "seatId") UUID seatId,
                            Model model){
        SeatEntity seat = seatService.getById(seatId);
        TrainEntity train = trainService.getById(seat.getTrain().getId());
        ticketService.save(new TicketDto(currentUser, passengerName, seat));
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("train",train);
        model.addAttribute("getArrivalTime",trainService.getArrivalTime(train.getEndPoint()).get(train.getId()));
        model.addAttribute("message", "Ticket successfully bought!");
        return "train-seats";
    }
}
