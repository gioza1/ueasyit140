package com.example.database;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {
	
	public static String DB_PATH = "/data/data/com.example.database/databases/";
	public static final String DB_Name = "Cobi";
	public static final String Table_Name = "User";
	public static final String ID = "_id";
	public static final String T_FName = "FName";
	public static final String T_LName = "LName";
	public static final int DB_Version = 1;
	int oldVersion;
	int newVersion;
	private String sql;
	private Context context;
	String Fname;
	String Lname;
	
	
	public Database(Context context){
		super(context, DB_Name,  null, DB_Version);
		this.context = context;
		Message.message(context, "constructor is called");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		Message.message(context, " onCreate is called");
		try {
			sql = "CREATE TABLE "+Table_Name+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+T_FName+" VARCHAR(255) unique, "+T_LName+" VARCHAR(255) unique);";
			db.execSQL(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Message.message(context, " "+ e);
		}
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		Message.message(context, " onUpgrade is called");
		sql = "DROP TABLE IF EXISTS "+Table_Name;
		try {
			db.execSQL(sql);
			onCreate(db);
			
		} catch (android.database.SQLException e) {
			// TODO Auto-generated catch block
			Message.message(context, " "+ e);
		}	

	}
	
	public long inserToDB (String F, String L){
		 SQLiteDatabase db = this.getWritableDatabase();
		 
		 ContentValues values = new ContentValues();
		  values.put(T_FName, F); // First Name
		  values.put(T_LName, L);// First Name
		  
		  //inserting Values
		  
		  long res =  db.insert(Table_Name , null, values);
		 // db.close();
		  /*
		  
		  if(res==-1){
	        	Toast.makeText(context, "Failed to Insert" , Toast.LENGTH_LONG).show();
	        }
	      
		  else{
	        	Toast.makeText(context, "Succeed to Insert", Toast.LENGTH_LONG).show();
	        }
			*/
		  
		  return res;
	}
	
	
	public String getAllData (){
		SQLiteDatabase db = this.getWritableDatabase();
		
		String[] columns = {ID, T_FName, T_LName};
		Cursor cursor = db.query(Table_Name, columns, null, null, null, null, null);
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(" ID Fname Lname \n");
		
		while(cursor.moveToNext()){
			int ndx1 = cursor.getColumnIndex(T_FName);
			int ndx2 = cursor.getColumnIndex(T_LName);
			int ndx3 = cursor.getColumnIndex(ID);
			buffer.append(" "+ cursor.getInt(ndx3)+" "+ cursor.getString(ndx1) +" "+ cursor.getString(ndx2) + "\n");
		}
		
		return buffer.toString();
	}
/*	
	public int getData(String F, String L){
		int flag=0;
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		String[] columns = {T_FName, T_LName};
		Cursor cursor = db.query(Table_Name, columns, null, null, null, null, null);
		while(cursor.moveToNext()){
			int ndx1 = cursor.getColumnIndex(T_FName);
			int ndx2 = cursor.getColumnIndex(T_LName);
			int ndx3 = cursor.getColumnIndex(ID);
			buffer.append(" "+ cursor.getInt(ndx3)+" "+ cursor.getString(ndx1) +" "+ cursor.getString(ndx2) + "\n");
		}
		
		return buffer.toString();
	}
	
*/	
	public int DelData(String Fname, String Lname){
		
		SQLiteDatabase db = this.getWritableDatabase();
		String[] whereArgs={Fname, Lname};
		int ret = db.delete(Table_Name, T_FName+"=? AND "+T_LName+" =?" , whereArgs);
		
		return ret;
	}


	public int updateFname(String NewName, String OldName){
		SQLiteDatabase db = this.getWritableDatabase();
		
		 ContentValues values = new ContentValues();
		 values.put(T_FName, NewName);
		 String[] whereArgs={OldName};
		int ret = db.update(Table_Name, values, T_FName+"=?", whereArgs);
		
		return ret;
	}
	
	
	public int updateLname(String NewName, String OldName){
		SQLiteDatabase db = this.getWritableDatabase();
		
		 ContentValues values = new ContentValues();
		 values.put(T_LName, NewName);
		 String[] whereArgs={OldName};
		int ret = db.update(Table_Name, values, T_LName+"=?", whereArgs);
		
		return ret;
	}
	
	
	public void copyDataBase() throws IOException{
	
		
        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_Name);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_Name;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }//end of copyDataBase() method
	
	
}
