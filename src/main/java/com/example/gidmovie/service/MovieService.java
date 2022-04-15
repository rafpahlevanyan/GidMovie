package com.example.gidmovie.service;

import com.example.gidmovie.entity.Actor;
import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.repository.ActorRepository;
import com.example.gidmovie.repository.GenreRepository;
import com.example.gidmovie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;



    @Value("C:/Users/asus/IdeaProjects/GidMovie/img/")
    private String imgPath;

    public Movie getById(int id) {
        return movieRepository.getById(id);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie addMovie(Movie movie, List<Integer> actors,MultipartFile uploadedFile) throws IOException {
        List<Actor> actorsFromRequest = getActorsFromRequest(actors);
        movie.setActors(actorsFromRequest);
        if (!uploadedFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imgPath + fileName);
            uploadedFile.transferTo(newFile);
            movie.setPicUrl(fileName);
        }
        movieRepository.save(movie);
        return movie;
    }




    private List<Actor> getActorsFromRequest(List<Integer> actorsIds) {
        List<Actor> actors = new ArrayList<>();
        for (Integer actor : actorsIds) {
            actors.add(actorRepository.getById(actor));
        }
        return actors;
    }

}
