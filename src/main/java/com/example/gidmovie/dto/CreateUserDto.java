package com.example.gidmovie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserDto {
    private int id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @NotEmpty(message = "Surname can not be empty")
    private String surname;
    @NotEmpty(message = "Input valid email address")
    private String email;
    @NotEmpty(message = "Password can not be empty")
    private String password;
}
