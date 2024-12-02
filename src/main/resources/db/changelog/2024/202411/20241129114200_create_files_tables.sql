--liquibase formatted sql
--changeset iyiola.oluwatosin:20241129114200_create_files_tables

CREATE TABLE files (
    id              UUID PRIMARY KEY,
    title           VARCHAR(50) NOT NULL,
    filename        TEXT NOT NULL,
    url             TEXT NOT NULL,
    user_id         UUID REFERENCES USERS(ID) NOT NULL,
    type            VARCHAR(50),
    created_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL,
    updated_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL
)
