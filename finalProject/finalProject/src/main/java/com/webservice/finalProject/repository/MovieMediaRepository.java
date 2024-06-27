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
        String sql0 = "DELETE FROM movie_media WHERE title = ?";
        jdbcTemplate.update(sql0, movieMedia.getTitle());
        String sql1 = "INSERT INTO movie_media (title, poster_link) VALUES (?, ?)";
//        String sql1  = "UPDATE movie_media SET poster_link = ? WHERE title = ?";
        jdbcTemplate.update(sql1, movieMedia.getTitle(), movieMedia.getPosterLink());
    }

    public MovieMedia findByTitle(String title) {
        String sql = "SELECT * FROM movie_media WHERE title = ?";
        return jdbcTemplate.queryForObject(sql,  new MovieMediaRowMapper(),title);
    }
}

