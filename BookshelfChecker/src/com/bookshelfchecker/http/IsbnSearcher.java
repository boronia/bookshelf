package com.bookshelfchecker.http;

import java.io.IOException;

import org.json.JSONException;

import com.bookshelfchecker.model.Book;
import com.bookshelfchecker.util.JsonProcessor;


public final class IsbnSearcher {

    public static final String ISBNDB_ENDPOINT = "http://isbndb.com/api/v2/json/WBLWO9LY/book/";
    
    public static final Book searchIsbn(String isbn) {
        Book book = null;
        try {
            String res = BookHttpClient.httpGet(ISBNDB_ENDPOINT + isbn);
            book = JsonProcessor.parseBookInfo(isbn, res);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return book;
    }
    
}
