package com.webservice.finalProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {

    private Long id;
    private String author;
    private String content;
    private Long movieId;
}
