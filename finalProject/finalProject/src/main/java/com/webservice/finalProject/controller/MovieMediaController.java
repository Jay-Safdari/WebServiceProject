package com.complete.lastversion.controller;

import com.complete.lastversion.model.MovieMedia;
import com.complete.lastversion.service.MovieMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieMediaController {

    private final MovieMediaService mediaService;

    @Autowired
    public MovieMediaController(MovieMediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/{title}/media")
    public MovieMedia getMedia(@PathVariable String title) {
        return mediaService.getMedia(title);
    }
}

