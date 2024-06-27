package com.complete.lastversion.repository;

import com.complete.lastversion.model.MovieProduction;
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
        String sql = "INSERT INTO movie_production (title, director, actors, language, country) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, movieProduction.getTitle(), movieProduction.getDirector(), movieProduction.getActors(), movieProduction.getLanguage(), movieProduction.getCountry());
    }

    public MovieProduction findByTitle(String title) {
        String sql = "SELECT * FROM movie_production WHERE title = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{title}, new MovieProductionRowMapper());
    }
}

