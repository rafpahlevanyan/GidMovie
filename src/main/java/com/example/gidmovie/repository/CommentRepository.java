package com.example.gidmovie.repository;

import com.example.gidmovie.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentsByMovie_Id(int movieId);

}
