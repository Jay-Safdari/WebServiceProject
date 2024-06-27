package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.MovieDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDetailsRowMapper implements RowMapper<MovieDetails> {
    @Override
    public MovieDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MovieDetails.builder()
                .title(rs.getString("title"))
                .runtime(rs.getString("runtime"))
                .genre(rs.getString("genre"))
                .plot(rs.getString("plot"))
                .build();
    }

}
