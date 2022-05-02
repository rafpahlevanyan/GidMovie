package com.example.gidmovie.service;


import com.example.gidmovie.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public Double getMovieRating(int movie_id) {
        return ratingRepository.getMovieRating(movie_id);
    }

    public void updateRating(int ratingVal, int movie_id, int user_id) {
        ratingRepository.updateRating(ratingVal, movie_id, user_id);
    }

    public void addRating(int ratingVal, int movie_id, int user_id) {
        ratingRepository.addRating(ratingVal, movie_id, user_id);
    }


}
