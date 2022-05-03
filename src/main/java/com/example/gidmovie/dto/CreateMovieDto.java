package com.example.gidmovie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateMovieDto {
    private int id;
    @NotEmpty(message = "Title can not be empty")
    private String title;
    @NotEmpty(message = "Description can not be empty")
    private String description;
    @NotNull(message = "Duration can not be empty")
    private Double duration;
    @NotEmpty(message = "Created Date can not be empty")
    private String createdDate;
    private String picUrl;
    @NotEmpty(message = "Video url can not be empty")
    private String videoUrl;
    @NotEmpty(message = "Please choose category")
    private List<Integer> categories;
    @NotEmpty(message = "Please choose actor")
    private List<Integer> actors;
    private int genreId;


}
