create database laba1 charset utf8 COLLATE utf8_general_ci;
USE laba1;

CREATE TABLE necklace (
id INT AUTO_INCREMENT NOT NULL, PRIMARY KEY (id));

CREATE TABLE amethyst (
id INT AUTO_INCREMENT NOT NULL, size INT, transparency DOUBLE (4, 4), stone_type VARCHAR (10), price INT,
necklace_id INT, FOREIGN KEY (necklace_id) REFERENCES necklace (id), PRIMARY KEY (id));

CREATE TABLE opal (
id INT AUTO_INCREMENT NOT NULL, size INT, transparency DOUBLE (4, 4), stone_type VARCHAR (10), price INT,
necklace_id INT, FOREIGN KEY (necklace_id) REFERENCES necklace (id), PRIMARY KEY (id));

CREATE TABLE diamond (
id INT AUTO_INCREMENT NOT NULL, size INT, transparency DOUBLE (4, 4), stone_type VARCHAR (10), price INT,
necklace_id INT, FOREIGN KEY (necklace_id) REFERENCES necklace (id), PRIMARY KEY (id));