package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasscodeDialogFragment extends DialogFragment {
    View v;
    TextInputLayout dialogOne, dialogTwo;
    EditText dialogCodeOne, dialogCodeTwo;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_passcode, null);
        dialogCodeOne = (EditText) v.findViewById(R.id.dialog_passcode_one);
        dialogCodeTwo = (EditText) v.findViewById(R.id.dialog_passcode_two);
        dialogOne = (TextInputLayout) v.findViewById(R.id.enter_layout_passcode_one);
        dialogTwo = (TextInputLayout) v.findViewById(R.id.enter_layout_passcode_two);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Change Passcode")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Bug : Doesn't want to stay open after button press
                        submitForm();
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
    public void submitForm() {
        if (!validatePasscode()) {
            return;
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(), dialogCodeOne.getText().toString() + " " + dialogCodeTwo.getText().toString(), Toast.LENGTH_LONG).show();
            //TODO
        }

    }
    private boolean validatePasscode(){
        if (dialogCodeOne.getText().toString() != dialogCodeTwo.getText().toString() ){
            dialogOne.setError("Passcodes Do Not Match");
            dialogTwo.setError("Passcodes Do Not Match");
            return false;
        }
        if (dialogCodeOne.getText().toString().trim().isEmpty()){
            dialogOne.setError("Enter a Passcode");
            return false;
        }
        if (dialogCodeTwo.getText().toString().trim().isEmpty()){
            dialogTwo.setError("Enter a Passcode");
            return false;
        }
        if(dialogCodeOne.getText().length() != 6){
            dialogOne.setError("Passcode Must be 6 Digits Only");
            return false;
        }
        if(dialogCodeTwo.getText().length() != 6){
            dialogTwo.setError("Passcode Must be 6 Digits Only");
            return false;
        }
        return true;
    }
}