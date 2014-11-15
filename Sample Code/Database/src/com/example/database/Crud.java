package com.example.database;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Crud extends Activity {
	
	Database DB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 DB = new Database(this);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	
	public void insert(View view){
		Intent iN = new Intent(this, Insert.class);
		startActivity(iN);
	}
	
	public void edit(View view){
		Intent iN = new Intent(this, Edit.class);
		startActivity(iN);
	}
	
	
	public void deldata(View view){
		Intent iN = new Intent(this, DelData.class);
		startActivity(iN);
	}
	
	public void read(View view){
		String data = DB.getAllData();
		Message.message(this, data);

	}
	
}
