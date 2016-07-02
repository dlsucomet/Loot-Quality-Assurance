package tapales.manto.bhuller.loot;

/**
 * Created by HP on 02/07/2016.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PopupEditExpenseActivity extends Activity{

    private Button editButton, deleteButton;
    private Expense currentExpense;
    private DatabaseOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DatabaseOpenHelper(getBaseContext());
        currentExpense = dbHelper.getExpense(getIntent().getExtras().getInt(Expense.COL_ID));

        setContentView(R.layout.popupactivity_edit);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.433), (int)(height*0.1));

        editButton = (Button) findViewById(R.id.button_edit);
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent viewEditExpenseIntent = new Intent(v.getContext(), EditExpenseActivity.class);
                viewEditExpenseIntent.putExtras(getIntent());
                v.getContext().startActivity(viewEditExpenseIntent);
                finish();
            }
        });

        deleteButton = (Button) findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Expense Deleted", Toast.LENGTH_LONG).show();
                dbHelper.deleteExpense(currentExpense.getId());
                finish();
            }
        });
    }
}
