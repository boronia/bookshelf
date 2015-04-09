package com.bookshelfchecker.http;

import android.os.AsyncTask;
import android.util.Log;

import com.bookshelfchecker.activity.Home;
import com.bookshelfchecker.model.Book;

public class IsbnSearchAsyncTask extends AsyncTask<String, String, Book>{

    private Home home = null;
    
    public IsbnSearchAsyncTask(Home home) {
        super();
        this.home = home;
    }
    
    @Override
    protected Book doInBackground(String... isbn) {
    	Log.w("********", isbn[0]);
        return IsbnSearcher.searchIsbn(isbn[0]);
    }
    
    @Override
    protected void onPostExecute(Book result) {
        super.onPostExecute(result);
        home.handleIsbnResult(result);        
    }

}
