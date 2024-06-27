package com.webservice.finalProject.repository;


import com.webservice.finalProject.model.MovieRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRatingsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieRatingsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(MovieRatings movieRatings) {
        String sql = "INSERT INTO movie_ratings (title, imdb_rating, imdb_votes) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, movieRatings.getTitle(), movieRatings.getImdbRating(), movieRatings.getImdbVotes());
    }

    public MovieRatings findByTitle(String title) {
        String sql = "SELECT * FROM movie_ratings WHERE title = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{title}, new MovieRatingsRowMapper());
    }
}

