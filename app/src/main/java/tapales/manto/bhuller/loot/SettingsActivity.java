package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CardView changeUser, changePasscode, deleteAllData;
    private DatabaseOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dbHelper = new DatabaseOpenHelper(getBaseContext());
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        changeUser = (CardView) findViewById(R.id.settings_user_card);
        changeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment df = new UserDialogFragment();
                df.show(getFragmentManager(), "");
            }
        });
        changePasscode = (CardView) findViewById(R.id.settings_passcode_card);
        changePasscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment df = new PasscodeDialogFragment();
                df.show(getFragmentManager(), "");
            }
        });
        deleteAllData = (CardView) findViewById(R.id.settings_delete_card);
        deleteAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("Remove All Data")
                        .setIcon(R.drawable.delete_all)
                        .setMessage("Are you sure you want to remove all data?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.deleteAllData();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    public void onYesSelectedU(String username){
        dbHelper.updateUsername(username);
    }

    public void onYesSelectedP(int passcode){
        dbHelper.updatePasscode(passcode);
    }

    public String getOldPasscode() {
        int passcode = dbHelper.getUser().getPincode();
        String p = String.valueOf(passcode);
        return p;
    }
}
