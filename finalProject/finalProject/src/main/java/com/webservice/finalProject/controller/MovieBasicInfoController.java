package com.webservice.finalProject.controller;

import com.webservice.finalProject.model.MovieBasicInfo;
import com.webservice.finalProject.service.MovieBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieBasicInfoController {

    private final MovieBasicInfoService basicInfoService;

    @Autowired
    public MovieBasicInfoController(MovieBasicInfoService basicInfoService) {
        this.basicInfoService = basicInfoService;
    }

    @PostMapping("/{title}/basicInfo")
    public MovieBasicInfo saveBasicInfo(@PathVariable String title) {
        return basicInfoService.getBasicInfo(title);
    }

    @GetMapping("/{title}/basicInfo")
    public MovieBasicInfo getBasicInfo(@PathVariable String title) {
        return basicInfoService.getBasicInfo(title);
    }
}
