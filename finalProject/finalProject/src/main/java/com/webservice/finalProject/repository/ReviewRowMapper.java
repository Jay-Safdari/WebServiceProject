package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {

    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Review.builder()
                .id(rs.getLong("id"))
                .author(rs.getString("author"))
                .content(rs.getString("content"))
                .movieId(rs.getLong("movie_id"))
                .build();
    }
}
