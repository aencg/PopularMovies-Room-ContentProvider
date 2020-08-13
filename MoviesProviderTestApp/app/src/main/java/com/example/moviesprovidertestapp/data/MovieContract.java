package com.example.moviesprovidertestapp.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract implements BaseColumns {

    public static final String CONTENT_AUTHORITY = "com.example.popularmovies.provider";

    /**
     * This is the {@link Uri} on which all other DroidTermsExample Uris are built.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIES = "movies";

    /**
     * This is the {@link Uri} used to get a full list of terms and definitions.
     */
    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();


    /**
     * This is a String type that denotes a Uri references a list or directory.
     */
    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

    /**
     * This is a String type that denotes a Uri references a single item.
     */
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;


    // Declaring all these as constants makes code a lot more readable.
    // It also looks a more like SQL.

    /**
     * This is the version of the database for {@link android.database.sqlite.SQLiteOpenHelper}.
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * This is the name of the SQL table for terms.
     */
    public static final String TERMS_TABLE = "term_entries";
    /**
     * This is the name of the SQL database for terms.
     */
    public static final String DATABASE_NAME = "terms";



    public static final String COLUMN_ID = Movie.MOVIE_ID;
    public static final String COLUMN_TITLE = Movie.MOVIE_TITLE;
    public static final String COLUMN_RELEASE_DATE = Movie.MOVIE_RELEASE_DATE;
    public static final String COLUMN_POSTER = Movie.MOVIE_POSTER;
    public static final String COLUMN_VOTE_AVERAGE = Movie.MOVIE_VOTE_AVERAGE;
    public static final String COLUMN_SYNOPSIS = Movie.MOVIE_SYNOPSIS;

    /**
     * This is an array containing all the column headers in the terms table.
     */
    public static final String[] COLUMNS =
            {COLUMN_ID, COLUMN_TITLE, COLUMN_RELEASE_DATE, COLUMN_POSTER, COLUMN_VOTE_AVERAGE, COLUMN_SYNOPSIS};

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_TITLE = 1;
    public static final int COLUMN_INDEX_RELEASE_DATE = 2;
    public static final int COLUMN_INDEX_POSTER = 3;
    public static final int COLUMN_INDEX_VOTE_AVERAGE= 4;
    public static final int COLUMN_INDEX_SYNOPSIS = 5;

    /**
     * This method creates a {@link Uri} for a single term, referenced by id.
     * @param id The id of the term.
     * @return The Uri with the appended id.
     */
    public static Uri buildTermUriWithId(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }
    public static Uri buildTermUriWithId(String id) {
        return Uri.withAppendedPath(CONTENT_URI, id);
    }

}
