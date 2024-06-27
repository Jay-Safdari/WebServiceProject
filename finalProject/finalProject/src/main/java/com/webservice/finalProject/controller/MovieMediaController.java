package com.complete.lastversion.controller;

import com.complete.lastversion.model.MovieMedia;
import com.complete.lastversion.service.MovieMediaService;
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
    public MovieMedia saveMedia(@PathVariable String title) {
        return mediaService.saveMedia(title);
    }

    @GetMapping("/{title}/media")
    public MovieMedia getMedia(@PathVariable String title) {
        return mediaService.getMedia(title);
    }
}

