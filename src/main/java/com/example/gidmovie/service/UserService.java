package com.example.gidmovie.service;

import com.example.gidmovie.entity.Role;
import com.example.gidmovie.entity.User;
import com.example.gidmovie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}
