package com.webservice.finalProject.controller;



import com.webservice.finalProject.model.MovieList;
import com.webservice.finalProject.service.MovieListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieListController {

    private final MovieListService movieListService;

    public MovieListController(MovieListService movieListService) {
        this.movieListService = movieListService;
    }

    @GetMapping("/list/{title}")
    public List<MovieList> getMoviesList(@PathVariable String title) {
        return movieListService.getMoviesByTitle(title);
    }
}
