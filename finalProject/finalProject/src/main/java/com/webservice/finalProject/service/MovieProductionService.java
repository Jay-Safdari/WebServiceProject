package com.webservice.finalProject.service;


import com.webservice.finalProject.adaptor.OMDbAdaptor;
import com.webservice.finalProject.dto.MovieDTO;
import com.webservice.finalProject.exception.productionNotFoundException;
import com.webservice.finalProject.model.MovieProduction;
import com.webservice.finalProject.repository.MovieProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieProductionService {

    private final OMDbAdaptor omdbAdaptor;
    private final MovieProductionRepository productionRepository;

    @Autowired
    public MovieProductionService(OMDbAdaptor omdbAdaptor, MovieProductionRepository productionRepository) {
        this.omdbAdaptor = omdbAdaptor;
        this.productionRepository = productionRepository;
    }

    public MovieProduction saveProduction(String title) {
        MovieDTO movieDTO = omdbAdaptor.getMovieInfoByTitle(title);
        MovieProduction production = extractProduction(movieDTO);
        productionRepository.save(production);
        return production;
    }

    public MovieProduction getProduction(String title) {
        try {
            return productionRepository.findByTitle(title);
        } catch (EmptyResultDataAccessException e) {
            return saveProduction(title);
        }
    }

    private MovieProduction extractProduction(MovieDTO movieDTO) {
        return MovieProduction.builder()
                .title(movieDTO.getTitle())
                .director(movieDTO.getDirector())
                .production(movieDTO.getProduction())
                .language(movieDTO.getLanguage())
                .country(movieDTO.getCountry())
                .build();
    }

    public List<MovieProduction> getProductionsByCountry(String country) {
        return productionRepository.getProductionsByCountry(country);
    }

    public List<MovieProduction> getProductionsByLanguage(String language) {
        return productionRepository.getProductionsByLanguage(language);
    }

    public long addProduction(MovieProduction prod) {
        return productionRepository.addProduction(prod);
    }


    public void deleteProduction(Long id){

        if(productionRepository.findByID(id) == null){
            //throw exception
            throw new productionNotFoundException(id);
        }
        productionRepository.deleteProduction(id);
    }

    public void updateProduction(Long id, MovieProduction prod){
        if(productionRepository.findByID(id) == null){
            //throw exception
            throw new productionNotFoundException(id);
        }
        productionRepository.updateProduction(id, prod);
    }

}

