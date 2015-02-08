package ueasy.it140.adapters;

import ueasy.it140.fragments.AboutFragment;
import ueasy.it140.fragments.FaqFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
		case 0:
			// Fragement for FaqsTab 
			return new FaqFragment();
		case 1:
			// Fragment for About Tab
			return new AboutFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2; // No of Tabs
	}
}