CREATE TABLE IF NOT EXISTS coffeeshop
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    address     VARCHAR(255) NOT NULL,
    phone       VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    website     VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    image       VARCHAR(255) NOT NULL,
    rating      INT          DEFAULT 0 CHECK (rating >= 0 AND rating <= 100),
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO coffeeshop (name, address, phone, email, website, description, image, rating)
VALUES ('Coffee Shop 1', '123 Main St, City, State, 12345', '123-456-7890', 'coffeeshop1@test.com','https://www.coffeeshop1.com', 'Coffee Shop 1 Description', 'coffeeshop1.jpg', 90),
       ('Coffee Shop 2', '456 Elm St, City, State, 12345', '123-456-7890', 'coffeeshop2@test.com','https://www.coffeeshop2.com', 'Coffee Shop 2 Description', 'coffeeshop2.jpg', 80),
       ('Coffee Shop 3', '789 Oak St, City, State, 12345', '123-456-7890', 'coffeeshop3@test.com','https://www.coffeeshop3.com', 'Coffee Shop 3 Description', 'coffeeshop3.jpg', 85);