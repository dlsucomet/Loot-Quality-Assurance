package tapales.manto.bhuller.loot;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>{
    private List<Expense> expenses;
    public ExpenseAdapter(List<Expense> expenses){
        this.expenses = expenses;
    }
    public static class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView expenseTitle, expenseValue, expenseDate;
        ImageView expenseCategory;
        CardView container;
        public ExpenseViewHolder(View itemView){
            super(itemView);
            expenseTitle = (TextView) itemView.findViewById(R.id.expense_title);
            expenseValue = (TextView) itemView.findViewById(R.id.expense_price);
            expenseDate = (TextView) itemView.findViewById(R.id.expense_date);
            expenseCategory = (ImageView) itemView.findViewById(R.id.category_photo);
            container = (CardView) itemView.findViewById(R.id.cv);
        }
    }
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item, parent, false);
        ExpenseViewHolder viewHolder = new ExpenseViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(ExpenseViewHolder holder, int position){
        holder.expenseTitle.setText(expenses.get(position).getExpName());
        holder.expenseValue.setText(String.format("%,d", expenses.get(position).getSpentAmount()));
//        holder.expenseValue.setText(String.valueOf(expenses.get(position).getSpentAmount()));
        holder.expenseDate.setText(expenses.get(position).getDate());
        holder.expenseCategory.setImageResource(expenses.get(position).getCategoryInt());
    }
    public int getItemCount(){
        return expenses.size();
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
