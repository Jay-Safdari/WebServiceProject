package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.MovieProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieProductionRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieProductionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(MovieProduction movieProduction) {
        String sql = "INSERT INTO movie_production (title, director, production, language, country) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, movieProduction.getTitle(), movieProduction.getDirector(), movieProduction.getProduction(), movieProduction.getLanguage(), movieProduction.getCountry());
    }

    public MovieProduction findByTitle(String title) {
        String sql = "SELECT * FROM movie_production WHERE title = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{title}, new MovieProductionRowMapper());
    }

    public MovieProduction getProductionByCountry(String country) {
        String sql = "SELECT * FROM movie_production WHERE country = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{country}, new MovieProductionRowMapper());
    }

}


