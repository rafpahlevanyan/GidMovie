package com.example.gidmovie.service;


import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.entity.Rating;
import com.example.gidmovie.entity.User;
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
    public int getUserMovieRating(int user_id,int movie_id) {
        return ratingRepository.getUserMovieRating(user_id,movie_id);
    }

    public void updateRating(int ratingVal, int movie_id, int user_id) {
        Rating r =  ratingRepository.findByUserIdAndMovieId(user_id, movie_id) ;
        r.setFilmRating(ratingVal);
        ratingRepository.saveAndFlush(r);
    }

    public void addRating(int ratingVal, Movie movie, User user) {
        Rating r = new Rating();

        r.setMovie(movie);
        r.setFilmRating(ratingVal);
        r.setUser(user);
        ratingRepository.save(r);
    }
    public Rating getUserRatingId(int user_id, int movie_id) {
        try {
            return ratingRepository.findByUserIdAndMovieId(user_id, movie_id);
        }catch (Exception ex){
            return null;
        }
    }


}
