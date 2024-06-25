package com.webservice.finalProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieDTO {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Released")
    private String released;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Poster")
    private String posterLink;
}
