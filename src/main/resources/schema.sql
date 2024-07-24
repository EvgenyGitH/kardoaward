DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users (
id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
email VARCHAR NOT NULL,
password VARCHAR NOT NULL,
nickname VARCHAR NOT NULL,
first_name VARCHAR NOT NULL,
last_name VARCHAR NOT NULL,
middle_name VARCHAR NOT NULL,
birthday TIMESTAMP NOT NULL,
country VARCHAR NOT NULL,
region VARCHAR NOT NULL,
city VARCHAR NOT NULL,
phone VARCHAR ,
photo_link VARCHAR,
background_link VARCHAR,
page_link VARCHAR,
about_me VARCHAR,
user_role VARCHAR NOT NULL,
state VARCHAR NOT NULL,

CONSTRAINT UQ_USER_EMAIL UNIQUE (email),
CONSTRAINT UQ_USER_NICKNAME UNIQUE (nickname)
);
