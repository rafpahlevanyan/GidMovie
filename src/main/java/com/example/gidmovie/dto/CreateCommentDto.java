package com.example.gidmovie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateCommentDto {

    private int id;
    private String description;
    private int userId;
    @NotBlank(message = "Cannot input null symbol")
    private int movieId;

}
