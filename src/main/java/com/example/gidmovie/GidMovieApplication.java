package com.example.gidmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GidMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(GidMovieApplication.class, args);
    }

}
