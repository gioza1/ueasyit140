package ueasy.it140.fragments;

import ueasy.it140.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View android = inflater.inflate(R.layout.fragment_about, container,
				false);
		// ((TextView)android.findViewById(R.id.textView)).setText("Android");
		// TextView desc = (TextView) android.findViewById(R.id.appDescription);
		// desc.setMovementMethod(new ScrollingMovementMethod());
		return android;
	}

	
}
