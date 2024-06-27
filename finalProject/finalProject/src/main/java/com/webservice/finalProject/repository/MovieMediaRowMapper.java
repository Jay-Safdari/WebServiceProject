package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.MovieMedia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMediaRowMapper implements RowMapper<MovieMedia> {
    @Override
    public MovieMedia mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MovieMedia.builder()
                .title(rs.getString("title"))
                .posterLink(rs.getString("poster_link"))
                .build();
    }
}

