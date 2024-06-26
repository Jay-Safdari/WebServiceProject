package com.complete.lastversion.repository;

import com.complete.lastversion.model.MovieRatings;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRatingsRowMapper implements RowMapper<MovieRatings> {
    @Override
    public MovieRatings mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MovieRatings.builder()
                .title(rs.getString("title"))
                .imdbRating(rs.getString("imdb_rating"))
                .imdbVotes(rs.getString("imdb_votes"))
                .build();
    }
}

