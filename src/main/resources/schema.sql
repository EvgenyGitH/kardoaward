DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS subscriptions CASCADE;
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS competitions CASCADE;
DROP TABLE IF EXISTS directions CASCADE;
DROP TABLE IF EXISTS participation_types CASCADE;
DROP TABLE IF EXISTS locations CASCADE;
DROP TABLE IF EXISTS competition_participation_types CASCADE;
DROP TABLE IF EXISTS competition_directions CASCADE;
DROP TABLE IF EXISTS competition_locations CASCADE;
DROP TABLE IF EXISTS applications CASCADE;
DROP TABLE IF EXISTS application_directions CASCADE; -- Добавляем строку для удаления таблицы application_directions

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
    phone VARCHAR,
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

-- Таблица направлений
CREATE TABLE IF NOT EXISTS directions (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT UQ_DIRECTION_NAME UNIQUE (name)
);

-- Таблица типов участия
CREATE TABLE IF NOT EXISTS participation_types (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT UQ_PARTICIPATION_TYPE_NAME UNIQUE (name)
);

-- Таблица локаций
CREATE TABLE IF NOT EXISTS locations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT UQ_LOCATION_NAME UNIQUE (name)
);

-- Таблица соревнований
CREATE TABLE IF NOT EXISTS competitions (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    competition_type VARCHAR NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL
);

-- Таблица связей соревнований и типов участия
CREATE TABLE IF NOT EXISTS competition_participation_types (
    competition_id BIGINT NOT NULL,
    participation_type_id BIGINT NOT NULL,
    CONSTRAINT fk_competition_participation FOREIGN KEY (competition_id) REFERENCES competitions (id) ON DELETE CASCADE,
    CONSTRAINT fk_participation_type FOREIGN KEY (participation_type_id) REFERENCES participation_types (id) ON DELETE CASCADE,
    PRIMARY KEY (competition_id, participation_type_id)
);

-- Таблица связей соревнований и направлений
CREATE TABLE IF NOT EXISTS competition_directions (
    competition_id BIGINT NOT NULL,
    direction_id BIGINT NOT NULL,
    CONSTRAINT fk_competition_direction FOREIGN KEY (competition_id) REFERENCES competitions (id) ON DELETE CASCADE,
    CONSTRAINT fk_direction FOREIGN KEY (direction_id) REFERENCES directions (id) ON DELETE CASCADE,
    PRIMARY KEY (competition_id, direction_id)
);

-- Таблица связей соревнований и локаций
CREATE TABLE IF NOT EXISTS competition_locations (
    competition_id BIGINT NOT NULL,
    location_id BIGINT NOT NULL,
    CONSTRAINT fk_competition_location FOREIGN KEY (competition_id) REFERENCES competitions (id) ON DELETE CASCADE,
    CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES locations (id) ON DELETE CASCADE,
    PRIMARY KEY (competition_id, location_id)
);

-- Таблица заявок
CREATE TABLE IF NOT EXISTS applications (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    competition_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    application_type VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    CONSTRAINT fk_competition FOREIGN KEY (competition_id) REFERENCES competitions (id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Таблица связей заявок и направлений
CREATE TABLE IF NOT EXISTS application_directions (
    application_id BIGINT NOT NULL,
    direction_id BIGINT NOT NULL,
    CONSTRAINT fk_application_direction_application FOREIGN KEY (application_id) REFERENCES applications (id) ON DELETE CASCADE,
    CONSTRAINT fk_application_direction_direction FOREIGN KEY (direction_id) REFERENCES directions (id) ON DELETE CASCADE,
    PRIMARY KEY (application_id, direction_id)
);
