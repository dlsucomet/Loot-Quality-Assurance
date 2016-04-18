package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelUpDialogFragment extends DialogFragment {
    View v;
    TextView levelName;
    Button btnOk;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_level_up, null);
        levelName = (TextView) v.findViewById(R.id.dialog_level_title);
        levelName.setText(" Level Up! You are now level " + getArguments().getString("level") + ".");
        btnOk = (Button) v.findViewById(R.id.btn_Ok);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setView(v);
        Dialog d = dialogBuilder.create();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return d;
    }
}