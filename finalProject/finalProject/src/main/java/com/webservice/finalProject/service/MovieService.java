package com.webservice.finalProject.service;

import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.dto.MovieSearchResponseDTO;
import com.webservice.finalProject.model.Movie;
import com.webservice.finalProject.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final OMDbAdaptor omdbAPIAdaptor;

    @Autowired
    public MovieService(MovieRepository movieRepository ,OMDbAdaptor omdbAPIAdaptor) {

        this.omdbAPIAdaptor = omdbAPIAdaptor;
        this.movieRepository = movieRepository;
    }

    public Movie getMovieInfoByTitle(String title) {

        MovieDTO movieDTO = omdbAPIAdaptor.getMovieInfoByTitle(title);

        return Movie.builder()
                .title(movieDTO.getTitle())
                .released(movieDTO.getReleased())
                .runtime(movieDTO.getRuntime())
                .plot(movieDTO.getPlot())
                .posterLink(movieDTO.getPosterLink())
                .build();
    }

    public List<Movie> getMoviesByTitle(String title) {
        MovieSearchResponseDTO response = omdbAPIAdaptor.getMoviesByTitle(title);
        List<Movie> movies = new ArrayList<>();

        if (response == null || response.getSearchResults() == null) {
            return movies;
        }

        for (MovieDTO movieDTO : response.getSearchResults()) {
            Movie movie = getMovieInfoByTitle(movieDTO.getTitle());
            movies.add(movie);
        }

        return movies;
    }

    public List<Movie> getMovies(){
        return movieRepository.getAllMovies();
    }
}
