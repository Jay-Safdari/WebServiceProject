package com.webservice.finalProject.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Actor {

    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String biography;
    private List<Integer> movieIds;
}

