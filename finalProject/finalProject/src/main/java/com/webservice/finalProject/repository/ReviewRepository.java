package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Review> getAllReviews() {
        String sql = "SELECT * FROM reviews";
        return jdbcTemplate.query(sql, new ReviewRowMapper());
    }

    public Review getReviewById(Long id) {
        String sql = "SELECT * FROM reviews WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ReviewRowMapper());
    }

    public int saveReview(Review review) {
        String sql = "INSERT INTO reviews (author, content, movie_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, review.getAuthor(), review.getContent(), review.getMovieId());
    }

    public int deleteReview(Long id) {
        String sql = "DELETE FROM reviews WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
