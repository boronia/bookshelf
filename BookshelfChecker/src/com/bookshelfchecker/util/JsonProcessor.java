package com.bookshelfchecker.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.bookshelfchecker.model.Book;

public final class JsonProcessor {

    
    public static final Book parseBookInfo(String isbn, String json) throws JSONException{
        Book book = null; 
        Log.w("***JsonProcessor***", json);
        JSONObject jo = new JSONObject(json);
        JSONArray data =  (JSONArray) jo.get("data");
        JSONObject dataObj = (JSONObject) data.get(0);
        String title = dataObj.getString("title");
        book = StringProcessor.processTitle(title);
        book.setIsbn(isbn);
        book.setCompleted(false);
        return book;
    }
    
}
