package com.bookshelfchecker.util;

import java.util.ArrayList;
import java.util.ListIterator;

import com.bookshelfchecker.model.Book;

public final class ListDisplayProcessor {

    public static final String[] getTitlesForDisplay(ArrayList<Book> bookList) {
    	if (bookList == null || bookList.isEmpty()) {
    		return new String[] {"Database Empty. Scan in some items!"};
    	}
        ListIterator<Book> itr = bookList.listIterator();
        ArrayList<String> displayStrList = new ArrayList<String>();
        while (itr.hasNext()) {
        	Book book = itr.next();
        	String displayString = book.getTitle() + " (" + book.getVolume() + ")"; 
        	displayStrList.add(displayString);       	
        }
        return displayStrList.toArray(new String[displayStrList.size()]);
    }
}
