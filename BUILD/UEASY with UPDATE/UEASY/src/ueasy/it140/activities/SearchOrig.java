package ueasy.it140.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ueasy.it140.R;
import ueasy.it140.database.Database;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchOrig extends Activity {

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

		lv.setAdapter(adapter);
		lv.setVisibility(View.INVISIBLE);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				String item = (String) adapter.getItem(position);
				Intent i = new Intent(getBaseContext(), AmenityBuilding.class);
				i.putExtra("AmenityName", item);
				startActivity(i);
			}
		});
		/**
		 * Enabling Search Filter
		 * */
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				if (cs.length() >= 3) {
					lv.setVisibility(View.VISIBLE);
					// for(String p: amenities){
					// if(p.contains(cs))
					SearchOrig.this.adapter.getFilter().filter(cs);
					// }
				}

				if (cs.length() < 3) {
					lv.setVisibility(View.INVISIBLE);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

}