package com.webservice.finalProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReviewDTO {

    @JsonProperty("Author")
    private String author;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("MovieTitle")
    private String movieTitle;
}
