package com.example.gidmovie.service;

import com.example.gidmovie.entity.Actor;
import com.example.gidmovie.entity.Category;
import com.example.gidmovie.entity.Genre;
import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorService actorService;
    private final CategoryService categoryService;


    @Value("${gidmovie.upload.path}")
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

    public Page<Movie> findAllByGenre(int genreId, Pageable pageable){
        return movieRepository.findMoviesByGenre_Id(genreId,pageable);
    }
    public Page<Movie> findMoviesByCategories(int categoryId, Pageable pageable){
        return movieRepository.findMoviesByCategories_Id(categoryId,pageable);
    }
    public Page<Movie> findMoviesByTitle(String title, Pageable pageable){
        return movieRepository.findMoviesByTitleContaining(title,pageable);
    }
    public Page<Movie> findMoviesByActor(int actorId, Pageable pageable){
        return movieRepository.findMoviesByActor_Id(actorId,pageable);
    }

    public Movie addMovie(Movie movie, List<Integer> actors, List<Integer> categories, MultipartFile uploadedFile) throws IOException {
        List<Actor> actorsFromRequest = actorService.getActorsFromRequest(actors);
        List<Category> categoriesFromRequest = categoryService.getCategoriesFromRequest(categories);
        movie.setActor(actorsFromRequest);
        movie.setCategories(categoriesFromRequest);
        if (!uploadedFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imgPath + fileName);
            uploadedFile.transferTo(newFile);
            movie.setPicUrl(fileName);
        }
        movieRepository.save(movie);
        return movie;
    }


}
