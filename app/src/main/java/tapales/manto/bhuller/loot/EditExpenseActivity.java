package tapales.manto.bhuller.loot;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class EditExpenseActivity extends AppCompatActivity{
    private Expense currentExpense;
    private Toolbar toolbar;
    private DatabaseOpenHelper dbHelper;
    private int mYear, mMonth, mDay;
    private TextView categoryItem, dateText;
    private Button dateButton, submitButton, cancelButton;
    private EditText editTitle, editValue;
    private TextInputLayout editLayoutTitle, editLayoutValue;
    private ImageView foodButton, leisureButton, transportButton, billButton, debtButton, othersButton;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Edit Expense");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        dbHelper = new DatabaseOpenHelper(getBaseContext());
        currentExpense = dbHelper.getExpense(getIntent().getExtras().getInt(Expense.COL_ID));
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        categoryItem = (TextView) findViewById(R.id.edit_category);
        categoryItem.setText(currentExpense.getCategory());
        dateText = (TextView) findViewById(R.id.edit_date);
        dateText.setText("Date - " + currentExpense.getDate());
        editTitle = (EditText) findViewById(R.id.edit_expense_title);
        editTitle.setText(currentExpense.getExpName());
        editValue = (EditText) findViewById(R.id.edit_expense_value);
        editValue.setText(String.valueOf(currentExpense.getSpentAmount()));
        editLayoutTitle = (TextInputLayout) findViewById(R.id.edit_layout_title);
        editLayoutValue = (TextInputLayout) findViewById(R.id.edit_layout_value);
        submitButton = (Button) findViewById(R.id.edit_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
        cancelButton = (Button) findViewById(R.id.edit_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        dateButton = (Button) findViewById(R.id.edit_date_button);
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog dpd = new DatePickerDialog(EditExpenseActivity.this,
                        new DatePickerDialog.OnDateSetListener(){
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                                dateText.setText("Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year);
                                Toast.makeText(getBaseContext(), "Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year, Toast.LENGTH_LONG).show();
                            }}
                        , mYear, mMonth, mDay);
                dpd.show();
            }
        });
        foodButton = (ImageView) findViewById(R.id.edit_food);
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
        leisureButton = (ImageView) findViewById(R.id.edit_leisure);
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
        transportButton = (ImageView) findViewById(R.id.edit_transportation);
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
        billButton = (ImageView) findViewById(R.id.edit_bills);
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
        debtButton = (ImageView) findViewById(R.id.edit_debt);
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
        othersButton = (ImageView) findViewById(R.id.edit_others);
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
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                Toast.makeText(getApplicationContext(), "Expense Deleted", Toast.LENGTH_LONG).show();
                dbHelper.deleteExpense(currentExpense.getId());
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */
    private void submitForm() {
        if (!validateTitle()) {
            return;
        }
        if (!validatePrice()) {
            return;
        }
        if (!validateCategory()) {
            return;
        }
        currentExpense.setExpName(editTitle.getText().toString());
        currentExpense.setSpentAmount(Float.valueOf(editValue.getText().toString()));
        currentExpense.setCategory(categoryItem.getText().toString());
        currentExpense.setDate(dateText.getText().toString().replace("Date - ", ""));
        currentExpense.setPaymentType(1);
        dbHelper.updateExpense(currentExpense);
        setResult(RESULT_OK);
        finish();
    }
    private boolean validateTitle() {
        if (editTitle.getText().toString().trim().isEmpty()) {
            editLayoutTitle.setError("Enter a Title");
            return false;
        } else {
            editLayoutTitle.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validatePrice(){
        if (editValue.getText().toString().trim().isEmpty()){
            editLayoutValue.setError("Enter a Value");
            return false;
        }
        return true;
    }
    private boolean validateCategory(){
        if (categoryItem.getText() == "Category - None"){
            categoryItem.setText(Html.fromHtml("<font color=\"#F44336\">Category - None</font>"));
            Toast.makeText(getBaseContext(), "Please Select a Category", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
