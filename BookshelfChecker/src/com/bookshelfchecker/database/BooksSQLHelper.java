package com.bookshelfchecker.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bookshelfchecker.database.BooksDbContract.BookEntry;

public class BooksSQLHelper extends SQLiteOpenHelper{
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String BOOL_TYPE = " BOOLEAN";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + BookEntry.TABLE_NAME + " (" +
        BookEntry._ID + " INTEGER PRIMARY KEY," +
        BookEntry.COLUMN_NAME_ISBN + TEXT_TYPE + COMMA_SEP +
        BookEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
        BookEntry.COLUMN_NAME_VOLUME + INTEGER_TYPE + COMMA_SEP +
        BookEntry.COLUMN_NAME_COMPLETED + BOOL_TYPE +
        " )";
    
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BookEntry.TABLE_NAME;
        
    private static final String SQL_REMOVE_ALL = "DELETE FROM " + BookEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mybooks.db";

    
    BooksSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
    public Cursor readOne(String title) {
        String[] colsReturn = {BookEntry.COLUMN_NAME_ISBN,
                   BookEntry.COLUMN_NAME_TITLE,
                   BookEntry.COLUMN_NAME_VOLUME,
                   BookEntry.COLUMN_NAME_COMPLETED};
        return getReadableDatabase().query(
                BookEntry.TABLE_NAME,  // The table to query
                colsReturn,            // The columns to return
                BookEntry.COLUMN_NAME_TITLE + "=\"" + title + "\"",  // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,                  // don't group the rows
                null,                  // don't filter by row groups
                BookEntry.COLUMN_NAME_VOLUME // order by row
                );
    }
    
    public Cursor readAll() {
        String[] colsReturn = {BookEntry.COLUMN_NAME_ISBN,
                   BookEntry.COLUMN_NAME_TITLE,
                   BookEntry.COLUMN_NAME_VOLUME,
                   BookEntry.COLUMN_NAME_COMPLETED};
        
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
        BookEntry.COLUMN_NAME_TITLE + " DESC";
        return getReadableDatabase().query(
                BookEntry.TABLE_NAME,  // The table to query
                colsReturn,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // don't group the rows
                null,                  // don't filter by row groups
                sortOrder              // The sort order
                );
    }
    
    public void write(ContentValues values) {
        getWritableDatabase().insert(
                BookEntry.TABLE_NAME, 
                BookEntry.COLUMN_NAME_NULLABLE, 
                values
                );
    }
    
    public void deleteAll() {
        getWritableDatabase().execSQL(SQL_REMOVE_ALL);
    }
    
}
