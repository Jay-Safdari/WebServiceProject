package com.complete.lastversion.adaptor;

import com.complete.lastversion.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class OMDbAdaptor {

    private final RestTemplate restTemplate;

    @Autowired
    public OMDbAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${omdbapi.url}")
    private String apiURL;

    @Value("${omdbapi.key}")
    private String apiKey;

    public MovieDTO getMovieInfoByTitle(final String title) {
        final String route = apiURL.concat("?apikey={key}&t={title}");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", apiKey);
        parameters.put("title", title);

        return restTemplate.getForObject(route, MovieDTO.class, parameters);
    }
}
