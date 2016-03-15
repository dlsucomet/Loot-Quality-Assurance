package tapales.manto.bhuller.loot;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ExpenseViewHolder>{
    private List<Income> income;
    public IncomeAdapter(List<Income> income){
        this.income = income;
    }
    public static class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView incomeTitle, incomeValue, incomeDate;
        CardView container;
        public ExpenseViewHolder(View itemView){
            super(itemView);
            incomeTitle = (TextView) itemView.findViewById(R.id.income_title);
            incomeValue = (TextView) itemView.findViewById(R.id.income_price);
            incomeDate = (TextView) itemView.findViewById(R.id.income_date);
            container = (CardView) itemView.findViewById(R.id.income_cv);
        }
    }
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.income_item, parent, false);
        ExpenseViewHolder viewHolder = new ExpenseViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(ExpenseViewHolder holder, int position){
        holder.incomeTitle.setText(income.get(position).getIncomeName());
        holder.incomeValue.setText(String.valueOf(income.get(position).getIncomeAmount()));
        holder.incomeDate.setText(income.get(position).getTimeInterval());
    }
    public int getItemCount(){
        return income.size();
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
