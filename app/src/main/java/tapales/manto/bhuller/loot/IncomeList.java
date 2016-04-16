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
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class IncomeList extends Fragment{
    RecyclerView rvIncome;
    IncomeCursorAdapter incomeAdapter;
    IncomeCursorAdapter swapAdapter;
    DatabaseOpenHelper dbHelper;
    TextView monthText, emptyView;
    private ArrayList<String> monthList;
    private String[] CurrentMandY;
    ImageView backMonth, forwardMonth;
    private int mYear, mMonth;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.income_list, container, false);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        monthList = new ArrayList<String>();
        String[] months = new DateFormatSymbols().getMonths();
        for(int i = 0; i < months.length; i++){
            String month = months[i];
            monthList .add(months[i]);
        }
        monthText = (TextView) v.findViewById(R.id.income_month_text);
        String date = months[mMonth] + " " + mYear;
        monthText.setText(date);
        CurrentMandY = date.split(" ");
        backMonth = (ImageView) v.findViewById(R.id.income_left_month);
        rvIncome = (RecyclerView) v.findViewById(R.id.recycler_income);
		emptyView = (TextView) v.findViewById(R.id.empty_income_view);
        dbHelper = new DatabaseOpenHelper(v.getContext());
        incomeAdapter = new IncomeCursorAdapter(v.getContext(), dbHelper.getAllIncomeByMonth(CurrentMandY[0],CurrentMandY[1]));
        rvIncome.setAdapter(incomeAdapter);
        rvIncome.setLayoutManager(new LinearLayoutManager(v.getContext()));
		if(incomeAdapter.getItemCount() > 0){
            rvIncome.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        else{
            rvIncome.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        backMonth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                String backMandY = prevMonth(MandY[0], Integer.parseInt(MandY[1]));
                String[] BackMandY = backMandY.split(" ");
                monthText.setText(backMandY);
                swapAdapter = new IncomeCursorAdapter(v.getContext(), dbHelper.getAllExpensesByMonth(BackMandY[0],BackMandY[1]));
                incomeAdapter.swapCursor(dbHelper.getAllIncomeByMonth(BackMandY[0], BackMandY[1]));
                if(incomeAdapter.getItemCount() > 0){
                    rvIncome.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvIncome.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
        forwardMonth = (ImageView) v.findViewById(R.id.income_right_month);
        forwardMonth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                String nextMandY = nextMonth(MandY[0], Integer.parseInt(MandY[1]));
                String[] NextMandY = nextMandY.split(" ");
                monthText.setText(nextMandY);
                swapAdapter = new IncomeCursorAdapter(v.getContext(), dbHelper.getAllExpensesByMonth(NextMandY[0], NextMandY[1]));
                incomeAdapter.swapCursor(dbHelper.getAllIncomeByMonth(NextMandY[0], NextMandY[1]));
                if(incomeAdapter.getItemCount() > 0){
                    rvIncome.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvIncome.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
        return v;
    }
    public void onResume(){
        super.onResume();
        String date = monthText.getText().toString();
        String[] MandY = date.split(" ");
        Cursor cursor = dbHelper.getAllIncomeByMonth(MandY[0],MandY[1]);
        incomeAdapter.swapCursor(cursor);
        if(incomeAdapter.getItemCount() > 0){
            rvIncome.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        else{
            rvIncome.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }
    public String prevMonth(String month, int year){
        String result="";
        int indexofCurrentMonth = 0;
        int indexofPrevMonth= 0;
        for(int i = 0; i < monthList.size(); i++){
            if(monthList.get(i).equalsIgnoreCase(month)){
                indexofCurrentMonth = i;
                indexofPrevMonth = i-1;
                break;
            }
        }
        if(indexofCurrentMonth == 0){
            year = year - 1;
            month = monthList.get(11);
        }
        else{
            month = monthList.get(indexofPrevMonth);
        }
        result = month + " " + year;
        return result;
    }
    public String nextMonth(String month, int year){
        String result="";
        int indexofCurrentMonth = 0;
        int indexofNextMonth= 0;
        for(int i = 0; i < monthList.size(); i++){
            if(monthList.get(i).equalsIgnoreCase(month)){
                indexofCurrentMonth = i;
                indexofNextMonth = i+1;
                break;
            }
        }
        if(indexofCurrentMonth == 11){
            year = year + 1;
            month = monthList.get(0);
        }
        else{
            month = monthList.get(indexofNextMonth);
        }
        result = month + " " + year;
        return result;
    }
}