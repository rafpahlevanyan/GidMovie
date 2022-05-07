package com.example.gidmovie.service;


import com.example.gidmovie.entity.Role;
import com.example.gidmovie.entity.User;
import com.example.gidmovie.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    void create() {


    }

    @Test
    void save() {
        User user = new User();
        user.setName("poxos");
        user.setSurname("poxosyan");
        user.setEmail("poxos@mail.com");
        user.setPassword("poxos");
        user.setRole(Role.USER);
        userService.save(user);
        assertEquals(1, userRepository.count());
        Optional<User> byId = userRepository.findById(user.getId());
        assertTrue(byId.isPresent());
        assertEquals("poxos", byId.get().getName());
        assertEquals("poxos@mail.com", byId.get().getEmail());

    }


    @Test
    void findByToken() {
    }
}