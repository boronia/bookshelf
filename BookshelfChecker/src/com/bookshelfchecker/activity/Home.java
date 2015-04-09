package com.bookshelfchecker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookshelfchecker.R;
import com.bookshelfchecker.database.BooksQueryHandler;
import com.bookshelfchecker.http.IsbnSearchAsyncTask;
import com.bookshelfchecker.model.Book;
import com.bookshelfchecker.util.ListDisplayProcessor;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Home extends Activity {

    private BooksQueryHandler queryHandler = null;
    private ListView listView = null;
    private ArrayAdapter<String> adapter = null;
    private String[] listContent = null;
    private boolean toSave = false;
    
    private void loadListContent() {
        listContent = ListDisplayProcessor.getTitlesForDisplay(queryHandler.readAllFromDb());
        Log.w("*** Home ***","List content loaded: " + listContent.length);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_home);
        listView = (ListView) findViewById(R.id.list);
        queryHandler = new BooksQueryHandler(this);
        loadListView();
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {
            
          @Override
          public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               // ListView Clicked item index
               int itemPosition  = position;
               
               // ListView Clicked item value
               String itemValue = (String) listView.getItemAtPosition(position);
                  
               // TODO: Show Alert 
               //Toast.makeText(getApplicationContext(),
               //   "Position :"+itemPosition+"  Id : " + String.valueOf(id) , Toast.LENGTH_LONG)
               //   .show();
               // TODO: Show Info
               TextView info = (TextView)findViewById(R.id.info);
               String blurb = "\n\n" + itemValue + "\nCompleted? No"; 
               info.setText(blurb);
           }
        }); 

        Log.w("***Home***", "Done");
    }

	private void loadListView() {
		// clear info label
		TextView info = (TextView)findViewById(R.id.info);
		info.setText("");
		
		loadListContent();
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
                                           android.R.id.text1, listContent);
        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        Log.w("***Home***", "Adapter all set");
	}
    
    public void callScanner(View view) {
        IntentIntegrator ii = new IntentIntegrator(this);
        ii.initiateScan();
    }
    
    public void scanAndSave(View view) {
    	toSave = true;
    	callScanner(view);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
      IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
      if (scanResult != null) {
          // handle scan result
          String isbn = scanResult.getContents();
          // Start thread to contact isbndb 
          new IsbnSearchAsyncTask(this).execute(new String[] {isbn});
          // processing of the result is inside IsbnSearcherAsyncTask
      }     
    }
    
    public void handleIsbnResult(Book currentBook) {
    	TextView info = (TextView)findViewById(R.id.info);
        // TODO fix this
    	if (currentBook == null) {
    		info.setText("Something wrong, scan again!");
    		return;
    	}
        if (toSave) {
        	queryHandler.writeToDb(currentBook);
        	info.setText("Book saved!");
        	toSave = false;
            loadListView();
        	return;
        }
        else {
            Book latestBook = queryHandler.getLatestBookByTitle(currentBook.getTitle());
            if (latestBook != null) {
                String newVolume = Integer.toString(currentBook.getVolume());
                info.setText("Title : " + currentBook.getTitle() + 
                             "\nLast Volume in DB: " + latestBook.getVolume() + 
                             "\nWhat you're holding: " + newVolume);
            }
            else {
            	info.setText("Series not in possession.");
            }
            loadListView();
        }
        
    }
    
    public void clearAll(View view) {
        queryHandler.deleteAllFromDb();
        Toast.makeText(this, "Database Cleared", Toast.LENGTH_LONG).show();
        // refresh view
        loadListView();
    }
    
    public void exit(View view) {
    	finish();
    	System.exit(0);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

}
