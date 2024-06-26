package com.webservice.finalProject.service;

import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.model.MovieBasicInfo;
import com.webservice.finalProject.repository.MovieBasicInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MovieBasicInfoService {

    private final OMDbAdaptor omdbAdaptor;
    private final MovieBasicInfoRepository basicInfoRepository;

    @Autowired
    public MovieBasicInfoService(OMDbAdaptor omdbAdaptor, MovieBasicInfoRepository basicInfoRepository) {
        this.omdbAdaptor = omdbAdaptor;
        this.basicInfoRepository = basicInfoRepository;
    }

    public MovieBasicInfo saveBasicInfo(String title) {
        MovieDTO movieDTO = omdbAdaptor.getMovieInfoByTitle(title);
        MovieBasicInfo basicInfo = extractBasicInfo(movieDTO);
        basicInfoRepository.save(basicInfo);
        return basicInfo;
    }

    public MovieBasicInfo getBasicInfo(String title) {
        try {
            return basicInfoRepository.findByTitle(title);
        } catch (EmptyResultDataAccessException e) {
            return saveBasicInfo(title);
        }
    }

    private MovieBasicInfo extractBasicInfo(MovieDTO movieDTO) {
        return MovieBasicInfo.builder()
                .title(movieDTO.getTitle())
                .year(movieDTO.getYear())
                .released(movieDTO.getReleased())
                .build();
    }
}
