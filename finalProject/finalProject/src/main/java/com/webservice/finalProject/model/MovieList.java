package com.webservice.finalProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieList {

    private String titleList;
    private String yearList;
    private String posterLinkList;
}
