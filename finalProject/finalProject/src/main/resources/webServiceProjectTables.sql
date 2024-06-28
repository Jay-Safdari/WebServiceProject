CREATE TABLE movie_basic_info (
    title VARCHAR(255) PRIMARY KEY,
    year VARCHAR(4),
    released VARCHAR(255)
);

CREATE TABLE movie_details (
    title VARCHAR(255) PRIMARY KEY,
    runtime VARCHAR(255),
    genre VARCHAR(255),
    plot TEXT
);


