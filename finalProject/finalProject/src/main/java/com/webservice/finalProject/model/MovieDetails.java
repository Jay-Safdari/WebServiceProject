package com.webservice.finalProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDetails {

    private String title;       // Movie Title
    private String runtime;     // Runtime of the movie
    private String genre;       // Genre of the movie
    private String plot;        // Plot of the movie

}