package ueasy.it140.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ueasy.it140.R;
import ueasy.it140.database.Database;
import ueasy.it140.modals.Success;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Update extends ActionBarActivity {
	// DB Class to perform DB related operations
	Database controller = new Database(this);
	// Progress Dialog Object
	ProgressDialog prgDialog;
	HashMap<String, String> queryValues;
	
	//Room Utility Variable
	int ru_id; 		int cr_id; 
	String ru_mon; 	String ru_tue; 
	String ru_wed; 	String ru_thu; 
	String ru_fri; 	String ru_sat; 
	String ru_sun;
	
	//Campus Variables
	int campus_id;			String c_name;
	String c_addr;	 	String c_desc;
	
	//Amenities Variables
	String a_catName;	int a_id; 
	int bldg_id;		int a_bLevel;
	String a_name; 		String a_desc; 
	String a_pic; 		double a_la; 
	double a_longi;
	
	//BuildingLevel Variables
	int bl_id; 		int bl_bID; 
	int bl_bNum;
	
	//DatabaseVersion Variables
	int db_version;		int db_id;
			
	String type;
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutandfaqs);
		// Get User records from SQLite DB
		ArrayList<HashMap<String, String>> crList = controller.getAllClassroom();
		// If users exists in SQLite DB
		Toast.makeText(getApplicationContext(), "crList: "+crList.size(), Toast.LENGTH_LONG).show();
		
		
		// Initialize Progress Dialog properties
		prgDialog = new ProgressDialog(this);
		prgDialog.setMessage("Transferring Data from Remote MySQL DB and Syncing SQLite. Please wait...");
		prgDialog.setCancelable(true);
		// BroadCase Receiver Intent Object
//		Intent alarmIntent = new Intent(getApplicationContext(), SampleBC.class);
		// Pending Intent Object
		syncSQLiteMySQLDB();
	
	}
	
	// Options Menu (ActionBar Menu)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// When Options Menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. 
		
		return super.onOptionsItemSelected(item);
	}
	
	// Method to Sync MySQL to SQLite DB
	public void syncSQLiteMySQLDB() {
		// Create AsycHttpClient object
		AsyncHttpClient client = new AsyncHttpClient();
		// Http Request Params Object
		RequestParams params = new RequestParams();
		// Show ProgressBar
		prgDialog.show();
		// Make Http call to getusers.php
		client.post("http://192.168.254.103/ueasy/getClassroom.php", params, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					// Hide ProgressBar
					Toast.makeText(getApplicationContext(), "Success na cya", Toast.LENGTH_LONG).show();
					Toast.makeText(getApplicationContext(), "response: "+ response, Toast.LENGTH_LONG).show();
					prgDialog.hide();
					// Update SQLite DB with response sent by getusers.php
					updateSQLite(response);
				}
				// When error occured
				@Override
				public void onFailure(int statusCode, Throwable error, String content) {
					// TODO Auto-generated method stub
					// Hide ProgressBar
					prgDialog.hide();
					if (statusCode == 404) {
						Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
					} else if (statusCode == 500) {
						Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]",
								Toast.LENGTH_SHORT).show();
					}
				}
		});
	}
	
	public void updateSQLite(String response){
		
		long result = 0;
		
		
		ArrayList<HashMap<String, String>> amenitysynclist;
		amenitysynclist = new ArrayList<HashMap<String, String>>();
		
		// Create GSON object
		Gson gson = new GsonBuilder().create();
		try {
			// Extract JSON array from the response
			JSONArray arr = new JSONArray(response);
			System.out.println(arr.length());
			Toast.makeText(getApplicationContext(), "arr lenght:  "+ arr.length() , Toast.LENGTH_SHORT).show();
			
			
			// If no of array elements is not zero
			if(arr.length() != 0){
				
				Toast.makeText(getApplicationContext(), "Nakasulod sa IF:  ", Toast.LENGTH_SHORT).show();
				
				// Loop through each array element, get JSON object which has userid and username
				for (int i = 0; i < arr.length(); i++) {
					Toast.makeText(getApplicationContext(), "Naka tuyok na sa FOR:  ", Toast.LENGTH_SHORT).show();
					// Get JSON object
					JSONObject obj = (JSONObject) arr.get(i);
					
					//Getting Common Data from the Json
								
					obj.get("type");
					
					type = obj.getString("type").toString();
					
					if(type.equals("amenity"))
					{
						//Getting Amenity Data from Json
						obj.get("a_catName");		obj.get("a_id");
						obj.get("a_bLevel");		obj.get("a_name");
						obj.get("a_desc");			obj.get("a_pic");
						obj.get("a_latitude");		obj.get("a_longitude");
						obj.get("b_id");			obj.get("c_id");
					}
					
					else if(type.equals("utility")) //Getting Room Utility Data from Json
					{
						obj.get("ru_id");			obj.get("cr_id");
						obj.get("ru_monday");		obj.get("ru_tuesday");
						obj.get("ru_wednesday");	obj.get("ru_thursday");
						obj.get("ru_friday");		obj.get("ru_saturday");
						obj.get("ru_sunday");
					}
					
					else if(type.equals("databaseVersion")) //Getting Campus Data from Json
					{
						obj.get("db_id");	obj.get("db_version");
						
					}
					
					else if(type.equals("campus")) //Getting Campus Data from Json
					{
						obj.get("c_name");	obj.get("c_addr");
						obj.get("c_desc");	obj.get("c_id");
					}
					
					else
					{
						//Getting Building Level Data from Json
						obj.get("bl_id"); 		obj.get("bldg_levelNum");
						obj.get("bldg_id");			
					}
					
					
					
					// DB QueryValues Object to insert into SQLite
					queryValues = new HashMap<String, String>();
					
					
					// Add values from the htdocs/ueasy extracted from Object = Common Data
					
						Toast.makeText(getApplicationContext(), "saving common to variables", Toast.LENGTH_SHORT).show();
						
						
					
							
							
					if(type.equals("amenity"))
					{
						Toast.makeText(getApplicationContext(), "saving Amenity to variables", Toast.LENGTH_SHORT).show();
						a_catName = obj.getString("a_catName").toString();
						a_id =Integer.parseInt( obj.getString("a_id").toString());
						a_bLevel  = Integer.parseInt(obj.getString("a_bLevel").toString());
						a_name = obj.getString("a_name").toString();
						a_desc = obj.getString("a_desc").toString();
						a_pic = obj.getString("a_pic").toString();
						a_la = Double.parseDouble(obj.getString("a_latitude").toString());
						a_longi = Double.parseDouble(obj.getString("a_longitude").toString());
						bldg_id = Integer.parseInt(obj.getString("b_id").toString());
						campus_id = Integer.parseInt(obj.getString("c_id").toString());
					}
					
					else if(type.equals("utility"))
					{
						ru_id = Integer.parseInt(obj.getString("ru_id").toString());
						ru_mon =  obj.getString("ru_monday").toString();
						ru_tue = obj.getString("ru_tuesday").toString();	 	
						ru_wed = obj.getString("ru_wednesday").toString();
						ru_thu = obj.getString("ru_thursday").toString();
						ru_fri = obj.getString("ru_friday").toString();	 	
						ru_sat = obj.getString("ru_saturday").toString();
						ru_sun = obj.getString("ru_sunday").toString();
						cr_id = Integer.parseInt(obj.getString("cr_id").toString());
						
					}
					
					else if(type.equals("databaseVersion"))
					{
						db_id = Integer.parseInt(obj.getString("db_id").toString());
						db_version =  Integer.parseInt( obj.getString("db_version").toString());
						
					}
					
					else if(type.equals("campus"))
					{
						c_name =  obj.getString("c_name").toString();
						c_addr = obj.getString("c_addr").toString();	 	
						c_desc = obj.getString("c_desc").toString();
						campus_id = Integer.parseInt(obj.getString("c_id").toString());
					}
					
					else 
					{
						Toast.makeText(getApplicationContext(), "saving bldg to variables", Toast.LENGTH_SHORT).show();
						bl_id =Integer.parseInt( obj.getString("bl_id").toString());
						bl_bID =Integer.parseInt( obj.getString("bldg_id").toString());
						bl_bNum =Integer.parseInt( obj.getString("bldg_levelNum").toString());
						
					}
					
					
	
					// Add userName extracted from Object
					//queryValues.put("userName", obj.get("userName").toString());
					// Insert User into SQLite DB

					
						
						if(type.equals("amenity"))
						{
							Toast.makeText(getApplicationContext(), "Naka insert sa Otheramenities:  ", Toast.LENGTH_SHORT).show();
							result = controller.inserToAmenity(a_catName, a_id, campus_id, bldg_id, a_bLevel, a_name, a_desc, a_pic, a_la, a_longi);
							Toast.makeText(getApplicationContext(), "Result  insert OA:  "+ result , Toast.LENGTH_SHORT).show();
						}
						
						else if(type.equals("utility"))
						{
							Toast.makeText(getApplicationContext(), "Naka insert sa Room Utility:  ", Toast.LENGTH_SHORT).show();
							result = controller.inserToRoomUtility(ru_id, cr_id, ru_mon, ru_tue, ru_wed, ru_thu, ru_fri, ru_sat, ru_sun);
							Toast.makeText(getApplicationContext(), "Result  insert Room Utility:  "+ result , Toast.LENGTH_SHORT).show();
						}
						
						else if(type.equals("databaseVersion"))
						{
							Toast.makeText(getApplicationContext(), "Naka insert sa Room Utility:  ", Toast.LENGTH_SHORT).show();
							result = controller.inserToDatabase(db_id, db_version);
							Toast.makeText(getApplicationContext(), "Result  insert Room Utility:  "+ result , Toast.LENGTH_SHORT).show();
						}
						
						else if(type.equals("campus"))
						{
							Toast.makeText(getApplicationContext(), "Naka insert sa Campus:  ", Toast.LENGTH_SHORT).show();
							result = controller.inserToCampus(campus_id, c_name, c_addr, c_desc);
							Toast.makeText(getApplicationContext(), "Result  insert Campus:  "+ result , Toast.LENGTH_SHORT).show();
						}
						
						else
						{
							Toast.makeText(getApplicationContext(), "Naka insert sa Level:  ", Toast.LENGTH_SHORT).show();
							result = controller.inserToBLevel(bl_id, bl_bID, bl_bNum);
							Toast.makeText(getApplicationContext(), "Result  insert Level:  "+ result , Toast.LENGTH_SHORT).show();
						}
						
		
					
					HashMap<String, String> map = new HashMap<String, String>();
					// Add status for each User in Hashmap

						map.put("type", type);
						if(type.equals("amenity"))
						{
							map.put("a_id", obj.get("a_id").toString());
							map.put("name", obj.get("a_name").toString());
							Toast.makeText(getApplicationContext(), "Map: "+type+" "+obj.get("a_id").toString(), Toast.LENGTH_SHORT).show();
						}
						
						else if(type.equals("utility"))
						{
							map.put("ru_id", obj.get("ru_id").toString());
							map.put("name", obj.get("ru_id").toString());
							Toast.makeText(getApplicationContext(), "Map: "+type+" "+obj.get("ru_id").toString(), Toast.LENGTH_SHORT).show();
						}
						
						else if(type.equals("campus"))
						{
							map.put("c_id", obj.get("c_id").toString());
							map.put("name", obj.get("c_name").toString());
							Toast.makeText(getApplicationContext(), "Map: "+type+" "+obj.get("c_id").toString(), Toast.LENGTH_SHORT).show();
						}
						
						else
						{
							map.put("bl_id", obj.get("bl_id").toString());
							map.put("name", obj.get("bl_id").toString());
							Toast.makeText(getApplicationContext(), "Map: "+type+" "+obj.get("bl_id").toString(), Toast.LENGTH_SHORT).show();
						}
						
						map.put("status", "1");
						amenitysynclist.add(map);
						
						
				}
				// Inform Remote MySQL DB about the completion of Sync activity by passing Sync status of Users
				updateMySQLSyncSts(gson.toJson(amenitysynclist));
				// Reload the Main Activity
				reloadActivity();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), "function error  "+e, Toast.LENGTH_SHORT).show();
		}
	}
	
	// Method to inform remote MySQL DB about completion of Sync activity
	public void updateMySQLSyncSts(String json) {
		System.out.println(json);
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("sync", json);
		// Make Http call to updatesyncsts.php with JSON parameter which has Sync statuses of Users
		client.post("http://192.168.254.103/ueasy/updatesyncsts.php", params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Toast.makeText(getApplicationContext(),	"MySQL DB has been informed about Sync activity", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(int statusCode, Throwable error, String content) {
					Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	// Reload MainActivity
	public void reloadActivity() {
		
		Success suc = new Success();
		suc.show(getFragmentManager(), "Confirm");
		
	}
}
