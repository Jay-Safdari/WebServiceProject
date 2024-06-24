package com.webservice.finalProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {

    private String title;
    private String released;
    private String runtime;
    private String plot;
    private String posterLink;
}
