package com.example.gidmovie.service;

import com.example.gidmovie.entity.Comment;
import com.example.gidmovie.entity.Movie;
import com.example.gidmovie.entity.User;
import com.example.gidmovie.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void addComment(Comment comment, User user, Movie movie) {
        comment.setUser(user);
        comment.setMovie(movie);
        commentRepository.save(comment);
    }

    public List<Comment> GetAllComments(int movieId) {
        List<Comment> comments = commentRepository.findCommentsByMovie_Id(movieId);
        if (!comments.isEmpty()){
            return comments;
        }
        return null;
    }
}

