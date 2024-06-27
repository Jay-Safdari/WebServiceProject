package com.webservice.finalProject.controller;

import com.webservice.finalProject.model.MovieDetails;
import com.webservice.finalProject.service.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieDetailsController {
    private final MovieDetailsService detailsService;

    @Autowired
    public MovieDetailsController(MovieDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @PostMapping("/{title}/details")
    public MovieDetails saveDetails(@PathVariable String title) {
        return  detailsService.saveDetails(title);
    }

    @GetMapping("/{title}/details")
    public MovieDetails getDetails(@PathVariable String title) {
        return detailsService.getDetails(title);
    }
}
