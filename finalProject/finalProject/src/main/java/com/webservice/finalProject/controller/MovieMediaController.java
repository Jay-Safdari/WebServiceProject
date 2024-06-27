package com.webservice.finalProject.controller;

import com.webservice.finalProject.model.MovieMedia;
import com.webservice.finalProject.service.MovieMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieMediaController {

    private final MovieMediaService mediaService;

    @Autowired
    public MovieMediaController(MovieMediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/{title}/media")
    public MovieMedia saveMedia(@PathVariable String title,@RequestBody MovieMedia movieMedia) {
        return mediaService.saveMedia(title, movieMedia);
    }

    @GetMapping("/{title}/media")
    public MovieMedia getMedia(@PathVariable String title) {
        return mediaService.getMedia(title);
    }
}

