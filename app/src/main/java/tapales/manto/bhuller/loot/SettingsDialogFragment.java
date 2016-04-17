package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by LUCKY on 4/17/2016.
 */
public class SettingsDialogFragment extends DialogFragment {
    View v;
    TextView title;
    Button btnOk;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_success_setting, null);
        title = (TextView) v.findViewById(R.id.dialog_setting_title);
        title.setText(getArguments().getString("title"));
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
