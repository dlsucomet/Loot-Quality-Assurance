package tapales.manto.bhuller.loot;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasscodeActivity extends AppCompatActivity{
    private TextView noPasscode, tvwelcome;
    private EditText passcode;
    private TextInputLayout inputLayoutPasscode;
    private Button submitButton;
    private DatabaseOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        dbHelper = new DatabaseOpenHelper(getBaseContext());
        tvwelcome = (TextView) findViewById(R.id.welcome_user_passcode);
        noPasscode = (TextView) findViewById(R.id.no_passcode_text);
        if(dbHelper.getUser() == null){
            tvwelcome.setText("Welcome to Loot.");
            noPasscode.setText((Html.fromHtml("No Passcode Yet? Click <u>Here</u> to Get One!")));
            noPasscode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else{
            tvwelcome.setText("Welcome " + dbHelper.getUser().getName() + ".");
            noPasscode.setText("");
        }
        passcode = (EditText) findViewById(R.id.enter_passcode);
        passcode.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
        inputLayoutPasscode = (TextInputLayout) findViewById(R.id.enter_layout_passcode);
        submitButton = (Button) findViewById(R.id.button_enter);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }
    private void submitForm(){
        if(!validatePasscode()){
            return;
        }
        else{
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private boolean validatePasscode(){
        if(passcode.getText().toString().trim().isEmpty()){
            inputLayoutPasscode.setError("Enter a Passcode");
            return false;
        }
        else if(passcode.getText().length() != 6){
            inputLayoutPasscode.setError("Passcode Must be 6 Digits Only");
            return false;
        }
        else{
            if(dbHelper.getUser() == null){
                inputLayoutPasscode.setError("No Registered User");
                return false;
            }
            else if(dbHelper.getUser().getPincode() != Integer.parseInt(passcode.getText().toString())){
                inputLayoutPasscode.setError("Incorrect Passcode");
                return false;
            }
        }
        return true;
    }
}
