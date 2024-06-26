/*
 * This file is generated by jOOQ.
 */
package com.tosin.notez.tables.records;


import com.tosin.notez.tables.Categories;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class CategoriesRecord extends UpdatableRecordImpl<CategoriesRecord> implements Record5<UUID, String, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>categories.id</code>.
     */
    public CategoriesRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>categories.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>categories.name</code>.
     */
    public CategoriesRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>categories.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>categories.image_url</code>.
     */
    public CategoriesRecord setImageUrl(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>categories.image_url</code>.
     */
    public String getImageUrl() {
        return (String) get(2);
    }

    /**
     * Setter for <code>categories.created_at</code>.
     */
    public CategoriesRecord setCreatedAt(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>categories.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>categories.updated_at</code>.
     */
    public CategoriesRecord setUpdatedAt(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>categories.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<UUID, String, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Categories.CATEGORIES.ID;
    }

    @Override
    public Field<String> field2() {
        return Categories.CATEGORIES.NAME;
    }

    @Override
    public Field<String> field3() {
        return Categories.CATEGORIES.IMAGE_URL;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Categories.CATEGORIES.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return Categories.CATEGORIES.UPDATED_AT;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getImageUrl();
    }

    @Override
    public LocalDateTime component4() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component5() {
        return getUpdatedAt();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getImageUrl();
    }

    @Override
    public LocalDateTime value4() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value5() {
        return getUpdatedAt();
    }

    @Override
    public CategoriesRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public CategoriesRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public CategoriesRecord value3(String value) {
        setImageUrl(value);
        return this;
    }

    @Override
    public CategoriesRecord value4(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public CategoriesRecord value5(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public CategoriesRecord values(UUID value1, String value2, String value3, LocalDateTime value4, LocalDateTime value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CategoriesRecord
     */
    public CategoriesRecord() {
        super(Categories.CATEGORIES);
    }

    /**
     * Create a detached, initialised CategoriesRecord
     */
    @ConstructorProperties({ "id", "name", "imageUrl", "createdAt", "updatedAt" })
    public CategoriesRecord(UUID id, String name, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(Categories.CATEGORIES);

        setId(id);
        setName(name);
        setImageUrl(imageUrl);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised CategoriesRecord
     */
    public CategoriesRecord(com.tosin.notez.tables.pojos.Categories value) {
        super(Categories.CATEGORIES);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setImageUrl(value.getImageUrl());
            setCreatedAt(value.getCreatedAt());
            setUpdatedAt(value.getUpdatedAt());
            resetChangedOnNotNull();
        }
    }
}
