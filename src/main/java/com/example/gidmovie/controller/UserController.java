package com.example.gidmovie.controller;

import com.example.gidmovie.entity.User;
import com.example.gidmovie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/saveUser")
    public String addUserPage() {
        return "userRegister";
    }

    @PostMapping("/saveUser")
    public String addUser(@ModelAttribute User user) {
        userService.create(user);
        return "/index";
    }
}
