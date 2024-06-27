package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDetailsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(MovieDetails movieDetails) {
        String sql = "INSERT INTO movie_details (title, runtime, genre, plot) VALUE (?, ?, ?, ?)";
        jdbcTemplate.update(sql, movieDetails.getTitle(), movieDetails.getGenre(), movieDetails.getPlot(), movieDetails.getRuntime());
    }

    public MovieDetails findByTitle(String title) {
        String sql = "SELECT * FROM movie_details WHERE title=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{title}, new MovieDetailsRowMapper());
    }
}
