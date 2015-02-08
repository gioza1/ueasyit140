package ueasy.it140.adapters;

import ueasy.it140.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final int[] images;
	private final String[] name;

	public CategoryAdapter(Context context, int[] images, String[] name) {
		super(context, R.layout.activity_category, name);
		this.context = context;
		this.images = images;
		this.name = name;
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		@SuppressWarnings("static-access")
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.activity_category_list,
				parent, false);

		ImageView img = (ImageView) rowView.findViewById(R.id.catIcon);
		TextView cname = (TextView) rowView.findViewById(R.id.catName);

		img.setImageResource(images[position]);
		cname.setText(name[position]);

		return rowView;
	}

}
