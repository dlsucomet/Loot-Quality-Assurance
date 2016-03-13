package tapales.manto.bhuller.loot;

import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasscodeActivity extends AppCompatActivity {
    private EditText passcode;
    private TextInputLayout inputLayoutPasscode;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
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
    private void submitForm() {
        if (!validatePasscode()) {
            return;
        }
        //TO DO
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
