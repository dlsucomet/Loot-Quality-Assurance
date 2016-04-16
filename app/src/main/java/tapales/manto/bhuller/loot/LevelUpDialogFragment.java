package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class LevelUpDialogFragment extends DialogFragment{
    View v;
    TextView levelName;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_level_up, null);
        levelName = (TextView) v.findViewById(R.id.dialog_level_title);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setView(v);
        Dialog d = dialogBuilder.create();
        return d;
    }
}