package tapales.manto.bhuller.loot;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class IncomeCursorAdapter extends CursorRecyclerViewAdapter2<IncomeCursorAdapter.ExpenseViewHolder> {
    private Context context;
    public IncomeCursorAdapter(Context context, Cursor cursor){
        super(context, cursor);
        this.context = context;
    }
    public void onBindViewHolder(ExpenseViewHolder viewHolder, Cursor cursor){
        String title = cursor.getString(cursor.getColumnIndex(Income.COL_NAME));
        String price = cursor.getString(cursor.getColumnIndex(Income.COL_INCOME_AMOUNT));
        String date = cursor.getString(cursor.getColumnIndex(Income.COL_TIME_INTERVAL));
        Double moneyForm = Double.parseDouble(price);
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        viewHolder.incomeTitle.setText(title);
        viewHolder.incomeValue.setText(formatter.format(moneyForm));
        viewHolder.incomeDate.setText(date);
        int incomeID = cursor.getInt(cursor.getColumnIndex(Income.COL_ID));
        viewHolder.container.setTag(incomeID);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewEditIncomeIntent = new Intent(v.getContext(), EditIncomeActivity.class);
                viewEditIncomeIntent.putExtra(Income.COL_ID, Integer.parseInt(v.getTag().toString()));
                v.getContext().startActivity(viewEditIncomeIntent);
            }
        });
        viewHolder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });
    }
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_item, parent, false);
        return new ExpenseViewHolder(v);
    }
    public class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView incomeTitle, incomeValue, incomeDate;
        CardView container;
        public ExpenseViewHolder(View itemView) {
            super(itemView);
            incomeTitle = (TextView) itemView.findViewById(R.id.income_title);
            incomeValue = (TextView) itemView.findViewById(R.id.income_price);
            incomeDate = (TextView) itemView.findViewById(R.id.income_date);
            container = (CardView) itemView.findViewById(R.id.income_cv);
        }
    }
}

