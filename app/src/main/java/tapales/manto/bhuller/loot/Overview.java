package tapales.manto.bhuller.loot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Overview extends Fragment{
    private TextView monthText, totalSavings, monthlySavings, monthlyIncome, monthlyExpenses;
    private ImageView backMonth, forwardMonth;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.overview, container, false);
        monthText = (TextView) v.findViewById(R.id.overview_month_text);
        totalSavings = (TextView) v.findViewById(R.id.overview_total_price);
        monthlySavings = (TextView) v.findViewById(R.id.overview_savings_price);
        monthlyIncome = (TextView) v.findViewById(R.id.overview_income_price);
        monthlyExpenses = (TextView) v.findViewById(R.id.overview_expense_price);
        backMonth = (ImageView) v.findViewById(R.id.overview_left_month);
        backMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        forwardMonth = (ImageView) v.findViewById(R.id.overview_right_month);
        forwardMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        return v;
    }
}
