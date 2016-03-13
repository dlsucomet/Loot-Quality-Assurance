package tapales.manto.bhuller.loot;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpenseCursorAdapter extends CursorRecyclerViewAdapter<ExpenseCursorAdapter.ExpenseViewHolder> {
    public ExpenseCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }
    public void onBindViewHolder(ExpenseViewHolder viewHolder, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(Expense.COL_EXPENSE_NAME));
        String price = cursor.getString(cursor.getColumnIndex(Expense.COL_SPENT_AMOUNT));
        String date = cursor.getString(cursor.getColumnIndex(Expense.COL_DATE));
        String category = cursor.getString(cursor.getColumnIndex(Expense.COL_CATEGORY));
        viewHolder.expenseTitle.setText(title);
        viewHolder.expenseValue.setText(price);
        viewHolder.expenseDate.setText(date);
        viewHolder.expenseCategory.setImageResource(getCategory(category));
        int expenseID = cursor.getInt(cursor.getColumnIndex(Expense.COL_ID));
        viewHolder.container.setTag(expenseID);
        viewHolder.container.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                Intent viewEditExpenseIntent = new Intent(v.getContext(), EditExpenseActivity.class);
                viewEditExpenseIntent.putExtra(Expense.COL_ID, Integer.parseInt(v.getTag().toString()));
                v.getContext().startActivity(viewEditExpenseIntent);
                return true;
            }
        });
    }
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        return new ExpenseViewHolder(v);
    }
    public class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView expenseTitle, expenseValue, expenseDate;
        ImageView expenseCategory;
        CardView container;
        public ExpenseViewHolder(View itemView) {
            super(itemView);
            expenseTitle = (TextView) itemView.findViewById(R.id.expense_title);
            expenseValue = (TextView) itemView.findViewById(R.id.expense_price);
            expenseDate = (TextView) itemView.findViewById(R.id.expense_date);
            expenseCategory = (ImageView) itemView.findViewById(R.id.category_photo);
            container = (CardView) itemView.findViewById(R.id.cv);
        }
    }
    public int getCategory(String category){
        if(category == "Food"){
            return R.drawable.food;
        }
        else if(category == "Leisure"){
            return R.drawable.leisure;
        }
        else if(category == "Transportation"){
            return R.drawable.transportation;
        }
        else if(category == "Bills"){
            return R.drawable.bills;
        }
        else if(category == "Debt"){
            return R.drawable.debt;
        }
        else if(category == "Others"){
            return R.drawable.others;
        }
        else return R.drawable.all;
    }
}

