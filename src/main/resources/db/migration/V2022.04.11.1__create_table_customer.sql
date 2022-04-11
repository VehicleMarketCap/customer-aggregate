CREATE TABLE customer(
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(36) NOT NULL,
    surname VARCHAR(36) NOT NULL,
    birthdate DATE NOT NULL,
    phone_number VARCHAR(12) NOT NULL UNIQUE,
    email VARCHAR(36) NOT NULL UNIQUE
);