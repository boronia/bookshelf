package com.bookshelfchecker.util;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.bookshelfchecker.model.Book;
import com.bookshelfchecker.util.ListDisplayProcessor;

public class ListDisplayProcessorTest {
	
	@Test
	public void testGetTitlesForDisplay() {
		String[] res = ListDisplayProcessor.getTitlesForDisplay(getBookList());
		String[] expected = { "Some title (1)" , "Some title (2)", "another title (213)"};
		Assert.assertArrayEquals(expected, res);
	}
	
	private ArrayList<Book> getBookList() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		Book book = new Book("12345", "Some title", 1, false);
		bookList.add(book);
		book = new Book("22222222", "Some title", 2, false);
		bookList.add(book);
		book = new Book("2313112", "another title", 213, true);
		bookList.add(book);
		return bookList;
	}
}
