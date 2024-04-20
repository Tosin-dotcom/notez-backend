--liquibase formatted sql
--changeset iyiola.oluwatosin:20240415022700_add_first_name_last_name_to_user_table

ALTER TABLE USERS ADD COLUMN first_name VARCHAR(50) NOT NULL DEFAULT 'john';
ALTER TABLE USERS ADD COLUMN last_name VARCHAR(50) NOT NULL DEFAULT 'doe';
