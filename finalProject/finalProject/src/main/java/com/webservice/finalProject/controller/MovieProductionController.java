package com.webservice.finalProject.controller;

import com.webservice.finalProject.model.MovieProduction;
import com.webservice.finalProject.service.MovieProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieProductionController {

    private final MovieProductionService productionService;

    @Autowired
    public MovieProductionController(MovieProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping("/{title}/production")
    public MovieProduction saveProduction(@PathVariable String title) {
        return productionService.saveProduction(title);
    }

    @GetMapping("/{title}/production")
    public MovieProduction getProduction(@PathVariable String title) {
        return productionService.getProduction(title);
    }

    @GetMapping("/production/country/{country}")
    public List<MovieProduction> getProductionsByCountry(@PathVariable String country) {
        return productionService.getProductionsByCountry(country);
    }

    @GetMapping("/production/language/{language}")
    public List<MovieProduction> getProductionsByLanguage(@PathVariable String language) {
        return productionService.getProductionsByLanguage(language);
    }

}
