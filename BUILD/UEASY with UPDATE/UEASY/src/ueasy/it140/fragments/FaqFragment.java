package ueasy.it140.fragments;

import ueasy.it140.R;
import ueasy.it140.adapters.FaqsAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FaqFragment extends Fragment {
	public FaqFragment() {
	}

	/*
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) {
	 * 
	 * View rootView = inflater.inflate(R.layout.fragment_staff, container,
	 * false);
	 * 
	 * return rootView; }
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_faqs, container, false);

		ListView lv = (ListView) v.findViewById(R.id.frame_container);
		final String[] pos = new String[] { "Question 1", "Question 2",
				"Question 3", "Question 4", "Question 5", "Question 6",
				"Question 7", "Question 8", "Question 9", "Question 10" };
		String[] name = new String[] { "Answer to Question 1",
				"Answer to Question 2", "Answer to Question 3",
				"Answer to Question 4", "Answer to Question 5",
				"Answer to Question 6", "Answer to Question 7",
				"Answer to Question 8", "Answer to Question 9",
				"Answer to Question 10" };

		final FaqsAdapter adap = new FaqsAdapter(getActivity(), pos, name);
		lv.setAdapter(adap);

		return v;
	}
}