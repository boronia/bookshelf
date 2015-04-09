package com.bookshelfchecker.util;

import com.bookshelfchecker.model.Book;

public final class StringProcessor {

    /**
     * No handling Exceptions here, just plain string parsing.
     * Returns null if the line is blank or null
     * 
     * @param line - the title string from the isbn query result html
     * @return an instance of Book, with title and volume (if available)
     */
    public static final Book processTitle(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }
        String[] titleAndVolume = getTitleAndVolume(line);
        String volStr = null;
        // remove "Vol." from volume
        if (titleAndVolume.length >= 2) {
            volStr = titleAndVolume[1].trim();
        }
        Book book = new Book();
        book.setTitle(titleAndVolume[0]);
        if (volStr != null)
        {
            book.setVolume(Integer.parseInt(volStr));
        }
        return book;
    }
    
    private static final String[] getTitleAndVolume(String line) {
        String[] res = null;
        if (line.contains(", Vol.")) {
            res = line.split(", Vol. ");
        }
        else if (line.contains(", Volume")) {
            res = line.split(", Volume");
        }
        else
        {
            res = new String[] {line};
        }
        return res;
    }
}
