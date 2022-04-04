package com.example.gidmovie.dto;

import com.example.gidmovie.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateRatingDto {
    private int id;
    private double filmRating;
    private int userId;
    private int movieId;


}
