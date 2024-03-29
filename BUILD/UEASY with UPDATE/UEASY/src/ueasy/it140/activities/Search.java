package ueasy.it140.activities;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ueasy.it140.R;
import ueasy.it140.database.Database;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Search extends ListActivity implements OnScrollListener {
	boolean prep;
	Database DB;
	List<String> amenities;
	// List view
	private static Toast results;
	private ListView lv;
	View footerView;

	// Listview Adapter
	ArrayAdapter<String> adapter;
	// Search EditText
	EditText inputSearch;
	int currentPage;

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
		ab.setDisplayHomeAsUpEnabled(true);

		DB = new Database(this);
		
		results = Toast.makeText(this, "No search results found!",
				Toast.LENGTH_SHORT);
		// Listview Data
		// amenities = new ArrayList<String>();

		// amenities = DB.getAllAmenityName();
		// footerView = (View) findViewById(R.layout.search_footer);
		// View footerView = ((LayoutInflater) this
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
		// R.layout.footer_layout, null, false);

		// lv = (ListView) findViewById(R.id.list);
		lv = getListView();

		// lv.addFooterView(footerView);
		inputSearch = (EditText) findViewById(R.id.inputSearch);

		// Adding items to listview
		// adapter = new ArrayAdapter<String>(this, R.layout.search_item,
		// R.id.amenity_name, amenities);

		// Cursor cursor1 = DB.getAllAmenityNameCursor();
		Cursor cursor1 = DB.getSearchResults("LB445", 0, 0);
		if (cursor1 != null) {
			cursor1.moveToFirst();
		}
		String[] columnNames = { "amenity_name" };
		int[] resIds = { R.id.amenity_name };
		dataAdapter = new SimpleCursorAdapter(this, R.layout.search_item,
				cursor1, columnNames, resIds);
		// Assign adapter to ListView
		// setListAdapter(dataAdapter);

		// lv.removeFooterView(footerView);
		lv.setAdapter(dataAdapter);
		// lv.addFooterView(footerView);
		lv.setOnScrollListener(this);
		lv.setVisibility(View.INVISIBLE);

		inputSearch.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (count < before) {
					currentPage = 0;
					Cursor amenityCursor = DB.getSearchResults("", 0, 0);
					dataAdapter.changeCursor(amenityCursor);
					dataAdapter.notifyDataSetChanged();
					System.out.println(currentPage);
					if (lv.getVisibility() != View.INVISIBLE)
						lv.setVisibility(View.INVISIBLE);
				}
				if (s.length() >= 3) {
					dataAdapter.getFilter().filter(s.toString());

					lv.setVisibility(View.VISIBLE);

				}

			}
		});

		dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				// return DB.getAmenityNameCursor(constraint.toString());
				currentPage++;
				Cursor cursor = DB.getSearchResults(constraint.toString(), 0,
						10);
				if (cursor.getCount() == 0)
					toast();
				return cursor;
			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {

				Cursor cursor = (Cursor) lv.getItemAtPosition(position);
				// Get the state's capital from this row in the database.
				String item = cursor.getString(cursor
						.getColumnIndexOrThrow("amenity_name"));
				Intent i = new Intent(getBaseContext(), AmenityBuilding.class);
				i.putExtra("AmenityName", item);
				startActivity(i);
			}
		});
	}

	public void toast() {
		// results.cancel();
		results.show();

	}

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		results.cancel();
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		results.cancel();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		results.cancel();
		super.onDestroy();
	}

	// /**
	// * Method to detect scroll on listview
	// */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// Leave this empty
	}

	/**
	 * Method to detect if scrolled to end of listview
	 */
	@Override
	public void onScrollStateChanged(AbsListView listView, int scrollState) {
		if (scrollState == SCROLL_STATE_IDLE) {
			// if (inputSearch.getText().toString().length() >= 2) {
			if (listView.getLastVisiblePosition() >= listView.getCount() - 1) {
				currentPage++;
				loadMoreAmenities(inputSearch.getText().toString(), currentPage);
			}
			// }
		}
	}

	//
	// /**
	// * Method to load more articles if scrolled to end of listview
	// */
	private void loadMoreAmenities(String keyword, int currentPage) {
		int from = currentPage * 1;
		int to = currentPage * 10;
		if (DB.getSearchResultsCount(keyword) < to) {
			Log.d("Database", "Not enough articles available! ArticleCount: "
					+ dataAdapter.getCount() + " ,tried to load " + to);
			// lv.removeFooterView(footerView);

		} else {

			System.out.println("load is called");
			Log.d("Database", "Load the next articles, from " + from + " to "
					+ to);
			Cursor amenityCursor = DB.getSearchResults(keyword, 0, to);
			dataAdapter.changeCursor(amenityCursor);
			dataAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.search_menu, menu);

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
			//
			NavUtils.navigateUpFromSameTask(this);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem item = menu.findItem(R.id.action_search);
		item.setEnabled(false);
		return true;
	}

}

// }