package tapales.manto.bhuller.loot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by HP on 02/07/2016.
 */
public class PopupEditActivity extends Activity{

    private Button editButton, deleteButton;
    private Income currentIncome;
    private DatabaseOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DatabaseOpenHelper(getBaseContext());
        currentIncome = dbHelper.getIncome(getIntent().getExtras().getInt(Income.COL_ID));

        setContentView(R.layout.popupactivity_edit);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.475), (int)(height*0.075));

        editButton = (Button) findViewById(R.id.button_edit);
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent viewEditIncomeIntent = new Intent(v.getContext(), EditIncomeActivity.class);
                viewEditIncomeIntent.putExtras(getIntent());
                v.getContext().startActivity(viewEditIncomeIntent);
                finish();
            }
        });

        deleteButton = (Button) findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Income Deleted", Toast.LENGTH_LONG).show();
                dbHelper.deleteIncome(currentIncome.getId());
                finish();
            }
        });
    }



}
