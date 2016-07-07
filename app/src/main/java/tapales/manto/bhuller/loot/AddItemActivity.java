package tapales.manto.bhuller.loot;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class AddItemActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DatabaseOpenHelper dbHelper;
    private int mYear, mMonth, mDay;
    private TextView categoryItem, dateText;
    private Button dateButton, submitButton, cancelButton;
    private EditText inputTitle, inputValue;
    private TextInputLayout inputLayoutTitle, inputLayoutValue;
    private RadioButton rbIncome, rbExpense;
    private ImageView foodButton, leisureButton, transportButton, billButton, debtButton, othersButton;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("New Item");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
        dbHelper = new DatabaseOpenHelper(getApplicationContext());
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        categoryItem = (TextView) findViewById(R.id.add_category);
        categoryItem.setText("Others");
        dateText = (TextView) findViewById(R.id.add_date);
        dateText.setText("Date - " + months[mMonth] + " " + mDay + ", " + mYear);
        inputTitle = (EditText) findViewById(R.id.input_expense_title);
        inputValue = (EditText) findViewById(R.id.input_expense_value);
        inputLayoutTitle = (TextInputLayout) findViewById(R.id.input_layout_title);
        inputLayoutValue = (TextInputLayout) findViewById(R.id.input_layout_value);
        submitButton = (Button) findViewById(R.id.add_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
        cancelButton = (Button) findViewById(R.id.add_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        dateButton = (Button) findViewById(R.id.add_date_button);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(getBaseContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateText.setText("Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year);
                            }
                        }
                        , mYear, mMonth, mDay);
                dpd.show();
            }
        });

        foodButton = (ImageView) findViewById(R.id.add_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Food");
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_text);
                foodButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        leisureButton = (ImageView) findViewById(R.id.add_leisure);
        leisureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Leisure");
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_text);
                leisureButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        transportButton = (ImageView) findViewById(R.id.add_transportation);
        transportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Transportation");
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_text);
                transportButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        billButton = (ImageView) findViewById(R.id.add_bills);
        billButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Bills");
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_text);
                billButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        debtButton = (ImageView) findViewById(R.id.add_debt);
        debtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Debt");
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_text);
                debtButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });
        othersButton = (ImageView) findViewById(R.id.add_others);
        othersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItem.setText("Others");
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeText = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_text);
                othersButton.startAnimation(animFadein);
                categoryItem.startAnimation(animFadeText);
            }
        });

        rbIncome = (RadioButton) findViewById(R.id.rbIncome);
        rbIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout) findViewById(R.id.categoryButtons)).setVisibility(View.GONE);
                ((LinearLayout) findViewById(R.id.categoryText)).setVisibility(View.GONE);
                loc = "income";
            }
        });
        rbExpense = (RadioButton) findViewById(R.id.rbExpense);
        rbExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout) findViewById(R.id.categoryButtons)).setVisibility(View.VISIBLE);
                ((LinearLayout) findViewById(R.id.categoryText)).setVisibility(View.VISIBLE);
                loc = "expense";
            }
        });

        loc = getIntent().getStringExtra("caller");
        if(isIncome()){
            rbIncome.setChecked(true);
        }
        else {
            rbExpense.setChecked(true);
            ((LinearLayout) findViewById(R.id.categoryButtons)).setVisibility(View.VISIBLE);
            ((LinearLayout) findViewById(R.id.categoryText)).setVisibility(View.VISIBLE);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_submit_item:
                submitForm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void submitForm(){
        if (!validateTitle()) {
            return;
        }
        if (!validatePrice()) {
            return;
        }
        if (!isIncome() && !validateCategory()) {
            return;
        }
        if (!isIncome()) {
            Expense expense = new Expense();
            expense.setExpName(inputTitle.getText().toString());
            expense.setSpentAmount(Float.valueOf(inputValue.getText().toString()));
            expense.setCategory(categoryItem.getText().toString());
            expense.setDate(dateText.getText().toString().replace("Date - ", ""));
            expense.setPaymentType(1);
            dbHelper.insertExpense(expense);
            setResult(Activity.RESULT_OK, new Intent(getApplicationContext(), MainActivity.class));
            finish();
            Toast.makeText(getApplicationContext(), "Expense Successfully Added", Toast.LENGTH_SHORT).show();
        }
        else {
            Income income = new Income();
            income.setIncomeName(inputTitle.getText().toString());
            income.setIncomeAmount(Float.valueOf(inputValue.getText().toString()));
            income.setTimeInterval(dateText.getText().toString().replace("Date - ", ""));
            dbHelper.insertIncome(income);
            setResult(Activity.RESULT_OK, new Intent(getApplicationContext(), MainActivity.class));
            finish();
            Toast.makeText(getApplicationContext(), "Income Successfully Added", Toast.LENGTH_SHORT).show();
        }
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
            Toast.makeText(getApplicationContext(), "Please Select a Category", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isIncome() {
        return loc.compareToIgnoreCase("income") == 0;
    }
}

