package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserDialogFragment extends DialogFragment {
    View v;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_user, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Change Username")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Doesn't have error handling yet
                        EditText dialogName = (EditText) v.findViewById(R.id.dialog_username);
                        Toast.makeText(getActivity().getApplicationContext(), "Username changed to " + dialogName.getText().toString(), Toast.LENGTH_LONG).show();
                        //TODO
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return dialogBuilder.create();
    }
}