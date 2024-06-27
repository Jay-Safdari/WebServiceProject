package com.complete.lastversion.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieMedia {
    private String title;
    private String posterLink;
}
