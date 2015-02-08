package ueasy.it140.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ueasy.it140.R;
import ueasy.it140.database.Database;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends Activity {

	Database DB;
	List<String> amenities;
	// List view
	private ListView lv;

	// Listview Adapter
	ArrayAdapter<String> adapter;

	// Search EditText
	EditText inputSearch;

	// ArrayList for Listview
	ArrayList<HashMap<String, String>> productList;

	private SimpleCursorAdapter dataAdapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		ActionBar ab = getActionBar();
		ab.setTitle(Html.fromHtml("<font color='#ffffff'>SEARCH </font>"));
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#048abf")));

		DB = new Database(this);

		// Listview Data
		amenities = new ArrayList<String>();

		amenities = DB.getAllAmenityName();

		lv = (ListView) findViewById(R.id.list_view);
		inputSearch = (EditText) findViewById(R.id.inputSearch);

		// Adding items to listview
		adapter = new ArrayAdapter<String>(this, R.layout.search_item,
				R.id.amenity_name, amenities);

		Cursor cursor1 = DB.getAllAmenityNameCursor();
		if (cursor1 != null) {
			cursor1.moveToFirst();
		}
		String[] columnNames = { "amenity_name" };
		int[] resIds = { R.id.amenity_name };
		dataAdapter = new SimpleCursorAdapter(this, R.layout.search_item,
				cursor1, columnNames, resIds);
		// Assign adapter to ListView
		lv.setAdapter(dataAdapter);
		lv.setVisibility(View.INVISIBLE);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				// Get the cursor, positioned to the corresponding row in the
				// result set
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				// Get the state's capital from this row in the database.
				String item = cursor.getString(cursor
						.getColumnIndexOrThrow("amenity_name"));
				Intent i = new Intent(getBaseContext(), AmenityBuilding.class);
				i.putExtra("AmenityName", item);
				startActivity(i);

			}
		});

		inputSearch.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.length() >= 3) {
					dataAdapter.getFilter().filter(s.toString());
					lv.setVisibility(View.VISIBLE);
				}
				if (s.length() < 3) {
					lv.setVisibility(View.INVISIBLE);
				}
			}
		});

		dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				return DB.getAmenityNameCursor(constraint.toString());
			}
		});

	}
}
