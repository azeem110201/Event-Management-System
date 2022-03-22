package com.azeem.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/profile")
public class ProfileController {

    @GetMapping("/user")
    public String userProfile(Model model){

        model.addAttribute("hello", "hello user");
        return "profile";
    }
}
