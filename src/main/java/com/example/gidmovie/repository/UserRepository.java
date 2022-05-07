package com.example.gidmovie.repository;

import com.example.gidmovie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String userEmail);

    Optional<User> findByToken(String token);

}
