package tapales.manto.bhuller.loot;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Overview extends Fragment{
    private TextView monthText, totalSavings, monthlySavings, monthlyIncome, monthlyExpenses;
    private ImageView backMonth, forwardMonth;
    private DatabaseOpenHelper dbHelper;
    private ArrayList<String> monthList;
    private String[] CurrentMandY;
    private PieChartView pieChart;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.overview, container, false);

        dbHelper = new DatabaseOpenHelper(v.getContext());

        pieChart = (PieChartView) v.findViewById(R.id.overview_pie_chart);




        monthList = new ArrayList<String>();
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            String month = months[i];
            monthList .add(months[i]);
        }

        monthText = (TextView) v.findViewById(R.id.overview_month_text);
        String date = monthText.getText().toString();
        CurrentMandY = date.split(" ");

        PieChartData data = new PieChartData(getData(CurrentMandY[0],CurrentMandY[1]));

        pieChart.setPieChartData(data);

        totalSavings = (TextView) v.findViewById(R.id.overview_total_price);
        monthlySavings = (TextView) v.findViewById(R.id.overview_savings_price);
        monthlyIncome = (TextView) v.findViewById(R.id.overview_income_price);
        monthlyExpenses = (TextView) v.findViewById(R.id.overview_expense_price);

        monthlyIncome.setText(getMonthlyIncome(CurrentMandY[0],CurrentMandY[1]));
        monthlyExpenses.setText(getMonthlyExpense(CurrentMandY[0], CurrentMandY[1]));
        monthlySavings.setText(getMonthlySaving(CurrentMandY[0], CurrentMandY[1]));
        totalSavings.setText(getTotalSaving());

        backMonth = (ImageView) v.findViewById(R.id.overview_left_month);
        backMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //Change monthText and filter items by month
                Toast.makeText(getActivity().getApplicationContext(), "Back by One Month", Toast.LENGTH_LONG).show();
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                String backMandY = prevMonth(MandY[0], Integer.parseInt(MandY[1]));
                String[] BackMandY = backMandY.split(" ");
                monthText.setText(backMandY);
                monthlyIncome.setText(getMonthlyIncome(BackMandY[0],BackMandY[1]));
                monthlyExpenses.setText(getMonthlyExpense(BackMandY[0], BackMandY[1]));
                monthlySavings.setText(getMonthlySaving(BackMandY[0], BackMandY[1]));
                totalSavings.setText(getTotalSaving());
                PieChartData data = new PieChartData(getData(BackMandY[0],BackMandY[1]));
                pieChart.setPieChartData(data);
            }
        });
        forwardMonth = (ImageView) v.findViewById(R.id.overview_right_month);
        forwardMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //Change monthText and filter items by month
                Toast.makeText(getActivity().getApplicationContext(),"Forward by One Month", Toast.LENGTH_LONG).show();
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                String nextMandY = nextMonth(MandY[0], Integer.parseInt(MandY[1]));
                String[] NextMandY = nextMandY.split(" ");
                monthText.setText(nextMandY);
                monthlyIncome.setText(getMonthlyIncome(NextMandY[0],NextMandY[1]));
                monthlyExpenses.setText(getMonthlyExpense(NextMandY[0], NextMandY[1]));
                monthlySavings.setText(getMonthlySaving(NextMandY[0], NextMandY[1]));
                totalSavings.setText(getTotalSaving());
                PieChartData data = new PieChartData(getData(NextMandY[0],NextMandY[1]));
                pieChart.setPieChartData(data);
            }
        });
        return v;
    }

    public String getMonthlyIncome(String month, String year)
    {
        String mI="";
        int sum=0;
        Cursor cursor = dbHelper.getAllIncomeByMonth(month,year);
        while(cursor.moveToNext()){
                String income = cursor.getString(cursor.getColumnIndex(Income.COL_INCOME_AMOUNT));
                int i = Integer.parseInt(income);
                sum += i;
        }
        cursor.close();
        mI = String.valueOf(sum);

        return mI;
    }


    public String getMonthlyExpense(String month, String year)
    {
        String mE="";
        int sum=0;
        Cursor cursor = dbHelper.getAllExpensesByMonth(month, year);
        while(cursor.moveToNext()){
            String expense = cursor.getString(cursor.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int i = Integer.parseInt(expense);
            sum += i;
        }
        cursor.close();
        mE = String.valueOf(sum);

        return mE;
    }

    public String getMonthlySaving(String month, String year)
    {
        String mS="";

        int saving = Integer.parseInt(getMonthlyIncome(month,year)) - Integer.parseInt(getMonthlyExpense(month,year));

        mS = String.valueOf(saving);

        return mS;
    }

    public String getTotalSaving()
    {
        String totalS="";
        int totalI, totalE, totalSav;
        int sumE=0;
        int sumI=0;
        Cursor cursorE = dbHelper.getAllExpenses();
        Cursor cursorI = dbHelper.getAllIncome();
        while(cursorE.moveToNext()){
            String expense = cursorE.getString(cursorE.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int e = Integer.parseInt(expense);
            sumE += e;
        }
        cursorE.close();

        while(cursorI.moveToNext()){
            String income = cursorI.getString(cursorI.getColumnIndex(Income.COL_INCOME_AMOUNT));
            int i = Integer.parseInt(income);
            sumI += i;
        }
        cursorI.close();

        totalSav = sumI - sumE;
        totalS = String.valueOf(totalSav);
        return totalS;
    }

    public String prevMonth(String month, int year){
        String result="";
        int indexofCurrentMonth = 0;
        int indexofPrevMonth= 0;
        for(int i = 0; i < monthList.size(); i++)
        {
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
        for(int i = 0; i < monthList.size(); i++)
        {
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

    public ArrayList<SliceValue> getData(String month, String year){
        ArrayList<SliceValue> dataList = new ArrayList<SliceValue>();
        int sumF = 0;
        int sumL = 0;
        int sumT = 0;
        int sumB = 0;
        int sumD = 0;
        int sumO = 0;
        Cursor cursorFood = dbHelper.getAllExpensesByCategoryandMonth("Food",month,year);
        Cursor cursorLeisure = dbHelper.getAllExpensesByCategoryandMonth("Leisure",month,year);
        Cursor cursorTransportation = dbHelper.getAllExpensesByCategoryandMonth("Transportation",month,year);
        Cursor cursorBills = dbHelper.getAllExpensesByCategoryandMonth("Bills",month,year);
        Cursor cursorDebt = dbHelper.getAllExpensesByCategoryandMonth("Debt",month,year);
        Cursor cursorOthers = dbHelper.getAllExpensesByCategoryandMonth("Others",month,year);

        while(cursorFood.moveToNext()){
            String s = cursorFood.getString(cursorFood.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int val = Integer.parseInt(s);
            sumF += val;
        }
        cursorFood.close();

        while(cursorLeisure.moveToNext()){
            String s = cursorLeisure.getString(cursorLeisure.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int val = Integer.parseInt(s);
            sumL += val;
        }
        cursorLeisure.close();

        while(cursorTransportation.moveToNext()){
            String s = cursorTransportation.getString(cursorTransportation.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int val = Integer.parseInt(s);
            sumT += val;
        }
        cursorTransportation.close();

        while(cursorBills.moveToNext()){
            String s = cursorBills.getString(cursorBills.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int val = Integer.parseInt(s);
            sumB += val;
        }
        cursorBills.close();

        while(cursorDebt.moveToNext()){
            String s = cursorDebt.getString(cursorDebt.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int val = Integer.parseInt(s);
            sumD += val;
        }
        cursorDebt.close();

        while(cursorOthers.moveToNext()){
            String s = cursorOthers.getString(cursorOthers.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int val = Integer.parseInt(s);
            sumO += val;
        }
        cursorOthers.close();

        SliceValue sliceF = new SliceValue(sumF, Color.BLUE);
        sliceF.setLabel(("Food " + (int) sliceF.getValue() + "%"));
        SliceValue sliceL = new SliceValue(sumL, Color.YELLOW);
        sliceL.setLabel("Leisure " + (int)sliceL.getValue() + "%" );
        SliceValue sliceT = new SliceValue(sumT, Color.RED);
        sliceT.setLabel("Transportation " + (int)sliceT.getValue() + "%" );
        SliceValue sliceB = new SliceValue(sumB, Color.LTGRAY);
        sliceB.setLabel("Bills " + (int)sliceB.getValue() + "%" );
        SliceValue sliceD = new SliceValue(sumD, Color.GREEN);
        sliceD.setLabel("Debt " + (int)sliceD.getValue() + "%" );
        SliceValue sliceO = new SliceValue(sumO, Color.DKGRAY);
        sliceO.setLabel("Others " + (int) sliceO.getValue() + "%");

        dataList.add(sliceF);
        dataList.add(sliceL);
        dataList.add(sliceT);
        dataList.add(sliceB);
        dataList.add(sliceD);
        dataList.add(sliceO);

        return dataList;
    }

    public void onResume(){
        super.onResume();
        String date = monthText.getText().toString();
        String[] MandY = date.split(" ");
        //Cursor cursor = dbHelper.getAllExpensesByMonth(MandY[0],MandY[1]);
        monthlyIncome.setText(getMonthlyIncome(MandY[0],MandY[1]));
        monthlyExpenses.setText(getMonthlyExpense(MandY[0], MandY[1]));
        monthlySavings.setText(getMonthlySaving(MandY[0], MandY[1]));
        totalSavings.setText(getTotalSaving());
        PieChartData data = new PieChartData(getData(MandY[0],MandY[1]));
        pieChart.setPieChartData(data);
    }
}
