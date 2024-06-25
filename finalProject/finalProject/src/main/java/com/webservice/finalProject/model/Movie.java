package com.webservice.finalProject.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Movie {
    private int movieId;
    private String title;
    private LocalDate releaseDate;
}
