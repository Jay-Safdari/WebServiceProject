package com.webservice.finalProject.service;

import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.model.MovieRatings;
import com.webservice.finalProject.repository.MovieRatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingsService {

    private final OMDbAdaptor omdbAdaptor;
    private final MovieRatingsRepository ratingsRepository;

    @Autowired
    public MovieRatingsService(OMDbAdaptor omdbAdaptor, MovieRatingsRepository ratingsRepository) {
        this.omdbAdaptor = omdbAdaptor;
        this.ratingsRepository = ratingsRepository;
    }

    public MovieRatings saveRatings(String title) {
        MovieDTO movieDTO = omdbAdaptor.getMovieInfoByTitle(title);
        MovieRatings ratings = extractRatings(movieDTO);
        ratingsRepository.save(ratings);
        return ratings;
    }

    public MovieRatings getRatings(String title) {
        try {
            return ratingsRepository.findByTitle(title);
        } catch (EmptyResultDataAccessException e) {
            return saveRatings(title);
        }
    }

    private MovieRatings extractRatings(MovieDTO movieDTO) {
        return MovieRatings.builder()
                .title(movieDTO.getTitle())
                .imdbRating(movieDTO.getImdbRating())
                .imdbVotes(movieDTO.getImdbVotes())
                .build();
    }
}

