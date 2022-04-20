package com.example.gidmovie.service;

import com.example.gidmovie.entity.Role;
import com.example.gidmovie.entity.User;
import com.example.gidmovie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }


}
