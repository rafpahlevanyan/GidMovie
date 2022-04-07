package com.example.gidmovie.repository;

import com.example.gidmovie.entity.Actor;
import com.example.gidmovie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    List<User> findAllByName(String name);



}
