package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.MovieProduction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieProductionRowMapper implements RowMapper<MovieProduction> {
    @Override
    public MovieProduction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MovieProduction.builder()
                .title(rs.getString("title"))
                .director(rs.getString("director"))
                .production(rs.getString("production"))
                .language(rs.getString("language"))
                .country(rs.getString("country"))
                .build();
    }
}

