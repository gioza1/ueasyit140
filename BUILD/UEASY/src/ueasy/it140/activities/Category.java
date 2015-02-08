//package ueasy.it140.activities;
//
//import ueasy.it140.R;
//import ueasy.it140.adapters.CategoryAdapter;
//import android.app.ActionBar;
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.v4.app.NavUtils;
//import android.text.Html;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class Category extends Activity {
//	final String[] name = { "Building", "Room", "Food and Beverages",
//			"Facilities", "Offices" };
//	final int[] icons = { R.drawable.ic_bldg, R.drawable.ic_room,
//			R.drawable.ic_food, R.drawable.ic_facilities, R.drawable.ic_offices };
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_category);
//		getActionBar().setDisplayHomeAsUpEnabled(true);
//		ListView lv = (ListView) findViewById(R.id.category_list);
//
//		CategoryAdapter adap = new CategoryAdapter(Category.this, icons, name);
//		lv.setAdapter(adap);
//
//		ActionBar ab = getActionBar();
//		ab.setTitle(Html.fromHtml("<font color='#ffffff'>CATEGORY </font>"));
//		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#048abf")));
//
//		lv.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				TextView name = (TextView) view.findViewById(R.id.catName);
//				String n = name.getText().toString();
//				Intent i;
//				if (n.equals("Room")) {
//					i = new Intent(getApplicationContext(), RoomCategory.class);
//					i.putExtra("name", n);
//					startActivity(i);
//				} else {
//					i = new Intent(getApplicationContext(), SpecificCategory.class);
//					i.putExtra("name", n);
//					startActivity(i);
//				}
//			}
//		});
//
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// TODO Auto-generated method stub
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.category, menu);
//		return super.onCreateOptionsMenu(menu);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// TODO Auto-generated method stub
//		switch(item.getItemId())
//		{
//		case R.id.action_about:
//			startActivity(new Intent(this,AboutAndFaqs.class));
//			break;
//		case R.id.action_search:
//			startActivity(new Intent(this,Search.class));
//			break;
//		case R.id.action_category:
//			startActivity(new Intent(this,Category.class));
//			break;
//		case R.id.action_map:
//			startActivity(new Intent(this,Map.class));
//			break;
//	    case android.R.id.home:
//	        NavUtils.navigateUpFromSameTask(this);
//	        break;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//}

package ueasy.it140.activities;

import ueasy.it140.R;
import ueasy.it140.adapters.CategoryAdapter;
import ueasy.it140.specificCategories.BuildingCategory;
import ueasy.it140.specificCategories.OtherAmenitiesCategory;
import ueasy.it140.specificCategories.RoomCategory;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Category extends Activity {
	final String[] name = { "Building", "Room", "Food and Beverages",
			"Facilities", "Offices" };
	final int[] icons = { R.drawable.ic_bldg, R.drawable.ic_room,
			R.drawable.ic_food, R.drawable.ic_facilities, R.drawable.ic_offices };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ListView lv = (ListView) findViewById(R.id.category_list);

		CategoryAdapter adap = new CategoryAdapter(Category.this, icons, name);
		lv.setAdapter(adap);

		ActionBar ab = getActionBar();
		ab.setTitle(Html.fromHtml("<font color='#ffffff'>CATEGORY </font>"));
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#048abf")));
		ab.setDisplayHomeAsUpEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView name = (TextView) view.findViewById(R.id.catName);
				String n = name.getText().toString();
				Intent i;
				if (n.equals("Room")) {
					i = new Intent(getApplicationContext(), RoomCategory.class);
					i.putExtra("name", n);
					startActivity(i);
				} else if (n.equals("Building")) {
					i = new Intent(getApplicationContext(),
							BuildingCategory.class);
					i.putExtra("name", n);
					startActivity(i);
				} else {
					i = new Intent(getApplicationContext(),
							OtherAmenitiesCategory.class);
					i.putExtra("name", n);

					startActivity(i);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.category, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
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
