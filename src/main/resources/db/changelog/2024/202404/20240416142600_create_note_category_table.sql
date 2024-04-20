--liquibase formatted sql
--changeset iyiola.oluwatosin:20240416142600_create_note_category_table

CREATE TABLE categories (
    id              UUID PRIMARY KEY,
    name            VARCHAR(50) NOT NULL UNIQUE,
    image_url       VARCHAR(50) NOT NULL,
    created_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL,
    updated_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL
)
