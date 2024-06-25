package com.webservice.finalProject.controller;

import com.webservice.finalProject.model.Movie;
import com.webservice.finalProject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{title}")
    public Movie getMovieByTitle(@PathVariable String title) {
        return movieService.getMovieInfoByTitle(title);
    }

    @GetMapping("/search/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable String title) {
        return movieService.getMoviesByTitle(title);
    }

    @GetMapping("/all")
    public List<Movie> getMoviesAll(){
        return movieService.getMovies();
    }
}
