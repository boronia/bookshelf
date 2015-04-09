package com.bookshelfchecker.util;

import org.junit.Assert;
import org.junit.Test;

import com.bookshelfchecker.model.Book;
import com.bookshelfchecker.util.StringProcessor;

public class StringProcessorTest {
	
	@Test
	public void testProcessTitleNormal() {
		String line = 
		"Naoki Urasawa's 20th Century Boys, Vol. 19";
		Book book = StringProcessor.processTitle(line);
		Assert.assertNotNull(book.getTitle());
		Assert.assertNotNull(book.getVolume());
		Assert.assertEquals("Naoki Urasawa's 20th Century Boys", book.getTitle());
		Assert.assertEquals(19, book.getVolume());
	}
	
	@Test
	public void testProcessTitleNoVolume() {
		String line = "Some Title";
		Book book = StringProcessor.processTitle(line);
		Assert.assertNotNull(book.getTitle());
		Assert.assertEquals("Some Title", book.getTitle());
	}
	
	@Test
	public void testProcessTitleBlank() {
		String line = "";
		Book book = StringProcessor.processTitle(line);
		Assert.assertNull(book);
	}

	@Test 
	public void testProcessTitleNull() {
		Assert.assertNull(StringProcessor.processTitle(null));
	}
	
}
