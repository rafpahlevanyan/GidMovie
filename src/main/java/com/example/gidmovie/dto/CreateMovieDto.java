package com.example.gidmovie.dto;

import com.example.gidmovie.entity.Category;
import com.example.gidmovie.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateMovieDto {
    private int id;
    private String title;
    private String description;
    private double duration;
    private String createdDate;
    private String picUrl;
    private String videoUrl;
    private List<Integer> categories;
    private int actorId;
    private int genreId;


}
