package com.example.gidmovie.repository;

import com.example.gidmovie.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value = "SELECT ROUND(AVG(film_rating),2) FROM rating WHERE movie_id =?1", nativeQuery = true)
    Double getMovieRating(int movie_id);

    @Query(value = "SELECT film_rating FROM rating WHERE movie_id =?2 and user_id=?1", nativeQuery = true)
    int getUserMovieRating(int user_id,int movie_id);

    Rating findByUserIdAndMovieId(int uid, int mid);
}