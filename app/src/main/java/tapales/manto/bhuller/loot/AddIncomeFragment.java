package tapales.manto.bhuller.loot;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class AddIncomeFragment extends Fragment{
    private DatabaseOpenHelper dbHelper;
    private int mYear, mMonth, mDay;
    private TextView dateText;
    private Button dateButton, submitButton, clearButton;
    private EditText inputTitle, inputValue;
    private TextInputLayout inputLayoutTitle, inputLayoutValue;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.add_income, container, false);
        dbHelper = new DatabaseOpenHelper(getActivity().getApplicationContext());
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        dateText = (TextView) v.findViewById(R.id.add_income_date);
        dateText.setText("Date - " + months[mMonth] + " " + mDay + ", " + mYear);
        inputTitle = (EditText) v.findViewById(R.id.input_income_title);
        inputValue = (EditText) v.findViewById(R.id.input_income_value);
        inputLayoutTitle = (TextInputLayout) v.findViewById(R.id.income_layout_title);
        inputLayoutValue = (TextInputLayout) v.findViewById(R.id.income_layout_value);
        submitButton = (Button) v.findViewById(R.id.add_income_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                submitForm();
            }
        });
        clearButton = (Button) v.findViewById(R.id.add_income_clear_button);
        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dateText.setText("Date - " + months[mMonth] + " " + mDay + ", " + mYear);
                inputTitle.setText("");
                inputValue.setText("");
                Toast.makeText(getActivity().getApplicationContext(), "Income Successfully Cleared", Toast.LENGTH_LONG).show();
            }
        });
        dateButton = (Button) v.findViewById(R.id.add_income_date_button);
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog dpd = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener(){
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                                dateText.setText("Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year);
                                Toast.makeText(getActivity().getApplicationContext(), "Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year, Toast.LENGTH_LONG).show();
                            }}
                        , mYear, mMonth, mDay);
                dpd.show();
            }
        });
        return v;
    }
    private void submitForm() {
        if (!validateTitle()){
            return;
        }
        if (!validatePrice()){
            return;
        }
        Income income = new Income();
        income.setIncomeName(inputTitle.getText().toString());
        income.setIncomeAmount(Float.valueOf(inputValue.getText().toString()));
        income.setTimeInterval(dateText.getText().toString().replace("Date - ", ""));
        dbHelper.insertIncome(income);
        getActivity().setResult(Activity.RESULT_OK, new Intent(getActivity().getApplicationContext(), MainActivity.class));
        getActivity().finish();
    }
    private boolean validateTitle() {
        if (inputTitle.getText().toString().trim().isEmpty()) {
            inputLayoutTitle.setError("Enter a Title");
            return false;
        } else {
            inputLayoutTitle.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validatePrice(){
        if (inputValue.getText().toString().trim().isEmpty()){
            inputLayoutValue.setError("Enter a Value");
            return false;
        }
        return true;
    }
}
