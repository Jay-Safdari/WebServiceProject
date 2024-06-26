package com.complete.lastversion.service;

import com.complete.lastversion.adaptor.OMDbAdaptor;
import com.complete.lastversion.dto.MovieDTO;
import com.complete.lastversion.model.MovieRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingsService {

    private final OMDbAdaptor omdbAdaptor;

    @Autowired
    public MovieRatingsService(OMDbAdaptor omdbAdaptor) {
        this.omdbAdaptor = omdbAdaptor;
    }

    public MovieRatings getRatings(String title) {
        MovieDTO movieDTO = omdbAdaptor.getMovieInfoByTitle(title);
        return extractRatings(movieDTO);
    }

    private MovieRatings extractRatings(MovieDTO movieDTO) {
        return MovieRatings.builder()
                .title(movieDTO.getTitle())
                .imdbRating(movieDTO.getImdbRating())
                .imdbVotes(movieDTO.getImdbVotes())
                .build();
    }
}

