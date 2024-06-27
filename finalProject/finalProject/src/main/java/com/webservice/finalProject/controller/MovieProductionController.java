package com.webservice.finalProject.controller;

import com.webservice.finalProject.exception.productionNotFoundException;
import com.webservice.finalProject.model.MovieProduction;
import com.webservice.finalProject.service.MovieProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduction(@PathVariable Long id, @RequestBody MovieProduction prod){
        try{
            productionService.updateProduction(id, prod);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduction(@PathVariable Long id){
        try{
            productionService.deleteProduction(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (productionNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}
