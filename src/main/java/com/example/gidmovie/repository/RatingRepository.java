package com.example.gidmovie.repository;

import com.example.gidmovie.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value = "SELECT AVG(film_rating)FROM rating WHERE movie_id =?1", nativeQuery = true)
    Double getMovieRating(int movie_id);

    @Modifying
    @Query(value = "UPDATE rating r set r.film_rating = ?1, r.movie_id=?2 where r.user_id=?3", nativeQuery = true)
    void updateRating(int ratingVal, int movie_id, int user_id);

    @Modifying
    @Query(value = "INSERT into rating (film_rating,movie_id,user_id) values(?1,?2,?3)", nativeQuery = true)
    void addRating(int ratingVal, int movie_id, int user_id);
}