package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserDialogFragment extends DialogFragment {
    View v;
    TextInputLayout dialogOne, dialogTwo;
    EditText dialogCodeOne, dialogCodeTwo;
    Button btnSubmit, btnCancel;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_user, null);
        dialogCodeOne = (EditText) v.findViewById(R.id.dialog_username);
        btnSubmit = (Button) v.findViewById(R.id.btn_user_submit);
        btnCancel = (Button) v.findViewById(R.id.btn_user_cancel);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Change Username")
                .setIcon(R.drawable.change_user);
                /*
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Doesn't have error handling yet
                        EditText dialogName = (EditText) v.findViewById(R.id.dialog_username);
                        Toast.makeText(getActivity().getApplicationContext(), "Username changed to " + dialogName.getText().toString(), Toast.LENGTH_LONG).show();
                        //TODO
                        ((SettingsActivity)getActivity()).onYesSelectedU(dialogName.getText().toString());

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
                */
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Submit Username", Toast.LENGTH_LONG).show();
                //if(submitForm())
                    dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getApplicationContext(),"testCancel", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });

        Dialog d = dialogBuilder.create();
        return d;
    }
}