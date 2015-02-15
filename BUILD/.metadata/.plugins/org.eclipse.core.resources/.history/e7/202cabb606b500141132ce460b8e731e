package ueasy.it140.modals;
import ueasy.it140.activities.UeasySplash;
import ueasy.it140.activities.Update;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;




public class UpdateFail extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Want to update?").setTitle("Update");
		builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getActivity(), Update.class);
				startActivity(in);
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
			//	Toast.makeText(getActivity(), "Pressed Cancel", Toast.LENGTH_SHORT).show();
				Intent objIntent = new Intent(getActivity(), UeasySplash.class);
				startActivity(objIntent);
			}
		});
		
		
		
		
		
		Dialog dialog  = builder.create();
		
		return dialog;
	}
	
	
	
    

}
