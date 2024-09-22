--liquibase formatted sql
--changeset iyiola.oluwatosin:20240811211200_seed_category_table

ALTER TABLE categories ALTER COLUMN image_url DROP NOT NULL;

INSERT INTO categories (id, name, image_url, created_at, updated_at) VALUES
  (gen_random_uuid(), 'Technology', NULL, now(), now()),
  (gen_random_uuid(), 'Science', NULL, now(), now()),
  (gen_random_uuid(), 'Health', NULL, now(), now()),
  (gen_random_uuid(), 'Travel', NULL, now(), now()),
  (gen_random_uuid(), 'Lifestyle', NULL, now(), now()),
  (gen_random_uuid(), 'Education', NULL, now(), now()),
  (gen_random_uuid(), 'Business', NULL, now(), now()),
  (gen_random_uuid(), 'Finance', NULL, now(), now()),
  (gen_random_uuid(), 'Entertainment', NULL, now(), now()),
  (gen_random_uuid(), 'Sports', NULL, now(), now());
