package ueasy.it140.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ueasy.it140.R;
import ueasy.it140.adapters.ExpandableListAdapter;
import ueasy.it140.database.Database;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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

public class RoomUtilization extends Activity {
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	Database DB;
	Bundle b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.room_util_main);

		// initialize DB
		DB = new Database(this);

		Intent i = getIntent();
		String cname = i.getStringExtra("ClassroomName");
		b = getIntent().getExtras();

		ActionBar ab = getActionBar();
		ab.setTitle(Html.fromHtml("<font color='#ffffff'>"
				+ b.getString("ClassroomName", "NULL") + " </font>"));
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#048abf")));
		ab.setDisplayHomeAsUpEnabled(true);

		Toast.makeText(this, "" + cname, Toast.LENGTH_SHORT).show();
		// get the
		expListView = (ExpandableListView) findViewById(R.id.roomUtil_expand);

		// preparing list data
		prepareListData(cname);

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
	private void prepareListData(String cname) {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		Toast.makeText(this, "prepareListData!", Toast.LENGTH_SHORT).show();

		// Adding child data
		listDataHeader.add("Monday");
		listDataHeader.add("Tuesday");
		listDataHeader.add("Wednesday");
		listDataHeader.add("Thursday");
		listDataHeader.add("Friday");
		listDataHeader.add("Saturday");

		String sched[] = DB.roomtUtil(cname);
		// Adding child data
		List<String> monday;
		List<String> tuesday;
		List<String> wednesday;
		List<String> thursday;
		List<String> friday;
		List<String> saturday;
		String time, teacher, subCode;
		monday = new ArrayList<String>();
		tuesday = new ArrayList<String>();
		wednesday = new ArrayList<String>();
		thursday = new ArrayList<String>();
		friday = new ArrayList<String>();
		saturday = new ArrayList<String>();

		try {

			for (int j = 0; j < 6; j++) {
				JSONArray arr = new JSONArray(sched[j]);
				for (int i = 0; i < arr.length(); i++) {
					JSONObject obj = (JSONObject) arr.get(i);
					obj.get("time");
					obj.get("teacher");
					obj.get("subCode");
					time = obj.getString("time").toString();
					teacher = obj.getString("teacher").toString();
					subCode = obj.getString("subCode").toString();

					if (j == 0)
						monday.add(time + " " + subCode + " " + teacher);

					else if (j == 1)
						tuesday.add(time + " " + subCode + " " + teacher);

					else if (j == 2)
						wednesday.add(time + " " + subCode + " " + teacher);

					else if (j == 3)
						thursday.add(time + " " + subCode + " " + teacher);

					else if (j == 4)
						friday.add(time + " " + subCode + " " + teacher);

					else
						saturday.add(time + " " + subCode + " " + teacher);

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listDataChild.put(listDataHeader.get(0), monday); // Header, Child data
		listDataChild.put(listDataHeader.get(1), tuesday);
		listDataChild.put(listDataHeader.get(2), wednesday);
		listDataChild.put(listDataHeader.get(3), thursday);
		listDataChild.put(listDataHeader.get(4), friday);
		listDataChild.put(listDataHeader.get(5), saturday);

	}
}