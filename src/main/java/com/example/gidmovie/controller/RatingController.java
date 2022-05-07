package com.example.gidmovie.controller;

import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.entity.Rating;
import com.example.gidmovie.entity.User;
import com.example.gidmovie.service.ActorService;
import com.example.gidmovie.service.MovieService;
import com.example.gidmovie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RatingController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private  ActorService actorService;



    @PostMapping("/setRating")
    public String setRating(ModelMap map, HttpServletRequest req) {
        int id =Integer.parseInt(req.getParameter("muvid"));
        int rate =Integer.parseInt(req.getParameter("rate"));
        try {
            int userId = ((User) map.getAttribute("user")).getId();
            Rating ratingId = ratingService.getUserRatingId(userId,id);
            if(ratingId !=null){
                ratingService.updateRating(rate,id,userId);
            }
            else {
                ratingService.addRating(rate,movieService.getById(id),(User) map.getAttribute("user"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/movies/"+id;
    }
}
