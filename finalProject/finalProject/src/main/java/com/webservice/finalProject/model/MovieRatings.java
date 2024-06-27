package com.webservice.finalProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRatings {
    private String title;
    private String imdbRating;
    private String imdbVotes;
}
