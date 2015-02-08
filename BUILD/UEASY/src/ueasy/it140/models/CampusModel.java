package ueasy.it140.models;

import java.util.ArrayList;

import ueasy.it140.R;

public class CampusModel {
	public String campusName;
	public String campusAddress;
	public int campusImage;

	public static ArrayList<CampusModel> populateItems() {
		ArrayList<CampusModel> items = new ArrayList<CampusModel>();

		for (int x = 0; x < 4; x++) {
			CampusModel itemCampus = new CampusModel();

			switch (x) {
			case 0: {

				itemCampus.campusName = "Talamban Campus";
				itemCampus.campusImage = R.drawable.bunzel;
				itemCampus.campusAddress = "Talamban, Cebu City";
			}

				break;

			case 1: {

				itemCampus.campusName = "Downtown Campus";
				itemCampus.campusImage = R.drawable.main;
				itemCampus.campusAddress = "P. del Rosario St., Cebu City";
			}
				break;

			case 2: {

				itemCampus.campusName = "South Campus";
				itemCampus.campusImage = R.drawable.south;
				itemCampus.campusAddress = " J. Alcantara St., Cebu City, Cebu";
			}
				break;

			case 3: {

				itemCampus.campusName = "North Campus";
				itemCampus.campusImage = R.drawable.north;
				itemCampus.campusAddress = "Gen. Maxilom Ave., Cebu City";
			}
				break;

			}

			items.add(itemCampus);
		}

		return items;
	}
}
