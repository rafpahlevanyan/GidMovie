package com.example.gidmovie.service;

import com.example.gidmovie.entity.Actor;
import com.example.gidmovie.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public List<Actor> getActorsFromRequest(List<Integer> actorsIds) {
        List<Actor> actors = new ArrayList<>();
        for (Integer actor : actorsIds) {
            actors.add(actorRepository.getById(actor));
        }
        return actors;
    }
}
