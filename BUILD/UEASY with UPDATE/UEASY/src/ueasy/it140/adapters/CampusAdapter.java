package ueasy.it140.adapters;

import java.util.ArrayList;

import ueasy.it140.R;
import ueasy.it140.models.CampusModel;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CampusAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<CampusModel> CampusModel;

	public CampusAdapter(Context context, ArrayList<CampusModel> CampusModel) {
		super(context, R.layout.activity_campus_item);
		this.context = context;
		this.CampusModel = CampusModel;

	}
	
	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.activity_campus_item, parent, false);
		TextView name = (TextView) rowView
				.findViewById(R.id.campusName);
		TextView address = (TextView) rowView
				.findViewById(R.id.campusAddress);
		ImageView image = (ImageView) rowView
				.findViewById(R.id.campusImage);
		name.setText(CampusModel.get(position).campusName);
		address.setText(CampusModel.get(position).campusAddress);

		image.setImageResource(CampusModel.get(position).campusImage);

		return rowView;
	}
	

	@Override
	public int getCount() {
		return CampusModel.size();
	}

}