package com.bookshelfchecker.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.bookshelfchecker.model.Book;
import com.bookshelfchecker.util.JsonProcessor;

public class JsonProcessorTest {
	
	@Test
	public void testParseBookInfo() throws Exception {
		String isbn = "12345";
		String json = readFile("testfiles/book2.eg.txt");
		Assert.assertNotNull(json);
		Book book = JsonProcessor.parseBookInfo(isbn, json);
		Assert.assertNotNull(book);
		Assert.assertEquals(isbn, book.getIsbn());
		Assert.assertEquals("D.Gray-man", book.getTitle());
		Assert.assertEquals(8, book.getVolume());
		Assert.assertEquals(false, book.isCompleted());
	}
	
	private String readFile(String filepath) throws IOException {
		String lines = null;
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        lines = sb.toString();
	    } finally {
	        br.close();
	    }		
		return lines;
	}

}
