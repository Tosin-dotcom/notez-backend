--liquibase formatted sql
--changeset iyiola.oluwatosin:20241130123200_modify_files_table

ALTER TABLE files DROP COLUMN url;
