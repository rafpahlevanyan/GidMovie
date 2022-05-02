package com.example.gidmovie.repository;

import com.example.gidmovie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {


    Page<Movie> findMoviesByGenre_Id(int genreId, Pageable pageable);
    Page<Movie> findMoviesByCategories_Id(int categoryId, Pageable pageable);

    Page<Movie> findMoviesByActor_Id(int actorId, Pageable pageable);

    Page<Movie> findMoviesByTitleContaining(String title,Pageable pageable);

}
