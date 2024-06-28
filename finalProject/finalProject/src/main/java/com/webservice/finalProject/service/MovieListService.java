package com.webservice.finalProject.service;


import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.dto.MoviesDTO;
import com.webservice.finalProject.model.MovieList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieListService {

    private final OMDbAdaptor omdbAPIAdaptor;

    public MovieListService(OMDbAdaptor omdbAPIAdaptor) {
        this.omdbAPIAdaptor = omdbAPIAdaptor;
    }

    public MovieList getMovieInfoByTitle(String title) {

        MovieDTO movieDTO = omdbAPIAdaptor.getMovieInfoByTitle(title);

        return MovieList.builder()
                .titleList(movieDTO.getTitle())
                .yearList(movieDTO.getYear())
                .posterLinkList(movieDTO.getPosterLink())
                .build();
    }


    public List<MovieList> getMoviesByTitle(String title) {
        MoviesDTO response = omdbAPIAdaptor.getMoviesByTitle(title);
        List<MovieList> movies = new ArrayList<>();

        if (response == null || response.getListMoviesIncludeTitle() == null) {
            return movies;
        }

        for (MovieDTO movieDTO : response.getListMoviesIncludeTitle()) {
            MovieList movieList = getMovieInfoByTitle(movieDTO.getTitle());
            movies.add(movieList);
        }

        return movies;
    }


}
