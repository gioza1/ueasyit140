package ueasy.it140.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.osmdroid.util.GeoPoint;

import ueasy.it140.activities.DatabaseObject;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseJacob extends SQLiteOpenHelper {

	public static String DB_PATH = "/data/data/ueasy.it140/databases/";
	public static final String DB_Name = "UEASY.db";
	public String DB_OffcialName = "UEASY.db";

	/* Table names */
	public static final String Table_Campus = "Campus";
	public static final String Table_BuildingLevel = "BuildingLevel";
	public static final String Table_Amenities = "Amenities";
	public static final String Table_RoomUtility = "RoomUtility";
	public static final String Table_DB = "DatabaseVersion";

	/* Campus Variables */
	public static final String Campus_ID = "c_id";
	public static final String Campus_name = "c_name";
	public static final String Campus_addr = "c_addr";
	public static final String Campus_desc = "c_desc";

	/* Building Level Variables */
	public static final String BuildingLevel_ID = "bl_id";
	public static final String BuildingLevel_BuildingID = "building_id";
	public static final String BuildingLevel_BuildingNum = "building_levelNum";

	/* Other Amenities Variables */
	public static final String Amenities_ID = "amenity_id";
	public static final String Amenities_name = "amenity_name";
	public static final String Amenities_pic = "amenity_pic";
	public static final String Amenities_desc = "amenity_desc";
	public static final String Amenities_catName = "amenity_catName";
	public static final String Amenities_latitude = "amenity_latitude";
	public static final String Amenities_longitude = "amenity_longitude";
	public static final String Amenities_BldgID = "amenity_bldgID";
	public static final String Amenities_BldgLevel = "amenity_bldgLevel";

	/* Room Utility Variables */
	public static final String RoomUtility_ID = "ru_id";
	public static final String RoomUtility_ClassroomID = "classroom_id";
	public static final String RoomUtility_Monday = "ru_monday";
	public static final String RoomUtility_Tuesday = "ru_tuesday";
	public static final String RoomUtility_Wednesday = "ru_wednesday";
	public static final String RoomUtility_Thursday = "ru_thursday";
	public static final String RoomUtility_Friday = "ru_friday";
	public static final String RoomUtility_Saturday = "ru_saturday";
	public static final String RoomUtility_Sunday = "ru_sunday";

	/* DatabaseVersion Variables */
	public static final String DB_ID = "db_id";
	public static final String DB_Version = "db_version";

	public static int superParam = 1;
	int oldVersion;
	int newVersion;
	private String queryCampus, queryBuilding, queryOtherAmenities, queryDB,
			queryRoomUtility;
	private String sql8;
	private Context context;

	public DatabaseJacob(Context context) {
		super(context, DB_Name, null, superParam);
		this.context = context;
		// Message.message(context, "constructor is called");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		queryCampus = "CREATE TABLE " + Table_Campus + "(" + Campus_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Campus_name
				+ " TEXT, " + Campus_addr + " TEXT, " + Campus_desc + " TEXT);";
		db.execSQL(queryCampus);

		queryBuilding = "CREATE TABLE " + Table_BuildingLevel + "("
				+ BuildingLevel_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ BuildingLevel_BuildingID + " INTEGER, "
				+ BuildingLevel_BuildingNum + " INTEGER);";
		db.execSQL(queryBuilding);

		queryOtherAmenities = "CREATE TABLE " + Table_Amenities + "("
				+ Amenities_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ Campus_ID + " INTEGER, " + Amenities_name + " TEXT, "
				+ Amenities_desc + " TEXT, " + Amenities_pic + " TEXT, "
				+ Amenities_BldgID + " INTEGER, " + Amenities_BldgLevel
				+ " INTEGER, " + Amenities_latitude + " TEXT,"
				+ Amenities_longitude + " TEXT, " + Amenities_catName
				+ " TEXT);";
		db.execSQL(queryOtherAmenities);

		queryRoomUtility = "CREATE TABLE " + Table_RoomUtility + "("
				+ RoomUtility_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ RoomUtility_ClassroomID + " INTEGER, " + RoomUtility_Monday
				+ " TEXT, " + RoomUtility_Tuesday + " TEXT, "
				+ RoomUtility_Wednesday + " TEXT, " + RoomUtility_Thursday
				+ " TEXT, " + RoomUtility_Friday + " TEXT, "
				+ RoomUtility_Saturday + " TEXT, " + RoomUtility_Sunday
				+ " TEXT);";
		db.execSQL(queryRoomUtility);

		queryDB = "CREATE TABLE " + Table_DB + "(" + DB_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + DB_Version
				+ " INTEGER);";
		db.execSQL(queryDB);

		// Inserting DB Version
		ContentValues values = new ContentValues();
		values.put(DB_Version, 0);
		db.insert(Table_DB, null, values);

		/*--------A Test table-----------*/
		sql8 = "CREATE TABLE Geopoints"
				+ "(gp_id INTEGER PRIMARY KEY AUTOINCREMENT,gp_lat INTEGER,gp_long INTEGER );";
		db.execSQL(sql8);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	/*--------------------------------------------CATEGORY---------------------------------------------------------------*/

	public List<GeoPoint> AllAmenity() {
		SQLiteDatabase db = this.getWritableDatabase();
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		String[] columns = { "gp_lat", "gp_long" };
		Cursor cursor = db.query("Geopoints", columns, null, null, null, null,
				null);
		while (cursor.moveToNext()) {
			int ndx1 = cursor.getColumnIndex("Geopoints");
			// list.add(cursor.getString(ndx1));
		}

		return list;
	}

	/*
	 * 
	 * public DatabaseObject get_DatabaseObject(int id) { SQLiteDatabase db =
	 * this.getReadableDatabase(); Cursor cursor = db.query(Table_Classroom, new
	 * String[] { Classroom_ID, Classroom_latitude, Classroom_longitude,
	 * Classroom_name, Classroom_desc }, Classroom_ID + "=?", new String[] {
	 * String.valueOf(id) }, null, null, null, null); if (cursor != null)
	 * cursor.moveToFirst();
	 * 
	 * DatabaseObject data = new DatabaseObject(Integer.parseInt(cursor
	 * .getString(0)), Double.parseDouble(cursor.getString(8)),
	 * Double.parseDouble(cursor.getString(9)), cursor.getString(4),
	 * cursor.getString(6)); // return database object return data; }
	 */

	// Getting All database objects
	public List<DatabaseObject> getAllDatabaseObject(String type) {
		List<DatabaseObject> contactList = new ArrayList<DatabaseObject>();
		String selectQuery;
		// Select All Query

		selectQuery = "SELECT  * FROM " + Table_Amenities + " WHERE "
				+ Amenities_catName + "= '" + type + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		String[] cNames = cursor.getColumnNames();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {

			do {
				DatabaseObject data = new DatabaseObject();

				for (int i = 0; i < cNames.length; i++) {
					if (cNames[i].contains("_id")) {
						data.setID(Integer.parseInt(cursor.getString(cursor
								.getColumnIndex(cNames[i]))));
					}
					if (cNames[i].contains("_latitude")) {
						data.setLat(Double.parseDouble(cursor.getString(cursor
								.getColumnIndex(cNames[i]))));
					}
					if (cNames[i].contains("_longitude")) {
						data.setLong(Double.parseDouble(cursor.getString(cursor
								.getColumnIndex(cNames[i]))));
					}
					if (cNames[i].contains("_name")) {
						data.setName(cursor.getString(cursor
								.getColumnIndex(cNames[i])));
					}
					if (cNames[i].contains("_desc")) {
						data.setInfo(cursor.getString(cursor
								.getColumnIndex(cNames[i])));
					}
					// data.setLat(cursor.getString(8));
					// data.setLong(cursor.getString(9));
					// data.setName(cursor.getString(4));
					// data.setInfo(cursor.getString(6));
				}
				// Adding contact to list
				contactList.add(data);

			} while (cursor.moveToNext());
		}

		// return database object list
		return contactList;
	}

	public List<String> Amenities(String type) {

		SQLiteDatabase db = this.getWritableDatabase();
		List<String> retVal = new ArrayList<String>();
		String[] whereArgs = { type };
		Cursor cursor2 = null;

		String[] columns2 = { Amenities_name };
		if (type.contains("Classroom")) {
			String roomCode = type.substring(9, 11);
			// Toast.makeText(get, text, duration)
			// String[] args = { roomCode + "%" };
			// cursor2 = db.query(Table_Amenities, columns2, Amenities_catName
			// + " LIKE ?", args, null, null, null);
			String rq = "SELECT " + Amenities_name + " FROM " + Table_Amenities
					+ " WHERE " + Amenities_name + " LIKE '" + roomCode + "%'";
			// String rq
			// ="SELECT amenity_name FROM Amenities WHERE amenity_name LIKE '"+roomCode+"%'";
			cursor2 = db.rawQuery(rq, null);
		} else {
			cursor2 = db.query(Table_Amenities, columns2, Amenities_catName
					+ "=?", whereArgs, null, null, null);
		}

		while (cursor2.moveToNext()) {
			int ndx2 = cursor2.getColumnIndex(Amenities_name);
			retVal.add(cursor2.getString(ndx2));
		}

		return retVal;
	}

	public DatabaseObject getAmenityInformation(String name) {
		String selectQuery;
		if (name.contentEquals("Facilities")) {
			name = "Facility";
		}
		selectQuery = "SELECT  * FROM " + Table_Amenities + " WHERE "
				+ Amenities_name + "= " + '"' + name + '"';

		DatabaseObject data = new DatabaseObject();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		String[] cNames = cursor.getColumnNames();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {

			for (int i = 0; i < cNames.length; i++) {
				if (cNames[i].contains("_id")) {

					data.setID(Integer.parseInt(cursor.getString(cursor
							.getColumnIndex(cNames[i]))));
				}
				if (cNames[i].contains("_latitude")) {
					data.setLat(Double.parseDouble(cursor.getString(cursor
							.getColumnIndex(cNames[i]))));
				}
				if (cNames[i].contains("_longitude")) {
					data.setLong(Double.parseDouble(cursor.getString(cursor
							.getColumnIndex(cNames[i]))));
				}
				if (cNames[i].contains("_name")) {
					data.setName(cursor.getString(cursor
							.getColumnIndex(cNames[i])));
				}
				if (cNames[i].contains("_desc")) {
					data.setInfo(cursor.getString(cursor
							.getColumnIndex(cNames[i])));
				}

				if (cNames[i].contains("_catName")) {
					data.setType(cursor.getString(cursor
							.getColumnIndex(cNames[i])));
				}
				if (cNames[i].contains("_bldgLevel")) {

					// gets and sets the building level
					int blevel = cursor
							.getInt(cursor.getColumnIndex(cNames[i]));
					if (blevel == 0) {
						int id = cursor
								.getInt(cursor.getColumnIndex(cNames[0]));
						String sq = "SELECT building_levelNum FROM BuildingLevel WHERE building_id = "
								+ id;
						Cursor cursor2 = db.rawQuery(sq, null);
						if (cursor2.getCount() > 0) {
							cursor2.moveToFirst();
							int ndx2 = cursor2.getInt(cursor2
									.getColumnIndex("building_levelNum"));
							data.setBlevels(ndx2);
						}

					}

				}
			}

		}
		return data;
	}

	/*--------------------------------------------COPYDB---------------------------------------------------------------*/
	public void copyDataBase() throws IOException {

		InputStream myInput = context.getAssets().open(DB_Name);
		String outFileName = DB_PATH + DB_Name;
		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	/*--------------------------------------------SEARCH---------------------------------------------------------------*/

	public List<String> getAllAmenityName() {

		SQLiteDatabase db = this.getWritableDatabase();
		List<String> retVal = new ArrayList<String>();

		// Retrieving all names of the Amenities
		String[] columns3 = { Amenities_name };
		Cursor cursor3 = db.query(Table_Amenities, columns3, null, null, null,
				null, null);

		// Adding all OtherAmenity names to the ArrayList
		while (cursor3.moveToNext()) {
			int ndx3 = cursor3.getColumnIndex(Amenities_name);
			retVal.add(cursor3.getString(ndx3));
		}

		return retVal;
	}

	public void insertPoints() {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("gp_long", 10.352515);
		values.put("gp_lat", 123.910585);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.354892);
		values.put("gp_lat", 123.910859);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.354892);
		values.put("gp_lat", 123.910859);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.353195);
		values.put("gp_lat", 123.909937);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.353924);
		values.put("gp_lat", 123.91218);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.352122);
		values.put("gp_lat", 123.913156);
		db.insert("Geopoints", null, values);
		values.put("gp_long", 10.355465);
		values.put("gp_lat", 123.910088);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.352122);
		values.put("gp_lat", 123.913156);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.352122);
		values.put("gp_lat", 123.913156);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.352122);
		values.put("gp_lat", 123.913156);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.354836);
		values.put("gp_lat", 123.910723);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.354967);
		values.put("gp_lat", 123.910854);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.355051);
		values.put("gp_lat", 123.910916);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.355117);
		values.put("gp_lat", 123.910964);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.355453);
		values.put("gp_lat", 123.909838);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.355484);
		values.put("gp_lat", 123.909913);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.355496);
		values.put("gp_lat", 123.909998);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.355492);
		values.put("gp_lat", 123.910078);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.355485);
		values.put("gp_lat", 123.910154);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.351825);
		values.put("gp_lat", 123.912779);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.354124);
		values.put("gp_lat", 123.90934);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.354892);
		values.put("gp_lat", 123.910859);
		db.insert("Geopoints", null, values);

		values.put("gp_long", 10.355173);
		values.put("gp_lat", 123.910995);
		db.insert("Geopoints", null, values);
		values.put("gp_long", 10.351916);
		values.put("gp_lat", 123.913082);
		db.insert("Geopoints", null, values);
		values.put("gp_long", 10.351884);
		values.put("gp_lat", 123.913188);
		db.insert("Geopoints", null, values);
		values.put("gp_long", 10.351718);
		values.put("gp_lat", 123.913298);
		db.insert("Geopoints", null, values);
		values.put("gp_long", 10.351776);
		values.put("gp_lat", 123.912986);
		db.insert("Geopoints", null, values);

	}
	
	
	
	/*--------------------Insert----------------------------*/

	public long inserToCampus(int campus_id, String c_name, String c_addr,
			String c_desc) {
		SQLiteDatabase db = this.getWritableDatabase();
		long retVal = 0;
		ContentValues values = new ContentValues();
		values.put(Campus_ID, campus_id);
		values.put(Campus_name, c_name);
		values.put(Campus_addr, c_addr);
		values.put(Campus_desc, c_desc);

		if (getID(campus_id, Table_Campus, Campus_ID) > 0) {
			String[] whereArgs = { "" + campus_id };
			retVal = db.update(Table_Campus, values, Campus_ID + "=?",
					whereArgs);
		}

		else {
			retVal = db.insert(Table_Campus, null, values);
		}
		Toast.makeText(this.context, "retVal in DB: " + retVal,
				Toast.LENGTH_SHORT).show();

		return retVal;

	}

	public long inserToAmenity(String a_catName, int a_id, int campus_id,
			int bldg_id, int a_bLevel, String a_name, String a_desc,
			String a_pic, double a_la, double a_longi) {
		long retVal = 0;
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Amenities_ID, a_id);
		values.put(Campus_ID, campus_id);
		values.put(Amenities_name, a_name);
		values.put(Amenities_catName, a_catName);
		values.put(Amenities_BldgLevel, a_bLevel);
		values.put(Amenities_BldgID, bldg_id);
		values.put(Amenities_desc, a_desc);
		values.put(Amenities_pic, a_pic);
		values.put(Amenities_latitude, a_la);
		values.put(Amenities_longitude, a_longi);

		if (getID(a_id, Table_Amenities, Amenities_ID) > 0) {
			String[] whereArgs = { "" + a_id };
			retVal = database.update(Table_Amenities, values, Amenities_ID
					+ "=?", whereArgs);
		}

		else {
			retVal = database.insert(Table_Amenities, null, values);
		}
		Toast.makeText(this.context, "retVal in DB: " + retVal,
				Toast.LENGTH_SHORT).show();

		return retVal;
	}

	public long inserToBLevel(int bl_id, int bl_bID, int bl_bNum) {
		long retVal = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(BuildingLevel_ID, bl_id);
		values.put(BuildingLevel_BuildingID, bl_bID);
		values.put(BuildingLevel_BuildingNum, bl_bNum);

		if (getID(bl_id, Table_BuildingLevel, BuildingLevel_ID) > 0) {
			String[] whereArgs = { "" + bl_id };
			retVal = db.update(Table_BuildingLevel, values, BuildingLevel_ID
					+ "=?", whereArgs);
		}

		else {
			retVal = db.insert(Table_BuildingLevel, null, values);
		}
		Toast.makeText(this.context, "retVal in DB: " + retVal,
				Toast.LENGTH_SHORT).show();

		return retVal;

	}

	public long inserToRoomUtility(int ru_id, int cr_id, String ru_mon,
			String ru_tue, String ru_wed, String ru_thu, String ru_fri,
			String ru_sat, String ru_sun) {
		long retVal = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(RoomUtility_ID, ru_id);
		values.put(RoomUtility_ClassroomID, cr_id);
		values.put(RoomUtility_Monday, ru_mon);
		values.put(RoomUtility_Tuesday, ru_tue);
		values.put(RoomUtility_Wednesday, ru_wed);
		values.put(RoomUtility_Thursday, ru_thu);
		values.put(RoomUtility_Friday, ru_fri);
		values.put(RoomUtility_Saturday, ru_sat);
		values.put(RoomUtility_Sunday, ru_sun);

		if (getID(ru_id, Table_RoomUtility, RoomUtility_ID) > 0) {
			String[] whereArgs = { "" + ru_id };
			retVal = db.update(Table_RoomUtility, values,
					RoomUtility_ID + "=?", whereArgs);
		}

		else {
			retVal = db.insert(Table_RoomUtility, null, values);
		}
		Toast.makeText(this.context, "retVal in DB: " + retVal,
				Toast.LENGTH_SHORT).show();

		return retVal;

	}

	public long inserToDatabase(int db_id, int db_version) {
		long retVal = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DB_ID, db_id);
		values.put(DB_Version, db_version);

		if (getID(db_id, Table_DB, DB_ID) > 0) {
			String[] whereArgs = { "" + db_id };
			retVal = db.update(Table_DB, values, DB_ID + "=?", whereArgs);
		}

		else {
			retVal = db.insert(Table_DB, null, values);
		}
		Toast.makeText(this.context, "retVal in DB: " + retVal,
				Toast.LENGTH_SHORT).show();

		return retVal;
	}

	/**
	 * Get list of Users from SQLite DB as Array List
	 * 
	 * @return
	 */

	public ArrayList<HashMap<String, String>> getAllClassroom() {
		ArrayList<HashMap<String, String>> usersList;
		usersList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM " + Table_Amenities;
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();

				int ndx1 = cursor.getColumnIndex(Amenities_ID);
				int ndx2 = cursor.getColumnIndex(Campus_ID);
				int ndx3 = cursor.getColumnIndex(Amenities_name);
				int ndx4 = cursor.getColumnIndex(Amenities_pic);
				int ndx5 = cursor.getColumnIndex(Amenities_desc);
				int ndx6 = cursor.getColumnIndex(Amenities_catName);
				int ndx7 = cursor.getColumnIndex(Amenities_latitude);
				int ndx8 = cursor.getColumnIndex(Amenities_longitude);
				int ndx9 = cursor.getColumnIndex(Amenities_BldgID);
				int ndx10 = cursor.getColumnIndex(Amenities_BldgLevel);

				map.put(Amenities_ID, cursor.getString(ndx1));
				map.put(Campus_ID, cursor.getString(ndx2));
				map.put(Amenities_name, cursor.getString(ndx3));
				map.put(Amenities_pic, cursor.getString(ndx4));
				map.put(Amenities_desc, cursor.getString(ndx5));
				map.put(Amenities_catName, cursor.getString(ndx6));
				map.put(Amenities_latitude, cursor.getString(ndx7));
				map.put(Amenities_longitude, cursor.getString(ndx8));
				map.put(Amenities_BldgID, cursor.getString(ndx9));
				map.put(Amenities_BldgLevel, cursor.getString(ndx10));

				usersList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		return usersList;
	}

		
	
/*-----------------------Getting an Amenity ID------------------*/
	
	public int getID(int id, String table, String columnID) {
		SQLiteDatabase db = this.getWritableDatabase();

		String[] columns = { columnID };
		String[] whereArgs = { "" + id };
		Cursor cursor = db.query(table, columns, columnID + "=?", whereArgs,
				null, null, null);
		cursor.moveToNext();
		Toast.makeText(context, "Cursor count: " + cursor.getCount(),
				Toast.LENGTH_SHORT).show();
		return cursor.getCount();
	}
	
	
	/*----------Get DB Version--------------------*/
	
	public int DBVersion() {
		SQLiteDatabase db = this.getWritableDatabase();
		int version = 0;

		String[] columns = { DB_Version };
		Cursor cursor = db.query(Table_DB, columns, null, null, null, null,
				null);

		while (cursor.moveToNext()) {
			int ndx1 = cursor.getColumnIndex(DB_Version);
			version = cursor.getInt(ndx1);
		}

		return version;
	}
	
	
	/*-----Check if DB exist-----------*/

	public boolean doesDatabaseExist(ContextWrapper context, String dbName) {
	    File dbFile = context.getDatabasePath(dbName);
	    return dbFile.exists();
	}
}