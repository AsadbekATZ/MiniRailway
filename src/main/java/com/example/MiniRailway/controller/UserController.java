package com.example.MiniRailway.controller;

import com.example.MiniRailway.service.train.TrainService;
import com.example.MiniRailway.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.UUID;

import static com.example.MiniRailway.controller.AuthController.currentUser;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TrainService trainService;
    @PostMapping(value = "/fill-balance")
    public String loginPost(@RequestParam(name = "amount") Double amount,
                            @RequestParam(name = "userId") UUID userId,
                            Model model){
        Double updatedBalance = currentUser.getBalance() + amount;
        userService.fillBalance(updatedBalance, userId);
        currentUser = userService.getById(userId);
        model.addAttribute("getArrivalTime", new HashMap<>());
        model.addAttribute("allTrains", trainService.getAll());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("message", "Balance successfully filled!");
        return "user-menu";
    }
}
