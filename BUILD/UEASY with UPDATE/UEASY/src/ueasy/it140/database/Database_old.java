package ueasy.it140.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.osmdroid.util.GeoPoint;

import ueasy.it140.activities.DatabaseObject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_old extends SQLiteOpenHelper {

	public static String DB_PATH = "/data/data/ueasy.it140/databases/";
	public static final String DB_Name = "UEASY.db";

	/* Table names */
	public static final String Table_Campus = "Campus";
	public static final String Table_Building = "Building";
	public static final String Table_OtherAmenities = "OtherAmenities";
	public static final String Table_Classroom = "Classroom";
	public static final String Table_RoomUtility = "RoomUtility";

	/* Campus Variables */
	public static final String Campus_ID = "c_id";
	public static final String Campus_name = "c_name";
	public static final String Campus_addr = "c_addr";
	public static final String Campus_desc = "c_desc";

	/* Building Variables */
	public static final String Building_ID = "b_id";
	public static final String Building_name = "b_name";
	public static final String Building_desc = "b_desc";
	public static final String Building_pic = "b_pic";
	public static final String Building_latitude = "b_latitude";
	public static final String Building_longitude = "b_longitude";
	public static final String Building_catName = "b_catName";

	/* Other Amenities Variables */
	public static final String OtherAmenities_ID = "oa_id";
	public static final String OtherAmenities_name = "oa_name";
	public static final String OtherAmenities_pic = "oa_pic";
	public static final String OtherAmenities_desc = "oa_desc";
	public static final String OtherAmenities_catName = "oa_catName";
	public static final String OtherAmenities_bLevel = "oa_BLevelNum";
	public static final String OtherAmenities_latitude = "oa_latitude";
	public static final String OtherAmenities_longitude = "oa_longitude";

	/* Classroom Variables */
	public static final String Classroom_ID = "cr_id";
	public static final String Classroom_name = "cr_name";
	public static final String Classroom_pic = "cr_pic";
	public static final String Classroom_sched = "cr_sched";
	public static final String Classroom_desc = "cr_desc";
	public static final String Classroom_catName = "cr_catName";
	public static final String Classroom_latitude = "cr_latitude";
	public static final String Classroom_longitude = "cr_longitude";
	public static final String Classroom_bLevel = "cr_BLevelNum";

	/* Room Utility Variables */
	public static final String RoomUtility_ID = "ru_id";
	public static final String RoomUtility_day = "ru_day";
	public static final String RoomUtility_time = "ru_time";
	public static final String RoomUtility_teacher_name = "ru_teacher_name";
	public static final String RoomUtility_subj_code = "cr_subj_code";

	public static final int DB_Version = 1;
	int oldVersion;
	int newVersion;
	private String queryCampus, queryBuilding, queryOtherAmenities,
			queryClassrooom, queryRoomUtility;
	private String sql8;
	private Context context;

	public Database_old(Context context) {
		super(context, DB_Name, null, DB_Version);
		this.context = context;
		// Message.message(context, "constructor is called");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// Message.message(context, " onCreate is called");

		queryCampus = "CREATE TABLE " + Table_Campus + "(" + Campus_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Campus_name
				+ " TEXT, " + Campus_addr + " TEXT, " + Campus_desc + " TEXT);";
		db.execSQL(queryCampus);

		queryBuilding = "CREATE TABLE " + Table_Building + "(" + Building_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Campus_ID
				+ " INTEGER, " + Building_name + " TEXT, " + Building_desc
				+ " TEXT, " + Building_pic + " TEXT, " + Building_latitude
				+ " TEXT," + Building_longitude + " TEXT" + Building_catName
				+ " TEXT);";
		db.execSQL(queryBuilding);

		queryOtherAmenities = "CREATE TABLE " + Table_OtherAmenities + "("
				+ OtherAmenities_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ Campus_ID + " INTEGER, " + Building_ID + " INTEGER, "
				+ OtherAmenities_name + " TEXT, " + OtherAmenities_desc
				+ " TEXT, " + OtherAmenities_pic + " TEXT, "
				+ OtherAmenities_bLevel + " INTEGER, "
				+ OtherAmenities_latitude + " TEXT," + OtherAmenities_longitude
				+ " TEXT, " + OtherAmenities_catName + " TEXT);";
		db.execSQL(queryOtherAmenities);

		queryClassrooom = "CREATE TABLE " + Table_Classroom + "("
				+ Classroom_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ Campus_ID + " INTEGER, " + Building_ID + " INTEGER, "
				+ Classroom_name + " TEXT, " + Classroom_sched + " TEXT, "
				+ Classroom_desc + " TEXT, " + Classroom_pic + " TEXT, "
				+ Classroom_bLevel + " INTEGER, " + Classroom_latitude
				+ " TEXT," + Classroom_longitude + " TEXT, "
				+ Classroom_catName + " TEXT);";
		;
		db.execSQL(queryClassrooom);

		queryRoomUtility = "CREATE TABLE " + Table_RoomUtility + "("
				+ RoomUtility_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ Classroom_ID + " INTEGER, " + RoomUtility_day + " TEXT, "
				+ RoomUtility_time + " TEXT, " + RoomUtility_teacher_name
				+ " TEXT, " + RoomUtility_subj_code + " TEXT);";
		db.execSQL(queryRoomUtility);

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

	public List<String> ClassroomCode(String roomCode) {

		SQLiteDatabase db = this.getWritableDatabase();
		List<String> retVal = new ArrayList<String>();
		;
		String[] columns = { Classroom_name };
		String[] args = { roomCode + "%" };
		Cursor cursor = db.query(Table_Classroom, columns, Classroom_name
				+ " LIKE ?", args, null, null, null);

		while (cursor.moveToNext()) {
			int ndx1 = cursor.getColumnIndex(Classroom_name);
			retVal.add(cursor.getString(ndx1));
		}

		return retVal;

	}

	public List<String> BuildingName() {
		SQLiteDatabase db = this.getWritableDatabase();
		// List<Amenity> test = new ArrayList<Amenity>();
		List<String> list = new ArrayList<String>();
		String[] columns = { Building_name };
		Cursor cursor = db.query(Table_Building, columns, null, null, null,
				null, null);
		while (cursor.moveToNext()) {
			int ndx1 = cursor.getColumnIndex(Building_name);
			list.add(cursor.getString(ndx1));
		}

		return list;
	}

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

	public DatabaseObject get_DatabaseObject(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(Table_Classroom, new String[] { Classroom_ID,
				Classroom_latitude, Classroom_longitude, Classroom_name,
				Classroom_desc }, Classroom_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		DatabaseObject data = new DatabaseObject(Integer.parseInt(cursor
				.getString(0)), Double.parseDouble(cursor.getString(8)),
				Double.parseDouble(cursor.getString(9)), cursor.getString(4),
				cursor.getString(6));
		// return database object
		return data;
	}

	// Getting All database objects
	public List<DatabaseObject> getAllDatabaseObject(String tablename) {
		List<DatabaseObject> contactList = new ArrayList<DatabaseObject>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + tablename;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		String[] cNames = cursor.getColumnNames();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {

			do {
				DatabaseObject data = new DatabaseObject();
				for (int i = 0; i < cNames.length; i++) {
					if (cNames[i].contains("_ID")) {
						data.setID(Integer.parseInt(cursor.getString(cursor
								.getColumnIndex(cNames[i++]))));
					}
					if (cNames[i].contains("_latitude")) {
						data.setLat(Double.parseDouble(cursor.getString(cursor
								.getColumnIndex(cNames[i++]))));
					}
					if (cNames[i].contains("_longitude")) {
						data.setLong(Double.parseDouble(cursor.getString(cursor
								.getColumnIndex(cNames[i++]))));
					}
					if (cNames[i].contains("_name")) {
						data.setName(cursor.getString(cursor
								.getColumnIndex(cNames[i++])));
					}
					if (cNames[i].contains("_desc")) {
						data.setInfo(cursor.getString(cursor
								.getColumnIndex(cNames[i++])));
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

	public List<String> OtherAmenities(String type) {

		SQLiteDatabase db = this.getWritableDatabase();
		List<String> retVal = new ArrayList<String>();
		String[] whereArgs = { type };

		String[] columns2 = { OtherAmenities_name };
		Cursor cursor2 = db.query(Table_OtherAmenities, columns2,
				OtherAmenities_catName + "=?", whereArgs, null, null, null);

		while (cursor2.moveToNext()) {
			int ndx2 = cursor2.getColumnIndex(OtherAmenities_name);
			retVal.add(cursor2.getString(ndx2));
		}

		return retVal;
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
//
//		String query = "SELECT " + Building_name + " " + " FROM "
//				+ Table_Building + " " + " UNION ALL SELECT " + Classroom_name
//				+ " " + " FROM " + Table_Classroom + " UNION ALL SELECT "
//				+ OtherAmenities_name + " " + " FROM " + Table_OtherAmenities;
		

		 // Retrieving all names of the Buildings
		 String[] columns = { Building_name };
		 Cursor cursor = db.query(Table_Building, columns, null, null, null,
		 null, null);
		
		 // Retrieving all names of the Classrooms
		 String[] columns2 = { Classroom_name };
		 Cursor cursor2 = db.query(Table_Classroom, columns2, null, null,
		 null,
		 null, null);
		
		 // Retrieving all names of the Other Amenities
		 String[] columns3 = { OtherAmenities_name };
		 Cursor cursor3 = db.query(Table_OtherAmenities, columns3, null, null,
		 null, null, null);
		
		 // Adding all Building names to the ArrayList
		 while (cursor.moveToNext()) {
		 int ndx1 = cursor.getColumnIndex(Building_name);
		 retVal.add(cursor.getString(ndx1));
		
		 }
		
		 // Adding all Classroom names to the ArrayList
		 while (cursor2.moveToNext()) {
		 int ndx2 = cursor2.getColumnIndex(Classroom_name);
		 retVal.add(cursor2.getString(ndx2));
		 }
		
		 // Adding all OtherAmenity names to the ArrayList
		 while (cursor3.moveToNext()) {
		 int ndx3 = cursor3.getColumnIndex(OtherAmenities_name);
		 retVal.add(cursor3.getString(ndx3));
		 }

//		Cursor cursor = db.rawQuery(query, null);
//		cursor.moveToFirst();
//		int i = 0;
//		while (cursor.moveToNext()) {
//			retVal.add(cursor.getString(i++));
//		}
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
}
