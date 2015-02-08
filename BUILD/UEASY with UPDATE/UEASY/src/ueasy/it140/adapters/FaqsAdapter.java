package ueasy.it140.adapters;

import ueasy.it140.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FaqsAdapter extends ArrayAdapter<String> {
	private final String[] pos;
	private final String[] name;
	private final Context context;

	public FaqsAdapter(Context context, String[] pos, String[] name){
		super(context,R.layout.fragment_faqs_item
				,pos);
		this.pos = pos;
		this.name = name;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.fragment_faqs_item, parent, false);
		TextView pos1 = (TextView) rowView.findViewById(R.id.faqQuestion);
		TextView name1 = (TextView) rowView.findViewById(R.id.faqAnswer);
		pos1.setText(pos[position]);
		name1.setText(name[position]);
		 
		return rowView;
	}
}

