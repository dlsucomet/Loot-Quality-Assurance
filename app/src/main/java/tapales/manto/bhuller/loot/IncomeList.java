package tapales.manto.bhuller.loot;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class IncomeList extends Fragment{
    RecyclerView rvIncome;
    IncomeCursorAdapter incomeAdapter;
    //IncomeAdapter incomeAdapter;
    DatabaseOpenHelper dbHelper;
    TextView monthText;
    ImageView backMonth, forwardMonth;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.income_list, container, false);
        monthText = (TextView) v.findViewById(R.id.income_month_text);
        backMonth = (ImageView) v.findViewById(R.id.income_left_month);
        backMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //Change monthText and filter items by month
                Toast.makeText(getActivity().getApplicationContext(), "Back by One Month", Toast.LENGTH_LONG).show();
            }
        });
        forwardMonth = (ImageView) v.findViewById(R.id.income_right_month);
        forwardMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //Change monthText and filter items by month
                Toast.makeText(getActivity().getApplicationContext(),"Forward by One Month", Toast.LENGTH_LONG).show();
            }
        });
        rvIncome = (RecyclerView) v.findViewById(R.id.recycler_income);
        dbHelper = new DatabaseOpenHelper(v.getContext());
        //dbHelper.insertDummyIncome();
        incomeAdapter = new IncomeCursorAdapter(v.getContext(), dbHelper.getAllIncome());
//        List<Income> income = new ArrayList<>();
//        income.add(new Income("Carpool Sponsorship", (float) 700, "March 13, 2016"));
//        income.add(new Income("Vaporizer Sale", (float) 3500, "March 13, 2016"));
        //incomeAdapter = new IncomeAdapter(income);
        rvIncome.setAdapter(incomeAdapter);
        rvIncome.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;
    }

    public void onResume(){
        super.onResume();
        Cursor cursor = dbHelper.getAllIncome();
        incomeAdapter.swapCursor(cursor);
    }
}