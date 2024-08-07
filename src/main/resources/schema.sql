
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS subscriptions CASCADE;
DROP TABLE IF EXISTS messages CASCADE;

CREATE TABLE IF NOT EXISTS users (
id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
email VARCHAR NOT NULL,
password VARCHAR NOT NULL,
nickname VARCHAR NOT NULL,
first_name VARCHAR NOT NULL,
last_name VARCHAR NOT NULL,
middle_name VARCHAR,
birthday TIMESTAMP NOT NULL,
country VARCHAR NOT NULL,
region VARCHAR NOT NULL,
city VARCHAR NOT NULL,
citizenship VARCHAR,
gender VARCHAR,
phone VARCHAR ,
photo_link VARCHAR,
background_link VARCHAR,
page_link VARCHAR,
about_me VARCHAR,
style VARCHAR,
user_role VARCHAR NOT NULL,
state VARCHAR NOT NULL,
CONSTRAINT UQ_USER_EMAIL UNIQUE (email),
CONSTRAINT UQ_USER_NICKNAME UNIQUE (nickname)
);


CREATE TABLE IF NOT EXISTS subscriptions (
id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
follower_id BIGINT NOT NULL,
following_id BIGINT NOT NULL,
CONSTRAINT fk_follower FOREIGN KEY (follower_id) REFERENCES users (id) ON DELETE CASCADE,
CONSTRAINT fk_following FOREIGN KEY (following_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS messages (
id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
sender_id BIGINT NOT NULL,
recipient_id BIGINT NOT NULL,
text VARCHAR NOT NULL,
date_message TIMESTAMP NOT NULL,
was_read BOOLEAN DEFAULT FALSE,

CONSTRAINT fk_sender FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE CASCADE,
CONSTRAINT fk_recipient FOREIGN KEY (recipient_id) REFERENCES users (id) ON DELETE CASCADE
);