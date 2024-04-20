--liquibase formatted sql
--changeset iyiola.oluwatosin:20240416143900_create_note_table

CREATE TABLE notes (
    id              UUID PRIMARY KEY,
    title           VARCHAR(50) NOT NULL,
    text_content    TEXT NOT NULL,
    category_id     UUID REFERENCES CATEGORIES(ID) NOT NULL,
    created_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL,
    updated_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL
)
