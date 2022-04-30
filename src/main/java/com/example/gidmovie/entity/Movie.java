package com.example.gidmovie.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private double duration;
    private String createdDate;
    private String picUrl;
    private String videoUrl;
    @OneToMany(mappedBy = "movie")
    private List<Comment> comments;
    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;

    @ManyToOne
    private Genre genre;
    @ManyToMany
    private List<Category> categories;
    @ManyToMany
    private List<Actor> actor;



}
