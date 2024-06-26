package com.webservice.finalProject.repository;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Movie.builder()
                .title(rs.getString("title"))
                .released(rs.getString("released"))
                .runtime(rs.getString("runtime"))
                .plot(rs.getString("plot"))
                .posterLink(rs.getString("poster"))
                .build();

    }
}
