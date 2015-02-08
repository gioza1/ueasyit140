package ueasy.it140.activities;

import java.util.ArrayList;
import java.util.List;

import ueasy.it140.R;
import ueasy.it140.database.Database;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.bonuspack.overlays.FolderOverlay;
import org.osmdroid.bonuspack.overlays.InfoWindow;
import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

public class Map extends Activity implements MapEventsReceiver {
	ArrayList<Marker> anotherOverlayItemArray;
	Database DB;
	Bundle b;

	// ===========================================================
	// Constants
	// ===========================================================
	public static final String TITLE = "Limited scroll area";
	public static MapView mapView;
	private static final int MENU_LIMIT_SCROLLING_ID = Menu.FIRST;

	private static final BoundingBoxE6 sCentralParkBoundingBox;
	private static final SafePaint sPaint;

	// ===========================================================
	// Fields
	// ===========================================================

	// private ShadeAreaOverlay mShadeAreaOverlay;

	static {
		sCentralParkBoundingBox = new BoundingBoxE6(10.35826, 123.91455,
				10.35105, 123.90402);
		sPaint = new SafePaint();
		sPaint.setColor(Color.argb(50, 255, 0, 0));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_map);
		
		getActionBar().setTitle("UEASY-TC");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#048abf")));
		mapView = new MapView(this, 256);
		// constructor
		mapView.setClickable(true);
		// mapView.setBuiltInZoomControls(true);
		setContentView(mapView); // displaying the MapView

		mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
		mapView.setMinZoomLevel(17);
		// mapView.setMaxZoomLevel(19);
		mapView.getController().setZoom(17); // set initial zoom-level, depends
												// on your need
		mapView.getController().setCenter(new GeoPoint(10.35421, 123.91152));
		mapView.setUseDataConnection(false);
		mapView.setMultiTouchControls(true);

		MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
		DB = new Database(this);
		
		String table = "Building";
		b = getIntent().getExtras();
		if(b!=null)
		{
		table = b.getString("tableName");
		}
		
		Marker oi;
		List<DatabaseObject> K = DB.getAllDatabaseObject(table);
//		List<GeoPoint> t = DB.AllAmenity();
		anotherOverlayItemArray = new ArrayList<Marker>();
		if (K != null)
			// Toast.makeText(this, cn.getName(), Toast.LENGTH_SHORT).show();
			for (DatabaseObject cn : K) {
				oi = new Marker(mapView);
				oi.setPosition(new GeoPoint(cn.getLat(), cn.getLong()));
				oi.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
				oi.setIcon(getResources()
						.getDrawable(R.drawable.amenity_marker));
				oi.setTitle(cn.getName());
				InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble, mapView);
				oi.setInfoWindow(infoWindow);
				anotherOverlayItemArray.add(oi);
			}
	
		mapView.getOverlays().addAll(anotherOverlayItemArray);
		mapView.getOverlays().add(0, mapEventsOverlay);
		// ---

		// Add Scale Bar
		ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
		mapView.getOverlays().add(myScaleBarOverlay);
		
//		InfoWindow.getOpenedInfoWindowsOn(mapView)
	}

	// protected void addOverlays() {
	//
	//
	// final Context context = this;
	//
	// mShadeAreaOverlay = new ShadeAreaOverlay(context);
	// mapView.getOverlayManager().add(mShadeAreaOverlay);
	//
	// setLimitScrolling(true);
	// // setHasOptionsMenu(true);
	// }

	// protected void setLimitScrolling(boolean limitScrolling) {
	// if (limitScrolling) {
	// mapView.getController().setZoom(15);
	// mapView.setScrollableAreaLimit(sCentralParkBoundingBox);
	// mapView.setMinZoomLevel(15);
	// mapView.setMaxZoomLevel(18);
	// mapView.getController().animateTo(sCentralParkBoundingBox.getCenter());
	// mShadeAreaOverlay.setEnabled(true);
	// mapView.invalidate();
	// } else {
	// mapView.setScrollableAreaLimit(null);
	// mapView.setMinZoomLevel(null);
	// mapView.setMaxZoomLevel(null);
	// mShadeAreaOverlay.setEnabled(false);
	// mapView.invalidate();
	// }
	// }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_about:
			startActivity(new Intent(this, AboutAndFaqs.class));
			break;
		case R.id.actionbar_search:
			startActivity(new Intent(this, Search.class));
			break;
		case R.id.action_search:
			startActivity(new Intent(this, Search.class));
			break;
		case R.id.action_category:
			startActivity(new Intent(this, Category.class));
			break;
		case R.id.action_map:
			Toast.makeText(this, "You're already viewing me", Toast.LENGTH_SHORT).show();
//			Intent i = new Intent(this,Map.class);
//			Bundle c = getIntent().getExtras();
//			String table = "Classroom";
//			if(c!=null)
//				table = c.getString("tableName");
//			i.putExtra("tableName", table);
//			startActivity(i);
			
			break;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean longPressHelper(GeoPoint arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean singleTapConfirmedHelper(GeoPoint arg0) {
		// TODO Auto-generated method stub
		InfoWindow.closeAllInfoWindowsOn(mapView);
		return true;
	}
	
	private class MyInfoWindow extends InfoWindow{


		   public MyInfoWindow(int layoutResId, MapView mapView) {
//			  this.layoutResId = layoutResId;
//			  mView = mapView;
//			  amenityName = name;
		      super(layoutResId, mapView); 
		   }
		   





		   public void onClose() {
		   }

		   public void onOpen(Object marker) {
		      LinearLayout layout = (LinearLayout) mView.findViewById(R.id.bubble_layout);
		      Marker current = (Marker) marker;
		      for(int i=0; i<mapView.getOverlays().size(); ++i){
		    	  Overlay o = mapView.getOverlays().get(i);
		    	  if(o instanceof Marker){
		    	  Marker m = (Marker) o;;
		    	  if(!m.getTitle().equals(current.getTitle()))
		    	  m.closeInfoWindow();
		    	  }
		    	  }
		    final String title = current.getTitle();
		      TextView txtTitle = (TextView) mView.findViewById(R.id.bubble_title);
//		      TextView txtDescription = (TextView) mView.findViewById(R.id.bubble_description);
//		      TextView txtSubdescription = (TextView) mView.findViewById(R.id.bubble_subdescription);

		      txtTitle.setText(title);
//		      txtDescription.setText("Click here to view details!");
//		      txtSubdescription.setText("You can also edit the subdescription");
		      layout.setOnClickListener(new OnClickListener() {
		         public void onClick(View v) {
		            // Override Marker's onClick behaviour here
		        	 Intent i = new Intent(v.getContext(),AmenityBuilding.class);
		        	 i.putExtra("AmenityName",title);
		        	 startActivity(i);
		        	 
		         }
		      }); 
		   } 
		}
	

	// class ShadeAreaOverlay extends SafeDrawOverlay {
	//
	// public ShadeAreaOverlay(Context ctx) {
	// super(ctx);
	// }
	//
	// @Override
	// protected void drawSafe(ISafeCanvas c, MapView osmv, boolean shadow) {
	// final Projection proj = osmv.getProjection();
	// // Rect area = proj.toPixels();
	// // c.drawRect(area, sPaint);
	// }
	// }
}
