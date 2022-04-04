package com.example.gidmovie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateCommentDto {

    private int id;
    private String description;
    private int userId;
    private int movieId;

}
