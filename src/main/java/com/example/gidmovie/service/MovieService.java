package com.example.gidmovie.service;

import com.example.gidmovie.entity.Actor;
import com.example.gidmovie.entity.Genre;
import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.repository.ActorRepository;
import com.example.gidmovie.repository.GenreRepository;
import com.example.gidmovie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    public Movie getById(int id){
        return movieRepository.getById(id);
    }

    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie addMovie(Movie movie, List<Integer> actors) {
        List<Actor> actorsFromRequest = getActorsFromRequest(actors);
        movie.setActors(actorsFromRequest);
        movieRepository.save(movie);
        return movie;
    }

    private List<Genre> getGenresFromRequest(List<Integer> genresIds) {
        List<Genre> genres = new ArrayList<>();
        for (Integer genre : genresIds) {
            genres.add(genreRepository.getById(genre));
        }
        return genres;
    }

    private List<Actor> getActorsFromRequest(List<Integer> actorsIds) {
        List<Actor> actors = new ArrayList<>();
        for (Integer actor : actorsIds) {
            actors.add(actorRepository.getById(actor));
        }
        return actors;
    }

}
