package com.webservice.finalProject.repository;

import com.webservice.finalProject.model.MovieBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieBasicInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieBasicInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(MovieBasicInfo movieBasicInfo) {
        String sql = "INSERT INTO movie_basic_info (title, year, released) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, movieBasicInfo.getTitle(), movieBasicInfo.getYear(), movieBasicInfo.getReleased());
    }

    public MovieBasicInfo findByTitle(String title) {
        String sql = "SELECT * FROM movie_basic_info WHERE title = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{title}, new MovieBasicInfoRowMapper());
    }
}
