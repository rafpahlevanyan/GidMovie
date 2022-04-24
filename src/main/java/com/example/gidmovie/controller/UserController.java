package com.example.gidmovie.controller;

import com.example.gidmovie.dto.CreateUserDto;
import com.example.gidmovie.entity.Role;
import com.example.gidmovie.entity.User;
import com.example.gidmovie.service.MailService;
import com.example.gidmovie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final ModelMapper mapper;
    private final MailService mailService;

    @GetMapping("/register")
    public String addUserPage() {
        return "userRegister";
    }


    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid CreateUserDto createUserDto,
                          BindingResult bindingResult,
                          ModelMap map, Locale locale) throws MessagingException {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError allError : bindingResult.getAllErrors()) {
                errors.add(allError.getDefaultMessage());
            }
            map.addAttribute("errors", errors);
            return "userRegister";
        } else {
            User user = mapper.map(createUserDto, User.class);
            user.setActive(false);
            user.setToken(UUID.randomUUID().toString());
            user.setTokenCreatedDate(LocalDateTime.now());
            user.setRole(Role.USER);
            userService.create(user);
            mailService.sendHtmlEmail(user.getEmail(),
                    "Welcome " + user.getSurname(),
                    user, " http://localhost:8080/user/activate?token=" + user.getToken(), "verifyTemplate", locale);
            return "emailCheck";
        }
    }

    @GetMapping("/user/activate")
    public String activateUser(ModelMap map, @RequestParam(name = "token") String token) {
        Optional<User> user = userService.findByToken(token);

        if (user.isEmpty()) {
            map.addAttribute("message", "User does not exist");
            return "activateUser";
        }
        User userFromDb = user.get();
        if (userFromDb.isActive()) {
            map.addAttribute("message", "User already active");
            return "activateUser";
        }
        userFromDb.setActive(true);
        userFromDb.setToken(null);
        userFromDb.setTokenCreatedDate(null);
        userService.save(userFromDb);
        map.addAttribute("message", "User activated ,please login");
        return "activateUser";
    }
}
