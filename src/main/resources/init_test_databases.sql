create database laba1_test charset utf8 COLLATE utf8_general_ci;
USE laba1_test;

CREATE TABLE necklace (
id INT AUTO_INCREMENT NOT NULL, PRIMARY KEY (id));

CREATE TABLE amethyst (
id INT AUTO_INCREMENT NOT NULL, size INT, transparency FLOAT (8), stone_type VARCHAR (15), price INT,
necklace_id INT, FOREIGN KEY (necklace_id) REFERENCES necklace (id), PRIMARY KEY (id));

CREATE TABLE opal (
id INT AUTO_INCREMENT NOT NULL, size INT, transparency FLOAT (8), stone_type VARCHAR (15), price INT,
necklace_id INT, FOREIGN KEY (necklace_id) REFERENCES necklace (id), PRIMARY KEY (id));

CREATE TABLE diamond (
id INT AUTO_INCREMENT NOT NULL, size INT, transparency FLOAT (8), stone_type VARCHAR (15), price INT,
necklace_id INT, FOREIGN KEY (necklace_id) REFERENCES necklace (id), PRIMARY KEY (id));

insert into necklace (id) values (1);
INSERT INTO diamond (size, transparency, stone_type, price, necklace_id) VALUES (95, 55.5, 'PRECIOUS', 251, 1 );
INSERT INTO opal (size, transparency, stone_type, price, necklace_id) VALUES (72, 35.5, 'SEMIPRECIOUS', 151, 1 );
INSERT INTO amethyst (size, transparency, stone_type, price, necklace_id) VALUES (85, 71.0, 'PRECIOUS', 231, 1 );