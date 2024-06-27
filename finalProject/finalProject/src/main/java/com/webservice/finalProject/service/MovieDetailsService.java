package com.webservice.finalProject.service;

import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.model.MovieDetails;
import com.webservice.finalProject.repository.MovieDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MovieDetailsService {

    private final OMDbAdaptor omDbAdaptor;
    private final MovieDetailsRepository detailsRepository;

    @Autowired
    public MovieDetailsService(OMDbAdaptor omDbAdaptor, MovieDetailsRepository detailsRepository) {
        this.omDbAdaptor = omDbAdaptor;
        this.detailsRepository = detailsRepository;
    }

    public MovieDetails saveDetails(String title) {
        MovieDTO movieDTO = omDbAdaptor.getMovieInfoByTitle(title);
        MovieDetails details = extractDetails(movieDTO);
        detailsRepository.save(details);
        return details;
    }

    public MovieDetails getDetails(String title) {
        try {
            return detailsRepository.findByTitle(title);
        } catch (EmptyResultDataAccessException e) {
            return  saveDetails(title);
        }
    }

    private MovieDetails extractDetails(MovieDTO movieDTO) {
        return MovieDetails.builder()
                .title(movieDTO.getTitle())
                .runtime(movieDTO.getRuntime())
                .genre(movieDTO.getGenre())
                .plot(movieDTO.getPlot())
                .build();
    }
}
