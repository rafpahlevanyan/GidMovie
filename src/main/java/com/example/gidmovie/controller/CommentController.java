package com.example.gidmovie.controller;

import com.example.gidmovie.dto.CreateCommentDto;
import com.example.gidmovie.entity.Comment;
import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.security.CurrentUser;
import com.example.gidmovie.service.CommentService;
import com.example.gidmovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final MovieService movieService;
    private final ModelMapper mapper;


    @PostMapping("/setComment/{id}")
    public String movieComment(@PathVariable("id") int movieId, @ModelAttribute @Valid CreateCommentDto createCommentDto,
                               BindingResult bindingResult,
                               ModelMap map,
                               @AuthenticationPrincipal CurrentUser currentUser) {

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError allError : bindingResult.getAllErrors()) {
                errors.add(allError.getDefaultMessage());
            }
            map.addAttribute("errors", errors);
            return "redirect:/movies/" + movieId;
        } else {
            Comment movieComment = mapper.map(createCommentDto, Comment.class);
            Movie movie = movieService.getById(movieId);
            commentService.addComment(movieComment, currentUser.getUser(), movie);
            return "redirect:/movies/" + movieId;
        }
    }
}


