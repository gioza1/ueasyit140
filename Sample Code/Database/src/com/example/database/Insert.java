package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Insert extends ActionBarActivity {
	
	Database DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
		DB = new Database(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert, menu);
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
	
	/*
	 protected void onPause(){
	        super.onPause();

	         // Clear all value here
	        Name.setText("");
	        .setText("");
	    }
	*/
	
	
	public void insertData(View view){
		EditText s1 = (EditText)findViewById(R.id.Fname);
		EditText s2 = (EditText)findViewById(R.id.Lname);
		int N1 = s1.getText().length();
		int N2 = s2.getText().length();
		
		  if( N1==0 && N2==0){
			  Message.message(this, "Please input names!");
		  }
		  else{
			  if(N1==0){
				  Message.message(this, "Missing: First name!");
			  }
			  
			  else if (N2==0){
				  Message.message(this, "Missing: Family name!");
			  }
			  
			  else{
				  
					String F = s1.getText().toString();
					String L = s2.getText().toString();

					
					long res = DB.inserToDB(F, L);
					
					  if(res==-1){
						  Message.message(this, "Failed to Insert");
				        }
				      
					  else{
						  Message.message(this, "Succeed to Insert");
				        }
					
					s1.setText("");
					s2.setText("");
					
					
			  }
		  }
		
		

		
	}
	
	public void home(View view){
		Intent iN = new Intent(this, Crud.class);
		startActivity(iN);	
	}
}
