package com.example.gidmovie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
