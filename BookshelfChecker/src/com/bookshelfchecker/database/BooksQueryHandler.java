package com.bookshelfchecker.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.bookshelfchecker.database.BooksDbContract.BookEntry;
import com.bookshelfchecker.model.Book;

public class BooksQueryHandler {

    private BooksSQLHelper sqlHelper;
    
    public BooksQueryHandler(Context context) {
        sqlHelper = new BooksSQLHelper(context);
    }
    
    public void writeToDb(Book book) {
        ContentValues values = new ContentValues();
        
        values.put(BookEntry.COLUMN_NAME_ISBN, book.getIsbn());
        values.put(BookEntry.COLUMN_NAME_TITLE, book.getTitle());
        values.put(BookEntry.COLUMN_NAME_VOLUME, book.getVolume());
        values.put(BookEntry.COLUMN_NAME_COMPLETED, Boolean.toString(book.isCompleted()));
        
        sqlHelper.write(values);
    }
    
    public Book getLatestBookByTitle(String title) {
    	Log.w("***** Get book: ****", title);
        Cursor c = sqlHelper.readOne(title);
        boolean resultNotEmpty = c.moveToLast();
        if (!resultNotEmpty) {
            return null;
        }
        return buildBook(c);
    }
    
    
    public ArrayList<Book> readAllFromDb() {
        Cursor c = sqlHelper.readAll();
        ArrayList<Book> allSavedBooks = new ArrayList<Book>();
        while (c.moveToNext())
        {
            Book book = buildBook(c);
            allSavedBooks.add(book);
        }
        return allSavedBooks;
    }
    
    public void deleteAllFromDb() {
        sqlHelper.deleteAll();
    }
    
    private Book buildBook(Cursor c) {
        int volume = c.getInt(c.getColumnIndex(BookEntry.COLUMN_NAME_VOLUME));
        String title = c.getString(c.getColumnIndex(BookEntry.COLUMN_NAME_TITLE));
    	Log.w("***loading from db***", title);
        String isCompletedStr = c.getString(c.getColumnIndex(BookEntry.COLUMN_NAME_COMPLETED));
        boolean isCompleted = Boolean.parseBoolean(isCompletedStr);
        String isbn = c.getString(c.getColumnIndex(BookEntry.COLUMN_NAME_ISBN));
        return new Book(isbn, title, volume, isCompleted);
    }
}
