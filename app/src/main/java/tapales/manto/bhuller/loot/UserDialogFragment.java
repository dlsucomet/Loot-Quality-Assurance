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
    TextInputLayout dialogOld, dialogOne;
    EditText etOldUsername, etUsername;
    Button btnSubmit, btnCancel;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_user, null);
        dialogOld = (TextInputLayout) v.findViewById(R.id.input_old_layout_title);
        dialogOne = (TextInputLayout) v.findViewById(R.id.input_layout_title);
        etOldUsername = (EditText) v.findViewById(R.id.dialog_old_username);
        etUsername = (EditText) v.findViewById(R.id.dialog_username);
        btnSubmit = (Button) v.findViewById(R.id.btn_SubmitU);
        btnCancel = (Button) v.findViewById(R.id.btn_CancelU);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Change Username")
                .setIcon(R.drawable.change_user);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitForm())
                    dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialogBuilder.create();
    }

    public boolean submitForm() {
        if (!validateUsername()) {
            return false;
        } else {
            ((SettingsActivity) getActivity()).onYesSelectedU(etUsername.getText().toString());
            DialogFragment df = new SettingsDialogFragment();
            df.show(getActivity().getFragmentManager(), null);
            Bundle bundle = new Bundle();
            bundle.putString("title", "Username");
            df.setArguments(bundle);
            return true;
        }
    }

    private boolean validateUsername() {
        if (etOldUsername.getText().toString().trim().isEmpty()) {
            dialogOld
                    .setError("Enter a Username");
            return false;

        }
        else {
            String u = ((SettingsActivity) getActivity()).getOldUsername();
            if(!(etOldUsername.getText().toString().equalsIgnoreCase(u)))
            {
                dialogOld
                        .setError("Incorrect Username");
                return false;
            }
            else dialogOld.setError("");
        }

        if (etUsername.getText().toString().trim().isEmpty()) {
            dialogOne.setError("Enter Username");
            return false;
        }
        return true;
    }
}