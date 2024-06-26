package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.MovieBasicInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieBasicInfoRowMapper implements RowMapper<MovieBasicInfo> {
    @Override
    public MovieBasicInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MovieBasicInfo.builder()
                .title(rs.getString("title"))
                .year(rs.getString("year"))
                .released(rs.getString("released"))
                .build();
    }
}
