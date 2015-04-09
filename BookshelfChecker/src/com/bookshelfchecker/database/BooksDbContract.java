package com.bookshelfchecker.database;

import android.provider.BaseColumns;

public final class BooksDbContract {

        public BooksDbContract() {}
        
        /* Inner class that defines the table contents */
        public static abstract class BookEntry implements BaseColumns {
            public static final String TABLE_NAME = "book";
            public static final String COLUMN_NAME_ISBN = "isbn";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_VOLUME = "volume";
            public static final String COLUMN_NAME_COMPLETED = "completed";
            public static final String COLUMN_NAME_NULLABLE = "null";
        }        
}
