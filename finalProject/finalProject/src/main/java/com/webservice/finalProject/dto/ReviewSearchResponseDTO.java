package com.webservice.finalProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReviewSearchResponseDTO {

    @JsonProperty("Reviews")
    private List<ReviewDTO> reviews;
}
