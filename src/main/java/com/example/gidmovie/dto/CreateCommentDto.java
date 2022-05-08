package com.example.gidmovie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateCommentDto {

    private int id;
    @NotEmpty(message = "Cannot input empty area")
    private String description;
    private int userId;
    private int movieId;

}
