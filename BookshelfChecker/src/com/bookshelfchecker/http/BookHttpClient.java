package com.bookshelfchecker.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public final class BookHttpClient {
    
    public static String httpGet(String urlStr) throws IOException {
    	Log.w("***BookHttpClient***", urlStr);
    	URL url = new URL(urlStr);
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	String res = "";
    	try 
    	{
    		BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
    		String line;
    		while ((line = in.readLine()) != null) {
    			res += line; 
    		}
    		in.close();
    	} finally {
    		conn.disconnect();
    	}
    	return res;
    }
}
