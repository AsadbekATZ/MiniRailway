package com.example.MiniRailway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class AuthController {
    @GetMapping
    public String registerGet(Model model){
        return "auth";
    }
}
