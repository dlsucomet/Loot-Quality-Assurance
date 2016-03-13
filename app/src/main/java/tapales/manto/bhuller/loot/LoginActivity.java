package tapales.manto.bhuller.loot;

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
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.input_username);
        username.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
        passcode = (EditText) findViewById(R.id.input_passcode);
        passcode.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorDefault), PorterDuff.Mode.SRC_ATOP);
        inputLayoutUser = (TextInputLayout) findViewById(R.id.input_layout_username);
        inputLayoutPasscode = (TextInputLayout) findViewById(R.id.input_layout_passcode);
        submitButton = (Button) findViewById(R.id.button_login);
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
        //TO DO
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
        return true;
    }

}
