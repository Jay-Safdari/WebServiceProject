package com.webservice.finalProject.repository;


import com.webservice.finalProject.model.MovieMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieMediaRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieMediaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(MovieMedia movieMedia) {
        String sql = "INSERT INTO movie_media (title, poster_link) VALUES (?, ?)";
        jdbcTemplate.update(sql, movieMedia.getTitle(), movieMedia.getPosterLink());
    }

    public MovieMedia findByTitle(String title) {
        String sql = "SELECT * FROM movie_media WHERE title = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{title}, new MovieMediaRowMapper());
    }
}

