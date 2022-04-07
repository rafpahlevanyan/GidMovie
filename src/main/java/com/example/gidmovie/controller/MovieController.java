package com.example.gidmovie.controller;

import com.example.gidmovie.dto.CreateMovieDto;
import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.service.ActorService;
import com.example.gidmovie.service.CategoryService;
import com.example.gidmovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final ModelMapper mapper;

    private final MovieService movieService;
    private final ActorService actorService;
    private final CategoryService categoryService;

    @GetMapping("/index")
    public String moviePage(ModelMap map, @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Movie> moviePage = movieService.findAll(pageRequest);
        map.addAttribute("moviePage", moviePage);
        int totalPages = moviePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            map.addAttribute("pageNumbers", pageNumbers);
        }
        return "index";
    }

    @GetMapping("/addFilm")
    public String addMoviePage(ModelMap map) {
        map.addAttribute("actors", actorService.findAll());
        map.addAttribute("categories", categoryService.findAll());
        return "addFilm";
    }

    @PostMapping("/addFilm")
    public String addMovie(@ModelAttribute
                           CreateMovieDto createMovieDto,
                           ModelMap map) {
        Movie movie = mapper.map(createMovieDto, Movie.class);
        movieService.addMovie(movie, createMovieDto.getCategories());
        return "redirect:/index";
    }

    @GetMapping("/index{id}")
    public String singleMovie(ModelMap map, @PathVariable int id){
        Movie movie = movieService.getById(id);
        map.addAttribute("movie",movie);
        map.addAttribute("actors",actorService.findAll());
        return "singleMovie";
    }

}
