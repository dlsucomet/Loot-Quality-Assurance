package tapales.manto.bhuller.loot;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class AddExpenseFragment extends Fragment {
    private DatabaseOpenHelper dbHelper;
    private int mYear, mMonth, mDay;
    private TextView categoryItem, dateText;
    private Button dateButton, submitButton, cancelButton;
    private EditText inputTitle, inputValue;
    private TextInputLayout inputLayoutTitle, inputLayoutValue;
    private ImageView foodButton, leisureButton, transportButton, billButton, debtButton, othersButton;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_expense, container, false);
        dbHelper = new DatabaseOpenHelper(getActivity().getApplicationContext());
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        categoryItem = (TextView) v.findViewById(R.id.add_category);
        categoryItem.setText("Others");
        dateText = (TextView) v.findViewById(R.id.add_date);
        dateText.setText("Date - " + months[mMonth] + " " + mDay + ", " + mYear);
        inputTitle = (EditText) v.findViewById(R.id.input_expense_title);
        inputValue = (EditText) v.findViewById(R.id.input_expense_value);
        inputLayoutTitle = (TextInputLayout) v.findViewById(R.id.input_layout_title);
        inputLayoutValue = (TextInputLayout) v.findViewById(R.id.input_layout_value);
        submitButton = (Button) v.findViewById(R.id.add_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
        cancelButton = (Button) v.findViewById(R.id.add_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getActivity().finish();
            }
        });
        dateButton = (Button) v.findViewById(R.id.add_date_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateText.setText("Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year);
                            }
                        }
                        , mYear, mMonth, mDay);
                dpd.show();
            }
        });
        foodButton = (ImageView) v.findViewById(R.id.add_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Food");
                Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_text);
                foodButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        leisureButton = (ImageView) v.findViewById(R.id.add_leisure);
        leisureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Leisure");
                Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_text);
                leisureButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        transportButton = (ImageView) v.findViewById(R.id.add_transportation);
        transportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Transportation");
                Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_text);
                transportButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        billButton = (ImageView) v.findViewById(R.id.add_bills);
        billButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Bills");
                Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_text);
                billButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        debtButton = (ImageView) v.findViewById(R.id.add_debt);
        debtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Debt");
                Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_text);
                debtButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        othersButton = (ImageView) v.findViewById(R.id.add_others);
        othersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Others");
                Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_text);
                othersButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        return v;
    }
    private void submitForm(){
        if (!validateTitle()) {
            return;
        }
        if (!validatePrice()) {
            return;
        }
        if (!validateCategory()) {
            return;
        }
        Expense expense = new Expense();
        expense.setExpName(inputTitle.getText().toString());
        expense.setSpentAmount(Float.valueOf(inputValue.getText().toString()));
        expense.setCategory(categoryItem.getText().toString());
        expense.setDate(dateText.getText().toString().replace("Date - ", ""));
        expense.setPaymentType(1);
        dbHelper.insertExpense(expense);
        getActivity().setResult(Activity.RESULT_OK, new Intent(getActivity().getApplicationContext(), MainActivity.class));
        getActivity().finish();
        Toast.makeText(getActivity().getApplicationContext(), "Expense Successfully Added", Toast.LENGTH_SHORT).show();
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
        else if (Integer.parseInt(inputValue.getText().toString().trim()) <= 0) {
            inputLayoutValue.setError("Enter a Value Greater Than 0");
            return false;
        }
        return true;
    }
    private boolean validateCategory(){
        if (categoryItem.getText() == "Category - None"){
            categoryItem.setText(Html.fromHtml("<font color=\"#F44336\">Category - None</font>"));
            Toast.makeText(getActivity().getApplicationContext(), "Please Select a Category", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

