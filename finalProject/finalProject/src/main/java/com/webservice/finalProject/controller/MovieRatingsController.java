package com.complete.lastversion.controller;

import com.complete.lastversion.model.MovieRatings;
import com.complete.lastversion.service.MovieRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieRatingsController {

    private final MovieRatingsService ratingsService;

    @Autowired
    public MovieRatingsController(MovieRatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GetMapping("/{title}/ratings")
    public MovieRatings getRatings(@PathVariable String title) {
        return ratingsService.getRatings(title);
    }
}

