package com.example.gidmovie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CreateUserDto {
    private int id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Surname can not be empty")
    private String surname;
    @NotBlank(message = "Input valid email address")
    private String email;
    @NotBlank(message = "Password can not be empty")
    private String password;
}
