-- Création de la base de données
CREATE DATABASE commerce_api;

-- Connexion à la base de données
\c commerce_api;

-- Création de la table users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    occupation VARCHAR(50),
    password VARCHAR(50) NOT NULL
);

-- Création de la table product
CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    status VARCHAR(50) CHECK (status IN ('append', 'available', 'unavailable')),
    price FLOAT NOT NULL CHECK (price >= 0),
    category VARCHAR(100),
    description VARCHAR(500)
);

-- Création de la table review
CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    author VARCHAR(50) NOT NULL,
    id_product INT REFERENCES product(id),
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment VARCHAR(100)
);

-- Création de la table promo
CREATE TABLE promo (
    id SERIAL PRIMARY KEY,
    id_product INT REFERENCES product(id),
    expiration_date DATE NOT NULL,
    category VARCHAR(50)
);

-- Création de la table client
CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE
);

-- Création de la table orders
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_date DATE NOT NULL,
    status BOOLEAN NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    total_price FLOAT NOT NULL CHECK (total_price >= 0),
    id_client INT REFERENCES client(id),
    id_product INT REFERENCES product(id)
);

-- Création de la table cart
CREATE TABLE cart (
    id SERIAL PRIMARY KEY,
    reference INT UNIQUE NOT NULL,
    type_cart VARCHAR(50),
    id_client INT REFERENCES client(id) ON DELETE CASCADE
);































