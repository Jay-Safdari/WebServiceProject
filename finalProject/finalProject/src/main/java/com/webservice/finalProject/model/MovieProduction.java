package com.webservice.finalProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieProduction {
    private String title;
    private String director;
    private String production;
    private String language;
    private String country;
}
