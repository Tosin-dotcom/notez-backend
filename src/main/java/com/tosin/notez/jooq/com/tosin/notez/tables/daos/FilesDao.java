/*
 * This file is generated by jOOQ.
 */
package com.tosin.notez.tables.daos;


import com.tosin.notez.tables.Files;
import com.tosin.notez.tables.records.FilesRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.13"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Repository
public class FilesDao extends DAOImpl<FilesRecord, com.tosin.notez.tables.pojos.Files, UUID> {

    /**
     * Create a new FilesDao without any configuration
     */
    public FilesDao() {
        super(Files.FILES, com.tosin.notez.tables.pojos.Files.class);
    }

    /**
     * Create a new FilesDao with an attached configuration
     */
    @Autowired
    public FilesDao(Configuration configuration) {
        super(Files.FILES, com.tosin.notez.tables.pojos.Files.class, configuration);
    }

    @Override
    public UUID getId(com.tosin.notez.tables.pojos.Files object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Files.FILES.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchById(UUID... values) {
        return fetch(Files.FILES.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.tosin.notez.tables.pojos.Files fetchOneById(UUID value) {
        return fetchOne(Files.FILES.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<com.tosin.notez.tables.pojos.Files> fetchOptionalById(UUID value) {
        return fetchOptional(Files.FILES.ID, value);
    }

    /**
     * Fetch records that have <code>title BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchRangeOfTitle(String lowerInclusive, String upperInclusive) {
        return fetchRange(Files.FILES.TITLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>title IN (values)</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchByTitle(String... values) {
        return fetch(Files.FILES.TITLE, values);
    }

    /**
     * Fetch records that have <code>filename BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchRangeOfFilename(String lowerInclusive, String upperInclusive) {
        return fetchRange(Files.FILES.FILENAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>filename IN (values)</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchByFilename(String... values) {
        return fetch(Files.FILES.FILENAME, values);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchRangeOfUserId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Files.FILES.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchByUserId(UUID... values) {
        return fetch(Files.FILES.USER_ID, values);
    }

    /**
     * Fetch records that have <code>type BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchRangeOfType(String lowerInclusive, String upperInclusive) {
        return fetchRange(Files.FILES.TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchByType(String... values) {
        return fetch(Files.FILES.TYPE, values);
    }

    /**
     * Fetch records that have <code>created_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchRangeOfCreatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Files.FILES.CREATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchByCreatedAt(LocalDateTime... values) {
        return fetch(Files.FILES.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchRangeOfUpdatedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Files.FILES.UPDATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<com.tosin.notez.tables.pojos.Files> fetchByUpdatedAt(LocalDateTime... values) {
        return fetch(Files.FILES.UPDATED_AT, values);
    }
}