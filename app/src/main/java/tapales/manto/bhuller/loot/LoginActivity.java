package tapales.manto.bhuller.loot;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity{
    private EditText username, passcode;
    private TextInputLayout inputLayoutUser, inputLayoutPasscode;
    private Button submitButton;
    private DatabaseOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DatabaseOpenHelper(getBaseContext());
        username = (EditText) findViewById(R.id.input_username);
        username.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
        passcode = (EditText) findViewById(R.id.input_passcode);
        passcode.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
        inputLayoutUser = (TextInputLayout) findViewById(R.id.input_layout_username);
        inputLayoutPasscode = (TextInputLayout) findViewById(R.id.input_layout_passcode);
        submitButton = (Button) findViewById(R.id.button_sign_up);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }
    private void submitForm() {
        if (!validateUsername()) {
            return;
        }
        if (!validatePasscode()) {
            return;
        }
        if(validatePasscode() &&  validateUsername())
        {
            //Temp just for Navigation
            dbHelper.insertUser(new User(username.getText().toString(),Integer.parseInt(passcode.getText().toString())));
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
            //TO DO
        }

    }
    private boolean validateUsername() {
        if (username.getText().toString().trim().isEmpty()) {
            inputLayoutUser.setError("Enter a Username");
            return false;
        } else {
            inputLayoutUser.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validatePasscode(){
        if (passcode.getText().toString().trim().isEmpty()){
            inputLayoutPasscode.setError("Enter a Passcode");
            return false;
        }
        else if (passcode.getText().length() != 6){
            inputLayoutPasscode.setError("Passcode Must be 6 Digits Only");
            return false;
        }
        return true;
    }
}
