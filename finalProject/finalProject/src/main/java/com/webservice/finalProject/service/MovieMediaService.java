package com.webservice.finalProject.service;

import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.model.MovieMedia;
import com.webservice.finalProject.repository.MovieMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MovieMediaService {

    private final OMDbAdaptor omdbAdaptor;
    private final MovieMediaRepository mediaRepository;

    @Autowired
    public MovieMediaService(OMDbAdaptor omdbAdaptor, MovieMediaRepository mediaRepository) {
        this.omdbAdaptor = omdbAdaptor;
        this.mediaRepository = mediaRepository;
    }

    public MovieMedia saveMedia(String title, MovieMedia movieMedia) {
        MovieDTO movieDTO = omdbAdaptor.getMovieInfoByTitle(title);
        movieDTO.setPosterLink(movieMedia.getPosterLink());
        MovieMedia NewMovieMedia = extractMedia(movieDTO);
        NewMovieMedia.setPosterLink(movieMedia.getPosterLink());
        mediaRepository.save(NewMovieMedia);
        return NewMovieMedia;
    }

    public MovieMedia getMedia(String title) {
        try {
            return mediaRepository.findByTitle(title);
        } catch (EmptyResultDataAccessException e) {
            MovieMedia nullMovieMedia = MovieMedia.builder()
                    .title(title)
                    .posterLink("")
                    .build();
            return saveMedia(title,nullMovieMedia);
        }
    }

    private MovieMedia extractMedia(MovieDTO movieDTO) {
        return MovieMedia.builder()
                .title(movieDTO.getTitle())
                .posterLink(movieDTO.getPosterLink())
                .build();
    }
}
