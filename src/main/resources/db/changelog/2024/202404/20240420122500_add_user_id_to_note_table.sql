--liquibase formatted sql
--changeset iyiola.oluwatosin:20240420122500_add_user_id_to_note_table

ALTER TABLE NOTES ADD COLUMN USER_ID UUID REFERENCES USERS(ID) NOT NULL;
