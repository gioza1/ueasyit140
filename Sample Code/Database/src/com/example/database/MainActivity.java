package com.example.database;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
	
	Database DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		DB = new Database(this);
		DB.getWritableDatabase();
		
		/*Copying DB from ASSETS folder*/
		try {
			DB.copyDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Message.message(this, e.toString());
				
		}
		
		/*Inserting data to DB*/
		/*
		DB.inserToDB("Jacob", "Borromeo");
        DB.inserToDB("Gio", "Mendoza");
        DB.inserToDB("Julius", "Chin");
        DB.inserToDB("Mark", "Valenzona");
		*/
		
		
		/*Splash Screen timer*/
        Thread timer = new Thread(){
        	public void run(){
        		try{
        			sleep(3000);
        			Intent iN = new Intent("Crud");
        			startActivity(iN);
        		}
        		catch(InterruptedException e){
        			e.printStackTrace();
        		
        		}
        		
        		finally{
        			finish();
        		}
        	}
        };
        timer.start();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
