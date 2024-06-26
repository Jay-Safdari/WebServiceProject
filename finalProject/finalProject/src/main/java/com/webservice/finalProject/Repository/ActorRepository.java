package com.webservice.finalProject.Repository;

import com.webservice.finalProject.model.Actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Repository
public class ActorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final class ActorRowMapper implements RowMapper<Actor> {
        @Override
        public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Actor actor = new Actor();
            actor.setId(rs.getLong("id"));
            actor.setFirstName(rs.getString("first_name"));
            actor.setLastName(rs.getString("last_name"));
            actor.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            // need to get  movie_ids FROM actor_movies
            String movieIdsQuery = "SELECT movie_id FROM actor_movies WHERE actor_id = ?";
            List<Integer> movieIds = jdbcTemplate.query(movieIdsQuery, (rs1, rowNum1) -> rs1.getInt("movie_id"), actor.getId());
            actor.setMovieIds(movieIds);
            return actor;
        }
    }

    public List<Actor> findAll() {
        String sql = "SELECT * FROM actors";
        return jdbcTemplate.query(sql, new ActorRowMapper());
    }

    public Actor findById(int id) {
        String sql = "SELECT * FROM actors WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ActorRowMapper(), id);

    }

    public List<Actor> findByNameActors(String firstName, String lastName) {
        String sql = "SELECT * FROM actors WHERE first_name = ? AND last_name = ?";
        return jdbcTemplate.query(sql, new ActorRowMapper(), firstName, lastName);
    }

    public Actor findLastInsertedActor() {
        String sql = "SELECT * FROM actors ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new ActorRowMapper());
    }

    public int save(Actor actor) {
        List<Actor> existedActors = findByNameActors(actor.getFirstName(), actor.getLastName());

        // If no actor with the same firstName and lastName exists
        if (existedActors.isEmpty()) {
            String insertSql = "INSERT INTO actors (first_name, last_name, date_of_birth, biography) VALUES (?, ?, ?, ?)";
            int updateResult = jdbcTemplate.update(insertSql, actor.getFirstName(), actor.getLastName(), actor.getDateOfBirth(), actor.getBiography());

            if (updateResult > 0) { // Check if insert was successful
                Actor lastInsertActor = findLastInsertedActor();
                if (lastInsertActor != null) {
                    long newInsertActorId = lastInsertActor.getId();
                    actor.setId(newInsertActorId);
                    insertNewActorMovies(actor);
                }
            }
        } else {
            // Else, update the actor-movies table for existing actors
            for (Actor existingActor : existedActors) {
                long existingActorId = existingActor.getId();
                actor.setId(existingActorId);
                insertExistingActorMovies(actor);
            }
        }

        return 1;
    }

    public int update(Actor actor) {
        String sql = "UPDATE actors SET first_name = ?, last_name = ?, date_of_birth = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, actor.getFirstName(), actor.getLastName(), actor.getDateOfBirth(), actor.getId());
        if (result > 0) {
            deleteActorMovies(Math.toIntExact(actor.getId()));
            insertNewActorMovies(actor);
        }
        return result;
    }

    public int deleteById(int id) {
        deleteActorMovies(id);
        String sql = "DELETE FROM actors WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private void insertNewActorMovies(Actor actor) {
        String sql = "INSERT INTO actor_movies (actor_id, movie_id) VALUES (?, ?)";
        for (Integer movieId : actor.getMovieIds()) {
            jdbcTemplate.update(sql, actor.getId(), movieId);
        }
    }

    private void insertExistingActorMovies(Actor actor) {
        String checkSql = "SELECT COUNT(id) FROM actor_movies WHERE actor_id = ? AND movie_id = ?";
        String insertSql = "INSERT INTO actor_movies (actor_id, movie_id) VALUES (?, ?)";
        if (!actor.getMovieIds().isEmpty()) {
            for (Integer movieId : actor.getMovieIds()) {

                Integer existedActorMovies = null;

                try {
                    existedActorMovies = jdbcTemplate.queryForObject(checkSql, Integer.class, actor.getId(), movieId);
                } catch (EmptyResultDataAccessException e) {
                    // Handle the case where no result is found
                } catch (DataAccessException e) {
                    // Handle general data access exceptions
                    System.out.println("Error executing query: " + e.getMessage());
                    return;
                }

                if (existedActorMovies != null && existedActorMovies == 0) {
                    jdbcTemplate.update(insertSql, actor.getId(), movieId);
                }
            }
        }
    }

    private void deleteActorMovies(int actorId) {
        String sql = "DELETE FROM actor_movies WHERE actor_id = ?";
        jdbcTemplate.update(sql, actorId);
    }



    public List<Actor> findByNameActorsFuzzy(String firstName, String lastName) {
        Function<String, String> truncateString = s -> s.length() > 2 ? s.substring(0, 2) : s;
        if ((firstName == null || firstName.isEmpty()) && (lastName == null || lastName.isEmpty())) {
            String findAllSql = "SELECT * FROM actors";
            List<Actor> foundByNameAll = jdbcTemplate.query(findAllSql, new ActorRowMapper());
            return foundByNameAll;
        } else {
            if (firstName == null || firstName.isEmpty()) {
                String fuzzySql0 = "SELECT * FROM actors WHERE first_name = ? OR last_name = ?";
                List<Actor> foundByNameFuzzy0 = jdbcTemplate.query(fuzzySql0, new ActorRowMapper(), lastName, lastName);
                return foundByNameFuzzy0;
            } else if (lastName == null || lastName.isEmpty()) {
                String fuzzySql1 = "SELECT * FROM actors WHERE first_name = ? OR last_name = ?";
                List<Actor> foundByNameFuzzy1 = jdbcTemplate.query(fuzzySql1, new ActorRowMapper(), firstName, firstName);
                return foundByNameFuzzy1;
            } else {
                String strictSql = "SELECT * FROM actors WHERE first_name = ? AND last_name = ?";
                List<Actor> foundByNameStrict = jdbcTemplate.query(strictSql, new ActorRowMapper(), firstName, lastName);
                if (!foundByNameStrict.isEmpty() && foundByNameStrict.size() > 1) {
                    return foundByNameStrict;
                } else {
                    String fuzzySql0 = "SELECT * FROM actors WHERE first_name LIKE ? OR last_name LIKE ?";
                    String firstNamePattern0 = "%" + truncateString.apply(firstName) + "%";
                    String lastNamePattern0 = "%" + truncateString.apply(lastName) + "%";
                    String fuzzySql1 = "SELECT * FROM actors WHERE first_name LIKE ? OR last_name LIKE ?";
                    String firstNamePattern1 = "%" + truncateString.apply(firstName) + "%";
                    String lastNamePattern1 = "%" + truncateString.apply(lastName) + "%";
                    List<Actor> foundActorsFuzzy0 = jdbcTemplate.query(fuzzySql0, new ActorRowMapper(), firstNamePattern0, lastNamePattern0);
                    List<Actor> foundActorsFuzzy1 = jdbcTemplate.query(fuzzySql1, new ActorRowMapper(), lastNamePattern1, firstNamePattern1);
                    List<Actor> result = new ArrayList<>(foundActorsFuzzy0);
                    result.addAll(foundActorsFuzzy1);
                    return result;
                }
            }
        }
    }


}

