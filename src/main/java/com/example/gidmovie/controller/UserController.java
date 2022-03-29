package com.example.gidmovie.controller;

import com.example.gidmovie.entity.User;
import com.example.gidmovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/saveUser")
    public String addUserPage() {
        return "userRegister";
    }

    @PostMapping("/saveUser")
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "/index";
    }
}
