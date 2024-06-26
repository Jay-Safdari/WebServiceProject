package com.webservice.finalProject.service;

import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.model.MovieBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieBasicInfoService {

    private final OMDbAdaptor omdbAdaptor;

    @Autowired
    public MovieBasicInfoService(OMDbAdaptor omdbAdaptor) {
        this.omdbAdaptor = omdbAdaptor;
    }

    public MovieBasicInfo getBasicInfo(String title) {
        MovieDTO movieDTO = omdbAdaptor.getMovieInfoByTitle(title);
        return extractBasicInfo(movieDTO);
    }

    private MovieBasicInfo extractBasicInfo(MovieDTO movieDTO) {
        return MovieBasicInfo.builder()
                .title(movieDTO.getTitle())
                .year(movieDTO.getYear())
                .released(movieDTO.getReleased())
                .build();
    }
}
