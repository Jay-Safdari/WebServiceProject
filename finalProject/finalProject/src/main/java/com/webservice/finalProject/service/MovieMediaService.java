package com.webservice.finalProject.service;

import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.model.MovieMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieMediaService {

    private final OMDbAdaptor omdbAdaptor;

    @Autowired
    public MovieMediaService(OMDbAdaptor omdbAdaptor) {
        this.omdbAdaptor = omdbAdaptor;
    }

    public MovieMedia getMedia(String title) {
        MovieDTO movieDTO = omdbAdaptor.getMovieInfoByTitle(title);
        return extractMedia(movieDTO);
    }

    private MovieMedia extractMedia(MovieDTO movieDTO) {
        return MovieMedia.builder()
                .title(movieDTO.getTitle())
                .posterLink(movieDTO.getPosterLink())
                .build();
    }
}
