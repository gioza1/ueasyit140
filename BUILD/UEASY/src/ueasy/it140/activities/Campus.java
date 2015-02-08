package ueasy.it140.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import ueasy.it140.adapters.CampusAdapter;
import ueasy.it140.database.Database;
import ueasy.it140.models.CampusModel;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class Campus extends ListActivity {
	Database DB;
	private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
	private long mBackPressed;
	/** Called when the activity is first created. */
	@SuppressLint("NewApi") @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		copyAssets();
		DB = new Database(this);
		DB.getWritableDatabase();
		try {
			DB.copyDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			Message.message(this, e.toString());
		}
		
				
		DB.BuildingName();
		
		ActionBar ab = getActionBar();
		ab.setTitle("Select Campus");
		
		ab.setIcon(android.R.color.transparent);
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#048abf")));
		ArrayList<CampusModel> items = CampusModel.populateItems();
		CampusAdapter adapter = new CampusAdapter(this, items);

		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			Intent another = new Intent(this, Map.class);
			startActivity(another);
			break;
		}
	}
	

	private void copyAssets() {
	    AssetManager assetManager = getAssets();
	    String[] files = null;
	    try {
	        files = assetManager.list("osmdroid");
	    } catch (IOException e) {
	        Log.e("tag", "Failed to get asset file list.", e);
	    }
	    for(String filename : files) {
	        InputStream in = null;
	        OutputStream out = null;
	        try {
	          in = assetManager.open(filename);
	          File outFile = new File(Environment.getExternalStorageDirectory()+"/osmdroid", filename);
	          out = new FileOutputStream(outFile);
	          copyFile(in, out);
	          in.close();
	          in = null;
	          out.flush();
	          out.close();
	          out = null;
	        } catch(IOException e) {
	            Log.e("tag", "Failed to copy asset file: " + filename, e);
	        }       
	    }
	}
	private void copyFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = in.read(buffer)) != -1){
	      out.write(buffer, 0, read);
	    }
	}
	
	public void onBackPressed()
	{
	    if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) 
	    { 
	        super.onBackPressed(); 
	        return;
	    }
 else {
			Toast.makeText(getBaseContext(), "Tap back button twice to exit", Toast.LENGTH_SHORT).show(); }

	    mBackPressed = System.currentTimeMillis();
	}
}






//package ueasy.it140.activities;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//
//import ueasy.it140.adapters.CampusAdapter;
//import ueasy.it140.database.Database;
//import ueasy.it140.models.CampusModel;
//import android.annotation.SuppressLint;
//import android.app.ActionBar;
//import android.app.ListActivity;
//import android.content.Intent;
//import android.content.res.AssetManager;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.os.Environment;
//import android.util.Log;
//import android.view.View;
//import android.widget.ListView;
//import android.widget.Toast;
//
//public class Campus extends ListActivity {
//	Database DB;
//
//	/** Called when the activity is first created. */
//	@SuppressLint("NewApi")
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		 copyAssets();
//		DB = new Database(this);
//		DB.getWritableDatabase();
//		try {
//			DB.copyDataBase();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			// Message.message(this, e.toString());
//		}
//
//		DB.BuildingName();
////		DB.insertPoints();
//
//		ActionBar ab = getActionBar();
//		ab.setTitle("Select Campus");
//
//		ab.setIcon(android.R.color.transparent);
//		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#048abf")));
//		ArrayList<CampusModel> items = CampusModel.populateItems();
//		CampusAdapter adapter = new CampusAdapter(this, items);
//
//		setListAdapter(adapter);
//	}
//
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		// TODO Auto-generated method stub
//		if (position == 0) {
//			Intent another = new Intent(this, Map.class);
//			startActivity(another);
//		} else {
//			Toast.makeText(this, "Sorry, only USC-TC is available for now.",
//					Toast.LENGTH_SHORT).show();
//		}
//
//	}
//
//	private void copyAssets() {
//		AssetManager assetManager = getAssets();
//		String[] files = null;
//		try {
//			files = assetManager.list("osmdroid");
//		} catch (IOException e) {
//			Log.e("tag", "Failed to get asset file list.", e);
//		}
//		for (String filename : files) {
//			InputStream in = null;
//			OutputStream out = null;
//			try {
//				in = assetManager.open(filename);
//				File outFile = new File(
//						Environment.getExternalStorageDirectory() + "/osmdroid",
//						filename);
//				out = new FileOutputStream(outFile);
//				copyFile(in, out);
//				in.close();
//				in = null;
//				out.flush();
//				out.close();
//				out = null;
//			} catch (IOException e) {
//				Log.e("tag", "Failed to copy asset file: " + filename, e);
//			}
//		}
//	}
//
//	private void copyFile(InputStream in, OutputStream out) throws IOException {
//		byte[] buffer = new byte[1024];
//		int read;
//		while ((read = in.read(buffer)) != -1) {
//			out.write(buffer, 0, read);
//		}
//	}
//}
