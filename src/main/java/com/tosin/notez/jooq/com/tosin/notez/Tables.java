/*
 * This file is generated by jOOQ.
 */
package com.tosin.notez;


import com.tosin.notez.tables.Categories;
import com.tosin.notez.tables.Files;
import com.tosin.notez.tables.Notes;
import com.tosin.notez.tables.Users;

import javax.annotation.processing.Generated;


/**
 * Convenience access to all tables in the default schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.13"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>categories</code>.
     */
    public static final Categories CATEGORIES = Categories.CATEGORIES;

    /**
     * The table <code>files</code>.
     */
    public static final Files FILES = Files.FILES;

    /**
     * The table <code>notes</code>.
     */
    public static final Notes NOTES = Notes.NOTES;

    /**
     * The table <code>users</code>.
     */
    public static final Users USERS = Users.USERS;
}
