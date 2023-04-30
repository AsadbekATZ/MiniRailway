package com.example.MiniRailway.controller;

import com.example.MiniRailway.domain.dto.UserDto;
import com.example.MiniRailway.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class AuthController {

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
        userService.login(username, password);
        model.addAttribute("message", userService.login(username, password).getUsername());
        return "auth";
    }
}
