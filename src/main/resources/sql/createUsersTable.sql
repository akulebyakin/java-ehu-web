CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,           -- Auto-incremented primary key
    name     VARCHAR(100) NOT NULL,        -- Name of the user, max length 100 characters
    email    VARCHAR(100) NOT NULL UNIQUE, -- Email of the user, must be unique
    login    VARCHAR(50)  NOT NULL UNIQUE, -- Login of the user, must be unique
    password VARCHAR(255) NOT NULL         -- Password of the user, stored as a hashed value
);

INSERT INTO users (name, email, login, password)
VALUES ('John Doe', 'john.doe@example.com', 'john.doe', '$2a$10$EB325UxgC5/jzhcshbjHNuG7O7vl3DPNZP0SW9LcLqFgqFfP.WuW6'),
       ('Jane Smith', 'jane.smith@example.com', 'jane.smith', '$2a$10$EB325UxgC5/jzhcshbjHNuG7O7vl3DPNZP0SW9LcLqFgqFfP.WuW6'),
       ('Alice Johnson', 'alice.johnson@example.com', 'alice.johnson', '$2a$10$EB325UxgC5/jzhcshbjHNuG7O7vl3DPNZP0SW9LcLqFgqFfP.WuW6'),
       ('Bob Brown', 'bob.brown@example.com', 'bob.brown', '$2a$10$EB325UxgC5/jzhcshbjHNuG7O7vl3DPNZP0SW9LcLqFgqFfP.WuW6');