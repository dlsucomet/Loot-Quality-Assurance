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
import android.widget.Toast;

public class ExpenseList extends Fragment{
    RecyclerView rvExpenses;
    //ExpenseCursorAdapter expenseAdapter;
    ExpenseCursorAdapter expenseAdapter;
    ExpenseCursorAdapter swapAdapter;
    DatabaseOpenHelper dbHelper;
    //These ImageView Buttons are to filter the categories
    ImageView allButton, foodButton, leisureButton, transportButton, billButton, debtButton, othersButton;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.expense_list, container, false);
        rvExpenses = (RecyclerView) v.findViewById(R.id.recycler_expenses);
        dbHelper = new DatabaseOpenHelper(v.getContext());
        //dbHelper.deleteAllExpenses();
        //dbHelper.insertDummyIncome();
        expenseAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpenses());
//        List<Expense> expenses= new ArrayList<>();
//        expenses.add(new Expense(1, "Starbucks Coffee", (float) 120, 1, "Food", "March 13, 2016"));
//        expenses.add(new Expense(2, "Uber", (float) 240, 1, "Transportation", "March 13, 2016"));
//        expenses.add(new Expense(3, "Wanderland Ticket", (float) 1500, 1, "Debt", "March 13, 2016"));
//        expenseAdapter = new ExpenseAdapter(expenses);
        rvExpenses.setAdapter(expenseAdapter);
        rvExpenses.setLayoutManager(new LinearLayoutManager(v.getContext()));
        allButton = (ImageView) v.findViewById(R.id.category_all);
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "All Categories", Toast.LENGTH_LONG).show();
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpenses());
                rvExpenses.swapAdapter(swapAdapter, true);
            }
        });
        foodButton = (ImageView) v.findViewById(R.id.category_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Category - Food", Toast.LENGTH_LONG).show();
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Food"));
                rvExpenses.swapAdapter(swapAdapter, true);
            }
        });
        leisureButton = (ImageView) v.findViewById(R.id.category_leisure);
        leisureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Leisure", Toast.LENGTH_LONG).show();
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Leisure"));
                rvExpenses.swapAdapter(swapAdapter, true);
            }
        });
        transportButton = (ImageView) v.findViewById(R.id.category_transportation);
        transportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Transportation", Toast.LENGTH_LONG).show();
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Transportation"));
                rvExpenses.swapAdapter(swapAdapter, true);
            }
        });
        billButton = (ImageView) v.findViewById(R.id.category_bills);
        billButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Bills", Toast.LENGTH_LONG).show();
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Bills"));
                rvExpenses.swapAdapter(swapAdapter, true);
            }
        });
        debtButton = (ImageView) v.findViewById(R.id.category_debt);
        debtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Debt", Toast.LENGTH_LONG).show();
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Debt"));
                rvExpenses.swapAdapter(swapAdapter, true);
            }
        });
        othersButton = (ImageView) v.findViewById(R.id.category_others);
        othersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(),"Category - Others", Toast.LENGTH_LONG).show();
                swapAdapter = new ExpenseCursorAdapter(v.getContext(), dbHelper.getAllExpensesByCategory("Others"));
                rvExpenses.swapAdapter(swapAdapter, true);
            }
        });
        return v;
    }

    public void onResume(){
        super.onResume();
        Cursor cursor = dbHelper.getAllExpenses();
        expenseAdapter.swapCursor(cursor);
    }
}