package com.webservice.finalProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieBasicInfo {
    private String title;
    private String year;
    private String released;
}
