package com.bookshelfchecker.http;

import junit.framework.Assert;

import org.junit.Test;

import com.bookshelfchecker.http.IsbnSearcher;
import com.bookshelfchecker.model.Book;

public class IsbnSearcherTest {
	
	@Test
	public void testIsbnSearch() {
		String isbn = "9781421515434";
		Book book = IsbnSearcher.searchIsbn(isbn);
		Assert.assertNotNull(book);
		Assert.assertEquals(isbn, book.getIsbn());
		Assert.assertEquals("D.Gray-man", book.getTitle());
		Assert.assertEquals(8, book.getVolume());
		Assert.assertFalse(book.isCompleted());
	}

}
