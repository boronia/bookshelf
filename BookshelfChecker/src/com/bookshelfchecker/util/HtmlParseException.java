package com.bookshelfchecker.util;

public class HtmlParseException extends Exception {
    
    private static final long serialVersionUID = 1L;

    HtmlParseException() {}
    
    HtmlParseException(Exception e) {
        super(e.getMessage());
    }

}
