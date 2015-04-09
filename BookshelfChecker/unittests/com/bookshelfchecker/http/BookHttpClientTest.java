package com.bookshelfchecker.http;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.bookshelfchecker.http.BookHttpClient;

public class BookHttpClientTest {
	
	@Test
	public void testContactServer() throws IOException {
		String url = "http://www.google.com/books/feeds/volumes?q=ISBN%7B9781421542775%7D";
		String res = BookHttpClient.httpGet(url);
		System.err.println(res);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void testContactServer2() throws IOException {
		String url = "http://isbndb.com/api/v2/json/WBLWO9LY/book/9781421515434";
		String res = BookHttpClient.httpGet(url);
		System.err.println(res);
		Assert.assertNotNull(res);
	}

}
