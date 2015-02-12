package ueasy.it140.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ueasy.it140.R;
import ueasy.it140.database.Database;
import ueasy.it140.adapters.ExpandableListAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class LevelExpandable extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	Bundle b;
	String bldgName;
	int bId;
	Database DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_main);
		b = getIntent().getExtras();
		bldgName = b.getString("BuildingName", "NULL");
		bId = b.getInt("BuildingID");
		ActionBar ab = getActionBar();
		ab.setTitle(Html.fromHtml("<font color='#ffffff'>" + bldgName
				+ " </font>"));
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#048abf")));
		ab.setDisplayHomeAsUpEnabled(true);
		// get the
		// expListView = (ExpandableListView) findViewById(R.id.level_expand);
		DB = new Database(this);
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

			}
		});

		/*----------------*/
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		int totalLevels = DB.getNumBldgLevel(bldgName);

		Toast.makeText(getBaseContext(), bldgName, Toast.LENGTH_SHORT).show();
		Toast.makeText(getBaseContext(), Integer.toString(totalLevels),
				Toast.LENGTH_SHORT).show();

		// }
		for (int i = 0; i < totalLevels; i++) {
			listDataHeader.add("Level " + (i + 1));
			List<String> level = new ArrayList<String>();
//			level.addAll(DB.getAllAmenityInBldgLevel(bId, (i + 1)));
			listDataChild.put("Level " + (i + 1), level);
		}
		// // Adding child data
		// listDataHeader.add("Level 3");
		// listDataHeader.add("Level 2");
		// listDataHeader.add("Level 1");
		//
		// // Adding child data
		// List<String> level3 = new ArrayList<String>();
		// level3.add("LB 301");
		// level3.add("LB 302");
		// level3.add("LB 303");
		// level3.add("LB 304");
		// level3.add("LB 305");
		// level3.add("LB 306");
		// level3.add("LB 307");
		//
		// List<String> level_2 = new ArrayList<String>();
		// level_2.add("LB 201");
		// level_2.add("LB 202");
		// level_2.add("LB 203");
		// level_2.add("LB 204");
		// level_2.add("LB 205");
		// level_2.add("LB 206");
		//
		// List<String> level_1 = new ArrayList<String>();
		// level_1.add("LB 101");
		// level_1.add("LB 102");
		// level_1.add("LB 103");
		// level_1.add("LB 104");
		// level_1.add("LB 105");
		//
		// listDataChild.put(listDataHeader.get(0), level3); // Header, Child
		// // data
		// listDataChild.put(listDataHeader.get(1), level_2);
		// listDataChild.put(listDataHeader.get(2), level_1);

	}
}
