package ueasy.it140.specificCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ueasy.it140.R;
import ueasy.it140.activities.AboutAndFaqs;
import ueasy.it140.activities.AmenityBuilding;
import ueasy.it140.activities.Category;
import ueasy.it140.activities.Map;
import ueasy.it140.activities.Search;
import ueasy.it140.adapters.RoomAdapter;
import ueasy.it140.database.Database;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class RoomCategory extends Activity {

	RoomAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	Database DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room);

		DB = new Database(this);

		Intent i = getIntent();
		String title = i.getStringExtra("name");

		ActionBar ab = getActionBar();
		ab.setTitle(Html.fromHtml("<font color='#ffffff'>"
				+ title.toUpperCase() + "</font>"));
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#048abf")));
		ab.setDisplayHomeAsUpEnabled(true);
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new RoomAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Expanded",
				// Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Collapsed",
				// Toast.LENGTH_SHORT).show();
				//
			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
//				 Toast.makeText(
//				 getApplicationContext(), listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get( childPosition), Toast.LENGTH_SHORT)
//				 .show();
				   String item = (String) listDataChild.get(listDataHeader.get(groupPosition)).get( childPosition);
					 Intent i = new Intent(v.getContext(),AmenityBuilding.class);
			    	 i.putExtra("AmenityName",item);
			    	 startActivity(i);
//				
				return false;
			}
		});

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		int width = getResources().getDisplayMetrics().widthPixels;
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			expListView.setIndicatorBounds(width - getPixelValue(40), width
					- getPixelValue(10));
		} else {
			expListView.setIndicatorBounds(width - getPixelValue(40), width
					- getPixelValue(10));
		}
	}

	public int getPixelValue(int dp) {

		final float scale = getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("LB");
		listDataHeader.add("PE");
		listDataHeader.add("RH");

		// Adding child data
		List<String> lb = new ArrayList<String>();
		lb = DB.Amenities("ClassroomLB");
		String type = "ClassroomLB";
		Toast.makeText(this, Integer.toString(lb.size()), Toast.LENGTH_SHORT).show();
		String roomCode = type.substring(9, 11);
		Toast.makeText(this, roomCode, Toast.LENGTH_SHORT).show();

		List<String> pe = new ArrayList<String>();
		pe = DB.Amenities("ClassroomPE");

		List<String> rh = new ArrayList<String>();
		rh = DB.Amenities("ClassroomRH");

		listDataChild.put(listDataHeader.get(0), lb); // Header, Child data
		listDataChild.put(listDataHeader.get(1), pe);
		listDataChild.put(listDataHeader.get(2), rh);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.specific_category, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.showInMap:
			Intent i = new Intent(this, Map.class);
			i.putExtra("tableName", "Classroom");
			// i.putExtra
			startActivity(i);

			break;
		case R.id.action_about:
			startActivity(new Intent(this, AboutAndFaqs.class));
			break;
		case R.id.action_search:
			startActivity(new Intent(this, Search.class));
			break;
		case R.id.action_category:
			startActivity(new Intent(this, Category.class));
			break;
		case R.id.action_map:
			startActivity(new Intent(this, Map.class));
			break;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}