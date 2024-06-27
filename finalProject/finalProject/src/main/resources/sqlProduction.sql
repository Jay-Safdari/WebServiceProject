DROP TABLE movie_production ;

CREATE TABLE movie_production (
    title VARCHAR(255) PRIMARY KEY,
    director VARCHAR(255),
    production TEXT,
    language VARCHAR(255),
    country VARCHAR(255)
);