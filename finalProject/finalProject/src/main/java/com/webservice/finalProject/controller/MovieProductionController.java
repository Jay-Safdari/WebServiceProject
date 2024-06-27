package com.webservice.finalProject.controller;

import com.webservice.finalProject.model.MovieProduction;
import com.webservice.finalProject.service.MovieProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/production/{country}")
    public MovieProduction getProductionByCountry(@PathVariable String country) {
        return productionService.getProductionByCountry(country);
    }
}
