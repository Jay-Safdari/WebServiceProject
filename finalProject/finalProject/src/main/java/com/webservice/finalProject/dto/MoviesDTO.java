package com.webservice.finalProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MoviesDTO {

    @JsonProperty("Search")
    private List<MovieDTO> listMoviesIncludeTitle;
}
