--liquibase formatted sql
--changeset iyiola.oluwatosin:20240411120600_create_users_table

CREATE TABLE IF NOT EXISTS users(
    id              UUID PRIMARY KEY,
    username        VARCHAR(30) NULL UNIQUE,
    email           VARCHAR(50) NOT NULL UNIQUE,
    role            VARCHAR(20) NOT NULL,
    password        TEXT NOT NULL,
    created_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL,
    updated_at      TIMESTAMP(3) without time zone DEFAULT (now() at time zone 'utc') NOT NULL
);
