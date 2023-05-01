package com.example.MiniRailway.controller;

import com.example.MiniRailway.domain.dto.UserDto;
import com.example.MiniRailway.domain.entity.user.UserEntity;
import com.example.MiniRailway.service.train.TrainService;
import com.example.MiniRailway.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class AuthController {
    public static UserEntity currentUser;

    private final TrainService trainService;
    private final UserService userService;
    @GetMapping
    public String registerGet(Model model){
        return "auth";
    }
    @PostMapping
    public String registerPost(@ModelAttribute UserDto userDto,
                               Model model){
        userService.save(userDto);
        model.addAttribute("message", "User successfully added!");
        return "auth";
    }

    @PostMapping(value = "/login")
    public String loginPost(@RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               Model model){
        currentUser = userService.login(username, password);
        if (!currentUser.getUsername().equals("admin")){
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("allTrains", trainService.getAll());
            model.addAttribute("getArrivalTime", new HashMap<>());
            return "user-menu";
        }
        return "auth";
    }
}
