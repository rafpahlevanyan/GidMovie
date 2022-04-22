package com.example.gidmovie.repository;

import com.example.gidmovie.entity.Genre;
import com.example.gidmovie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {


   Page<Movie> findMoviesByGenre_Id(int genreId,Pageable pageable);

}
