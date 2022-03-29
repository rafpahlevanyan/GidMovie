package com.example.gidmovie.controller;

import com.example.gidmovie.entity.Actor;
import com.example.gidmovie.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class ActorController {
    @Autowired
    private ActorRepository actorRepository;
    @GetMapping("/saveActor")
    public String addaActorPage() {
        return "actorRegister";
    }

    @PostMapping("/saveActor")
    public String addActor(@ModelAttribute Actor actor) {
        actorRepository.save(actor);
        return "/index";
    }
}

