package ueasy.it140.modals;
import ueasy.it140.activities.Update;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;




public class UpdateLater extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Update Available. You can update later by going About Us?").setTitle("Update");
		builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Pressed Later", Toast.LENGTH_SHORT).show();
			}
		});
		
		builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getActivity(), Update.class);
				startActivity(in);
			}
		});
		
		Dialog dialog  = builder.create();
		
		return dialog;
	}
	
	
	
    

}
