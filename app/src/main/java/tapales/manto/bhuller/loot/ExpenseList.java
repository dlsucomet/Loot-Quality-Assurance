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

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class ExpenseList extends Fragment{
    RecyclerView rvExpenses;
    ExpenseCursorAdapter expenseAdapter;
    ExpenseCursorAdapter swapAdapter;
    DatabaseOpenHelper dbHelper;
    TextView monthText, emptyView;
    private ArrayList<String> monthList;
    private String[] CurrentMandY;
    ImageView allButton, foodButton, leisureButton, transportButton, billButton, debtButton, othersButton, backMonth, forwardMonth;
    private int mYear, mMonth;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.expense_list, container, false);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        monthList = new ArrayList<String>();
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            String month = months[i];
            monthList .add(months[i]);
        }
        monthText = (TextView) v.findViewById(R.id.expense_month_text);
        String date = months[mMonth] + " " + mYear;
        monthText.setText(date);
        CurrentMandY = date.split(" ");
        rvExpenses = (RecyclerView) v.findViewById(R.id.recycler_expenses);
		emptyView = (TextView) v.findViewById(R.id.empty_expense_view);
        dbHelper = new DatabaseOpenHelper(v.getContext());
        expenseAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByMonth(CurrentMandY[0],CurrentMandY[1]));
        rvExpenses.setAdapter(expenseAdapter);
        rvExpenses.setLayoutManager(new LinearLayoutManager(v.getContext()));
		if(expenseAdapter.getItemCount() > 0){
            rvExpenses.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        else{
            rvExpenses.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        backMonth = (ImageView) v.findViewById(R.id.expense_left_month);
        backMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                String backMandY = prevMonth(MandY[0], Integer.parseInt(MandY[1]));
                String[] BackMandY = backMandY.split(" ");
                monthText.setText(backMandY);
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByMonth(BackMandY[0],BackMandY[1]));
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByMonth(BackMandY[0],BackMandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
        forwardMonth = (ImageView) v.findViewById(R.id.expense_right_month);
        forwardMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                String nextMandY = nextMonth(MandY[0], Integer.parseInt(MandY[1]));
                String[] NextMandY = nextMandY.split(" ");
                monthText.setText(nextMandY);
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByMonth(NextMandY[0], NextMandY[1]));
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByMonth(NextMandY[0], NextMandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
        allButton = (ImageView) v.findViewById(R.id.category_all);
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpenses());
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByMonth(MandY[0],MandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                SnackbarManager.show(
                        Snackbar.with(getActivity())
                                .text("All Categories")
                                .duration(600));
            }
        });
        foodButton = (ImageView) v.findViewById(R.id.category_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Food"));
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByCategoryandMonth("Food",MandY[0],MandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                SnackbarManager.show(
                        Snackbar.with(getActivity())
                                .text("Category - Food")
                                .duration(600));
            }
        });
        leisureButton = (ImageView) v.findViewById(R.id.category_leisure);
        leisureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Leisure"));
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByCategoryandMonth("Leisure",MandY[0],MandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                SnackbarManager.show(
                        Snackbar.with(getActivity())
                                .text("Category - Leisure")
                                .duration(600));
            }
        });
        transportButton = (ImageView) v.findViewById(R.id.category_transportation);
        transportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Transportation"));
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByCategoryandMonth("Transportation", MandY[0], MandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                SnackbarManager.show(
                        Snackbar.with(getActivity())
                                .text("Category - Transportation")
                                .duration(600));
            }
        });
        billButton = (ImageView) v.findViewById(R.id.category_bills);
        billButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Bills"));
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByCategoryandMonth("Bills",MandY[0],MandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                SnackbarManager.show(
                        Snackbar.with(getActivity())
                                .text("Category - Bills")
                                .duration(600));
            }
        });
        debtButton = (ImageView) v.findViewById(R.id.category_debt);
        debtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Debt"));
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByCategoryandMonth("Debt",MandY[0],MandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                SnackbarManager.show(
                        Snackbar.with(getActivity())
                                .text("Category - Debt")
                                .duration(600));
            }
        });
        othersButton = (ImageView) v.findViewById(R.id.category_others);
        othersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Others"));
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                expenseAdapter.swapCursor(dbHelper.getAllExpensesByCategoryandMonth("Others",MandY[0],MandY[1]));
                if(expenseAdapter.getItemCount() > 0){
                    rvExpenses.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
                else{
                    rvExpenses.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
                SnackbarManager.show(
                        Snackbar.with(getActivity())
                                .text("Category - Others")
                                .duration(600));
            }
        });
        return v;
    }
    public void onResume(){
        super.onResume();
        String date = monthText.getText().toString();
        String[] MandY = date.split(" ");
        Cursor cursor = dbHelper.getAllExpensesByMonth(MandY[0],MandY[1]);
        expenseAdapter.swapCursor(cursor);
        if(expenseAdapter.getItemCount() > 0){
            rvExpenses.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        else{
            rvExpenses.setVisibility(View.GONE);
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