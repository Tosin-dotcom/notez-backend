/*
 * This file is generated by jOOQ.
 */
package com.tosin.notez.tables.records;


import com.tosin.notez.tables.Files;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


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
public class FilesRecord extends UpdatableRecordImpl<FilesRecord> implements Record7<UUID, String, String, UUID, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>files.id</code>.
     */
    public FilesRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>files.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>files.title</code>.
     */
    public FilesRecord setTitle(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>files.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>files.filename</code>.
     */
    public FilesRecord setFilename(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>files.filename</code>.
     */
    public String getFilename() {
        return (String) get(2);
    }

    /**
     * Setter for <code>files.user_id</code>.
     */
    public FilesRecord setUserId(UUID value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>files.user_id</code>.
     */
    public UUID getUserId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>files.type</code>.
     */
    public FilesRecord setType(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>files.type</code>.
     */
    public String getType() {
        return (String) get(4);
    }

    /**
     * Setter for <code>files.created_at</code>.
     */
    public FilesRecord setCreatedAt(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>files.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>files.updated_at</code>.
     */
    public FilesRecord setUpdatedAt(LocalDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>files.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<UUID, String, String, UUID, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<UUID, String, String, UUID, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Files.FILES.ID;
    }

    @Override
    public Field<String> field2() {
        return Files.FILES.TITLE;
    }

    @Override
    public Field<String> field3() {
        return Files.FILES.FILENAME;
    }

    @Override
    public Field<UUID> field4() {
        return Files.FILES.USER_ID;
    }

    @Override
    public Field<String> field5() {
        return Files.FILES.TYPE;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Files.FILES.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Files.FILES.UPDATED_AT;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getFilename();
    }

    @Override
    public UUID component4() {
        return getUserId();
    }

    @Override
    public String component5() {
        return getType();
    }

    @Override
    public LocalDateTime component6() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component7() {
        return getUpdatedAt();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getFilename();
    }

    @Override
    public UUID value4() {
        return getUserId();
    }

    @Override
    public String value5() {
        return getType();
    }

    @Override
    public LocalDateTime value6() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value7() {
        return getUpdatedAt();
    }

    @Override
    public FilesRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public FilesRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public FilesRecord value3(String value) {
        setFilename(value);
        return this;
    }

    @Override
    public FilesRecord value4(UUID value) {
        setUserId(value);
        return this;
    }

    @Override
    public FilesRecord value5(String value) {
        setType(value);
        return this;
    }

    @Override
    public FilesRecord value6(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public FilesRecord value7(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public FilesRecord values(UUID value1, String value2, String value3, UUID value4, String value5, LocalDateTime value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FilesRecord
     */
    public FilesRecord() {
        super(Files.FILES);
    }

    /**
     * Create a detached, initialised FilesRecord
     */
    @ConstructorProperties({ "id", "title", "filename", "userId", "type", "createdAt", "updatedAt" })
    public FilesRecord(UUID id, String title, String filename, UUID userId, String type, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(Files.FILES);

        setId(id);
        setTitle(title);
        setFilename(filename);
        setUserId(userId);
        setType(type);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised FilesRecord
     */
    public FilesRecord(com.tosin.notez.tables.pojos.Files value) {
        super(Files.FILES);

        if (value != null) {
            setId(value.getId());
            setTitle(value.getTitle());
            setFilename(value.getFilename());
            setUserId(value.getUserId());
            setType(value.getType());
            setCreatedAt(value.getCreatedAt());
            setUpdatedAt(value.getUpdatedAt());
            resetChangedOnNotNull();
        }
    }
}
