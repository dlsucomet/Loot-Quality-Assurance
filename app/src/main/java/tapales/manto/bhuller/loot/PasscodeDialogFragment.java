<<<<<<< HEAD
package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasscodeDialogFragment extends DialogFragment {
    View v;
    TextInputLayout dialogOld,dialogOne, dialogTwo;
    EditText dialogCodeOld, dialogCodeOne, dialogCodeTwo;
    Button btnSubmit, btnCancel;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_passcode, null);
        dialogCodeOld = (EditText) v.findViewById(R.id.dialog_old_passcode);
        dialogCodeOne = (EditText) v.findViewById(R.id.dialog_passcode_one);
        dialogCodeTwo = (EditText) v.findViewById(R.id.dialog_passcode_two);
        dialogOld = (TextInputLayout) v.findViewById(R.id.enter_layout_old_passcode);
        dialogOne = (TextInputLayout) v.findViewById(R.id.enter_layout_passcode_one);
        dialogTwo = (TextInputLayout) v.findViewById(R.id.enter_layout_passcode_two);
        btnSubmit = (Button) v.findViewById(R.id.btn_Submit);
        btnCancel = (Button) v.findViewById(R.id.btn_Cancel);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Change Passcode")
                .setIcon(R.drawable.change_passcode);
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(submitForm())
                    dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dismiss();
            }
        });
        Dialog d = dialogBuilder.create();
        return d;
    }
    public boolean submitForm() {
        if (!validatePasscode()){
            return false;
        }
        else{
            ((SettingsActivity)getActivity()).onYesSelectedP(Integer.parseInt(dialogCodeTwo.getText().toString()));
            return true;
        }
    }
    private boolean validatePasscode(){
        boolean noError = true;
        if (dialogCodeOld.getText().toString().trim().isEmpty()){
            dialogOld.setError("Enter Old Passcode");
            noError = false;
        }
        else if(dialogCodeOld.getText().length() != 6){
            dialogOld.setError("Passcode Must be 6 Digits Only");
            noError = false;
        }
        else if(!(dialogCodeOld.getText().toString().trim().isEmpty())){
            String p =   ((SettingsActivity)getActivity()).getOldPasscode();
            String oldP = dialogCodeOld.getText().toString();
            if(oldP.equalsIgnoreCase(p)== false){
                dialogOld.setError("Enter Old Passcode");
                noError = false;
            }
            else dialogOld.setError("");
        }
        if(dialogCodeOne.getText().toString().trim().isEmpty()){
            dialogOne.setError("Enter Passcode");
            noError = false;
        }
        else if(dialogCodeOne.getText().length() != 6){
            dialogOne.setError("Passcode Must be 6 Digits Only");
            noError = false;
        }
        else dialogOne.setError("");
        if(dialogCodeTwo.getText().toString().trim().isEmpty()){
            dialogTwo.setError("Enter Passcode");
            noError = false;
        }
        else if(dialogCodeTwo.getText().length() != 6){
            dialogTwo.setError("Passcode Must be 6 Digits Only");
            noError = false;
        }
        else if(!(dialogCodeOne.getText().toString().equalsIgnoreCase(dialogCodeTwo.getText().toString()))){
            dialogOne.setError("Passcodes Do Not Match");
            dialogTwo.setError("Passcodes Do Not Match");
            noError = false;
        }
        else dialogTwo.setError("");
        return noError;
    }
    public void refreshErrors(){
        dialogOld.setError("");
        dialogOne.setError("");
        dialogTwo.setError("");
    }
}