package com.example.gidmovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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
    private List<Comment> commentList;
    @OneToMany(mappedBy = "movie")
    private List<Rating> ratingList;
    @ManyToOne
    private Genre genre;
    @ManyToMany
    @JoinTable(
            name = "movie_categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private List<Category> categories;
    @ManyToMany
        @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;




}
