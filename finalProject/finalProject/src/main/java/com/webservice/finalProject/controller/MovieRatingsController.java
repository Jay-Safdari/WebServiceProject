package com.webservice.finalProject.controller;

import com.complete.lastversion.model.MovieRatings;
import com.complete.lastversion.service.MovieRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieRatingsController {

    private final MovieRatingsService ratingsService;

    @Autowired
    public MovieRatingsController(MovieRatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @PostMapping("/{title}/ratings")
    public MovieRatings saveRatings(@PathVariable String title) {
        return ratingsService.saveRatings(title);
    }

    @GetMapping("/{title}/ratings")
    public MovieRatings getRatings(@PathVariable String title) {
        return ratingsService.getRatings(title);
    }
}

