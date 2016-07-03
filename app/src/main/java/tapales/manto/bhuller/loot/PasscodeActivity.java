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
    //private EditText passcode;
    private EditText etName;
    private TextInputLayout inputLayoutPasscode;
    private Button submitButton;
    private DatabaseOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        dbHelper = new DatabaseOpenHelper(getBaseContext());
        tvwelcome = (TextView) findViewById(R.id.welcome_user);
        //noPasscode = (TextView) findViewById(R.id.no_passcode_text);
        if(dbHelper.getUser() != null){
           /*Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);*/
            startMainActivity();
            finish();
        }
        /*else{
            tvwelcome.setText("Welcome " + dbHelper.getUser().getName() + ".");
            noPasscode.setText("");
        }*/
        //passcode = (EditText) findViewById(R.id.enter_passcode);
//        passcode.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
        etName = (EditText) findViewById(R.id.enter_name);
        etName.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
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
        if(!validateName()){
            return;
        }
        else{
            /*Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);*/
            dbHelper.insertUser(new User(etName.getText().toString(),1,0));
            startMainActivity();
            finish();
        }
    }

    private boolean validateName() {
        if(etName.getText().toString().trim().isEmpty()){
            inputLayoutPasscode.setError("Enter a Name");
            return false;
        }
        else if(etName.getText().length() <= 1){
            inputLayoutPasscode.setError("Please enter a longer name.");
            return false;
        }
        return true;
    }

    private void startMainActivity() {
        Intent i = new Intent(PasscodeActivity.this, MainActivity.class);
        // set the new task and clear flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    /*private boolean validatePasscode(){
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
    }*/
}
