DROP TABLE movie_ratings ;

CREATE TABLE movie_ratings (
    title VARCHAR(255) PRIMARY KEY,
    imdb_rating VARCHAR(10),
    imdb_votes VARCHAR(20)
);
