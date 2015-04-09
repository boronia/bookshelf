package com.bookshelfchecker.model;

public class Book {
    
    private String isbn;
    private String title;
    private int volume;
    private boolean isCompleted;
    
    public Book() {}
    
    public Book(String isbn) {
        this.setIsbn(isbn);
    }
    
    public Book(String isbn, String title, int volume, boolean isCompleted) {
        this.setIsbn(isbn);
        this.title = title;
        this.volume = volume;
        this.isCompleted = isCompleted;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String toString() {
    	return this.title + ", vol." + String.valueOf(this.volume);
    }
}
