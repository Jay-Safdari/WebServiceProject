package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.MovieProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public MovieProduction findByID(long id) {
        String sql = "SELECT * FROM movie_production WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MovieProductionRowMapper());
    }

    public List<MovieProduction> getProductionsByCountry(String country) {
        String sql = "SELECT * FROM movie_production WHERE country LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + country + "%"}, new MovieProductionRowMapper());
    }

    public List<MovieProduction> getProductionsByLanguage(String language) {
        String sql = "SELECT * FROM movie_production WHERE language LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + language + "%"}, new MovieProductionRowMapper());
    }


    public void updateProduction(Long id, MovieProduction prod){
        String sql = "UPDATE movie_production set title=?, director =?, production=? , language=?, country=? WHERE id=?";
        jdbcTemplate.update(sql, prod.getTitle(), prod.getDirector(), prod.getProduction(), prod.getLanguage(), prod.getCountry(), id);
    }

    public void deleteProduction(Long id){
        String sql = "DELETE FROM movie_production WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

}


