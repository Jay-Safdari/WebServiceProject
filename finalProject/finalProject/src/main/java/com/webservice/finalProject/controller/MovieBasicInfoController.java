package com.webservice.finalProject.controller;

import com.webservice.finalProject.model.MovieBasicInfo;
import com.webservice.finalProject.service.MovieBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieBasicInfoController {

    private final MovieBasicInfoService basicInfoService;

    @Autowired
    public MovieBasicInfoController(MovieBasicInfoService basicInfoService) {
        this.basicInfoService = basicInfoService;
    }

    @GetMapping("/{title}/basicInfo")
    public MovieBasicInfo getBasicInfo(@PathVariable String title) {
        return basicInfoService.getBasicInfo(title);
    }
}
