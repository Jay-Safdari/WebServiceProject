DROP TABLE movie_ratings ;

CREATE TABLE movie_ratings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    imdb_rating VARCHAR(10),
    imdb_votes VARCHAR(20)
);
