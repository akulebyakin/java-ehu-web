CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY,          -- Auto-incremented primary key
    name  VARCHAR(100) NOT NULL,       -- Name of the user, max length 100 characters
    email VARCHAR(100) NOT NULL UNIQUE -- Email of the user, must be unique
);


INSERT INTO users (name, email)
VALUES ('John Doe', 'john.doe@example.com'),
       ('Jane Smith', 'jane.smith@example.com'),
       ('Alice Johnson', 'alice.johnson@example.com'),
       ('Bob Brown', 'bob.brown@example.com');
