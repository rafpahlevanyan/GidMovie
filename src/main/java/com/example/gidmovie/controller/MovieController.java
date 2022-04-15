package com.example.gidmovie.controller;

import com.example.gidmovie.dto.CreateMovieDto;
import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.service.ActorService;
import com.example.gidmovie.service.CategoryService;
import com.example.gidmovie.service.GenreService;
import com.example.gidmovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private final GenreService genreService;

    @Value("C:/Users/User/IdeaProjects/GidMovie/img/")
    private String imgPath;

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
        map.addAttribute("movies", movieService.findAll());
        map.addAttribute("actors", actorService.findAll());
        map.addAttribute("categories", categoryService.findAll());
        map.addAttribute("genres", genreService.findAll());
        return "addFilm";
    }

    @PostMapping("/addFilm")
    public String addMovie(@ModelAttribute CreateMovieDto createMovieDto,
                           @RequestParam("picture") MultipartFile uploadedFile,
                           @RequestParam(name = "actors") List<Integer> actors,
                           @RequestParam(name = "categories") List<Integer> categories) throws IOException {
        Movie movie = mapper.map(createMovieDto, Movie.class);
        movieService.addMovie(movie, actors, categories, uploadedFile);
        return "redirect:/";
    }

    @GetMapping("/index{id}")
    public String singleMovie(ModelMap map, @PathVariable int id) {
        Movie movie = movieService.getById(id);
        map.addAttribute("movies", movie);
        map.addAttribute("actors", actorService.findAll());
        return "singleMovie";
    }

    @GetMapping("/")
    public String main(ModelMap map) {
        map.addAttribute("movies", movieService.findAll());
        return "index";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imgPath + picName);
        return IOUtils.toByteArray(inputStream);
    }

}
