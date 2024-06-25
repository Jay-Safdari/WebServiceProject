package com.webservice.finalProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovieSearchResponseDTO {

    @JsonProperty("Search")
    private List<MovieDTO> searchResults;
}
