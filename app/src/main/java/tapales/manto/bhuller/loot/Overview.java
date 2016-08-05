package tapales.manto.bhuller.loot;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DateFormatSymbols;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Overview extends Fragment{
    private TextView monthText, totalSavings, monthlySavings, monthlyIncome, monthlyExpenses;

    private ImageView backMonth, forwardMonth;
    private DatabaseOpenHelper dbHelper;
    private ArrayList<String> monthList;
    private String[] CurrentMandY;
    private PieChart pieChart;
    private PieDataSet pieDataSet;
    private PieData dataSet;
    private int mYear, mMonth, mDay;
    private ArrayList<String> labels = new ArrayList<String>();
    private int[] colors = new int[6];
    private DecimalFormat format = new DecimalFormat("0.#");
    private DecimalFormat formatter = new DecimalFormat("#,##0.00");
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.overview, container, false);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        dbHelper = new DatabaseOpenHelper(v.getContext());
        pieChart = (PieChart) v.findViewById(R.id.pie_chart);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(30);
        pieChart.setTransparentCircleRadius(36);
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutQuad);
        pieChart.setDrawSliceText(!pieChart.isDrawSliceTextEnabled());
        pieChart.setHighlightPerTapEnabled(false);
        monthList = new ArrayList<String>();
        final String[] months = new DateFormatSymbols().getMonths();
        for(int i = 0; i < months.length; i++){
            String month = months[i];
            monthList .add(months[i]);
        }
        monthText = (TextView) v.findViewById(R.id.overview_month_text);
        String date = months[mMonth] + " " + mYear;
        monthText.setText(date);
        CurrentMandY = date.split(" ");
        labels.add("Food");
        labels.add("Leisure");
        labels.add("Transportation");
        labels.add("Bills");
        labels.add("Debt");
        labels.add("Others");
        colors[0] = Color.parseColor("#FFA000");
        colors[1] = Color.parseColor("#00897B");
        colors[2] = Color.parseColor("#E53935");
        colors[3] = Color.parseColor("#5C6BC0");
        colors[4] = Color.parseColor("#66BB6A");
        colors[5] = Color.parseColor("#1976D2");
        ArrayList<Entry> dataList = getData(CurrentMandY[0],CurrentMandY[1]);
        pieDataSet = new PieDataSet(dataList, "");
        pieDataSet.setColors(colors);
        dataSet = new PieData(labels, pieDataSet);
        dataSet.setValueTextSize(9f);
        dataSet.setValueTextColor(Color.WHITE);
        pieChart.setData(dataSet);
        pieChart.highlightValues(null);
        pieChart.setDescription("");
        pieChart.invalidate();
        totalSavings = (TextView) v.findViewById(R.id.overview_total_price);
        monthlySavings = (TextView) v.findViewById(R.id.overview_savings_price);
        monthlyIncome = (TextView) v.findViewById(R.id.overview_income_price);
        monthlyExpenses = (TextView) v.findViewById(R.id.overview_expense_price);
        monthlyIncome.setText(formatter.format(Double.parseDouble(getMonthlyIncome(CurrentMandY[0],CurrentMandY[1]))));
        monthlyExpenses.setText(formatter.format(Double.parseDouble(getMonthlyExpense(CurrentMandY[0], CurrentMandY[1]))));
        monthlySavings.setText(formatter.format(Double.parseDouble(getMonthlySaving(CurrentMandY[0], CurrentMandY[1]))));
        double monthly = Double.parseDouble(getMonthlySaving(CurrentMandY[0], CurrentMandY[1]));

        if(monthly < 0)
            monthlySavings.setTextColor(getResources().getColor(R.color.colorRed));
        else monthlySavings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        totalSavings.setText(formatter.format(Double.parseDouble(getTotalSaving())));

        double total = Double.parseDouble(getTotalSaving());
        if(total < 0)
            totalSavings.setTextColor(getResources().getColor(R.color.colorRed));
        else totalSavings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        backMonth = (ImageView) v.findViewById(R.id.overview_left_month);
        backMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                String backMandY = prevMonth(MandY[0], Integer.parseInt(MandY[1]));
                String[] BackMandY = backMandY.split(" ");
                monthText.setText(backMandY);
                monthlyIncome.setText(formatter.format(Double.parseDouble(getMonthlyIncome(BackMandY[0],BackMandY[1]))));
                monthlyExpenses.setText(formatter.format(Double.parseDouble(getMonthlyExpense(BackMandY[0], BackMandY[1]))));
                monthlySavings.setText(formatter.format(Double.parseDouble(getMonthlySaving(BackMandY[0], BackMandY[1]))));
                double monthly = Double.parseDouble(getMonthlySaving(BackMandY[0], BackMandY[1]));
                if(monthly < 0)
                    monthlySavings.setTextColor(getResources().getColor(R.color.colorRed));
                else monthlySavings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                totalSavings.setText(formatter.format(Double.parseDouble(getTotalSaving())));
                double total = Double.parseDouble(getTotalSaving());
                if(total < 0)
                    totalSavings.setTextColor(getResources().getColor(R.color.colorRed));
                else totalSavings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                pieDataSet = new PieDataSet(getData(BackMandY[0],BackMandY[1]), "");
                pieDataSet.setColors(colors);
                dataSet = new PieData(labels, pieDataSet);
                dataSet.setValueTextSize(9f);
                dataSet.setValueTextColor(Color.WHITE);
                pieChart.setData(dataSet);
                pieChart.highlightValues(null);
                pieChart.setDescription("");
                pieChart.animateY(1000, Easing.EasingOption.EaseInOutQuad);
                pieChart.invalidate();
            }
        });
        forwardMonth = (ImageView) v.findViewById(R.id.overview_right_month);
        forwardMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = monthText.getText().toString();
                String[] MandY = date.split(" ");
                String nextMandY = nextMonth(MandY[0], Integer.parseInt(MandY[1]));
                String[] NextMandY = nextMandY.split(" ");
                monthText.setText(nextMandY);
                monthlyIncome.setText(formatter.format(Double.parseDouble(getMonthlyIncome(NextMandY[0],NextMandY[1]))));
                monthlyExpenses.setText(formatter.format(Double.parseDouble(getMonthlyExpense(NextMandY[0], NextMandY[1]))));
                monthlySavings.setText(formatter.format(Double.parseDouble(getMonthlySaving(NextMandY[0], NextMandY[1]))));
                double monthly = Double.parseDouble(getMonthlySaving(NextMandY[0], NextMandY[1]));
                if(monthly < 0)
                    monthlySavings.setTextColor(getResources().getColor(R.color.colorRed));
                else monthlySavings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                totalSavings.setText(formatter.format(Double.parseDouble(getTotalSaving())));
                double total = Double.parseDouble(getTotalSaving());
                if(total < 0)
                    totalSavings.setTextColor(getResources().getColor(R.color.colorRed));
                else totalSavings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                pieDataSet = new PieDataSet(getData(NextMandY[0],NextMandY[1]), "");
                pieDataSet.setColors(colors);
                dataSet = new PieData(labels, pieDataSet);
                dataSet.setValueTextSize(9f);
                dataSet.setValueTextColor(Color.WHITE);
                pieChart.setData(dataSet);
                pieChart.highlightValues(null);
                pieChart.setDescription("");
                pieChart.animateY(1000, Easing.EasingOption.EaseInOutQuad);
                pieChart.invalidate();
            }
        });

        monthText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        return v;
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            monthText.setText(getMonth(monthOfYear + 1) + " " + year);
        }
    };

    private void showDatePicker() {
        DatePickerFragment date2 = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date2.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date2.setCallBack(ondate);
        date2.show(getFragmentManager(), "Date Picker");
    }

    public String getMonthlyIncome(String month, String year){
        String mI="";
        double sum=0;
        Cursor cursor = dbHelper.getAllIncomeByMonth(month,year);
        while(cursor.moveToNext()){
                String income = cursor.getString(cursor.getColumnIndex(Income.COL_INCOME_AMOUNT));
                double i = Double.parseDouble(income);
                sum += i;
        }
        cursor.close();
        mI = String.valueOf(format.format(sum));
        return mI;
    }
    public String getMonthlyExpense(String month, String year){
        String mE="";
        double sum=0;
        Cursor cursor = dbHelper.getAllExpensesByMonth(month, year);
        while(cursor.moveToNext()){
            String expense = cursor.getString(cursor.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            double i = Double.parseDouble(expense);
            sum += i;
        }
        cursor.close();
        mE = String.valueOf(format.format(sum));
        return mE;
    }
    public String getMonthlySaving(String month, String year){
        String mS="";
        double saving = Double.parseDouble(getMonthlyIncome(month,year)) - Double.parseDouble(getMonthlyExpense(month,year));
        mS = String.valueOf(format.format(saving));
        return mS;
    }
    public String getTotalSaving(){
        String totalS="";
        double totalI, totalE, totalSav;
        double sumE=0;
        double sumI=0;
        Cursor cursorE = dbHelper.getAllExpenses();
        Cursor cursorI = dbHelper.getAllIncome();
        while(cursorE.moveToNext()){
            String expense = cursorE.getString(cursorE.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            double e = Double.parseDouble(expense);
            sumE += e;
        }
        cursorE.close();
        while(cursorI.moveToNext()){
            String income = cursorI.getString(cursorI.getColumnIndex(Income.COL_INCOME_AMOUNT));
            double i = Double.parseDouble(income);
            sumI += i;
        }
        cursorI.close();
        totalSav = sumI - sumE;
        totalS = String.valueOf(format.format(totalSav));
        return totalS;
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
        String result = "";
        int indexofCurrentMonth = 0;
        int indexofNextMonth = 0;
        for(int i = 0; i < monthList.size(); i++){
            if(monthList.get(i).equalsIgnoreCase(month)){
                indexofCurrentMonth = i;
                indexofNextMonth = i + 1;
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
    private ArrayList<Entry> getData(String month, String year){
        ArrayList<Entry> dataList = new ArrayList<Entry>();
        float sumF = 0;
        float sumL = 0;
        float sumT = 0;
        float sumB = 0;
        float sumD = 0;
        float sumO = 0;
        Cursor cursorFood = dbHelper.getAllExpensesByCategoryandMonth("Food",month,year);
        Cursor cursorLeisure = dbHelper.getAllExpensesByCategoryandMonth("Leisure",month,year);
        Cursor cursorTransportation = dbHelper.getAllExpensesByCategoryandMonth("Transportation",month,year);
        Cursor cursorBills = dbHelper.getAllExpensesByCategoryandMonth("Bills",month,year);
        Cursor cursorDebt = dbHelper.getAllExpensesByCategoryandMonth("Debt",month,year);
        Cursor cursorOthers = dbHelper.getAllExpensesByCategoryandMonth("Others",month,year);
        while(cursorFood.moveToNext()){
            String s = cursorFood.getString(cursorFood.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            float val = Float.parseFloat(s);
            sumF += val;
        }
        cursorFood.close();
        while(cursorLeisure.moveToNext()){
            String s = cursorLeisure.getString(cursorLeisure.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            float val = Float.parseFloat(s);
            sumL += val;
        }
        cursorLeisure.close();
        while(cursorTransportation.moveToNext()){
            String s = cursorTransportation.getString(cursorTransportation.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            float val = Float.parseFloat(s);
            sumT += val;
        }
        cursorTransportation.close();
        while(cursorBills.moveToNext()){
            String s = cursorBills.getString(cursorBills.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            float val = Float.parseFloat(s);
            sumB += val;
        }
        cursorBills.close();
        while(cursorDebt.moveToNext()){
            String s = cursorDebt.getString(cursorDebt.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            float val = Float.parseFloat(s);
            sumD += val;
        }
        cursorDebt.close();
        while(cursorOthers.moveToNext()){
            String s = cursorOthers.getString(cursorOthers.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            float val = Float.parseFloat(s);
            sumO += val;
        }
        cursorOthers.close();
            //ctr =0;
            //if sumf is not zero
            // data list,add(sumF,ctr));
            int index = 0;
            if (sumF > 0) {
                dataList.add(new Entry(sumF, index));
                index++;
            }
            if (sumL > 0) {
                dataList.add(new Entry(sumL, index));
                index++;
            }
            if (sumT > 0) {
                dataList.add(new Entry(sumT, index));
                index++;

            }
            if (sumB > 0) {
                dataList.add(new Entry(sumB, index));
                index++;

            }
            if (sumD > 0) {
                dataList.add(new Entry(sumD, index));
                index++;

            }
            if (sumO > 0) {
                dataList.add(new Entry(sumO, index));
                index++;

            }
            /*dataList.add(new Entry(sumF, 0));
            dataList.add(new Entry(sumL, 1));
            dataList.add(new Entry(sumT, 2));
            dataList.add(new Entry(sumB, 3));
            dataList.add(new Entry(sumD, 4));
            dataList.add(new Entry(sumO, 5));*/
        return dataList;
    }
    public void onResume(){
        super.onResume();
        String date = monthText.getText().toString();
        String[] MandY = date.split(" ");
        monthlyIncome.setText(formatter.format(Double.parseDouble(getMonthlyIncome(MandY[0],MandY[1]))));
        monthlyExpenses.setText(formatter.format(Double.parseDouble(getMonthlyExpense(MandY[0], MandY[1]))));
        monthlySavings.setText(formatter.format(Double.parseDouble(getMonthlySaving(MandY[0], MandY[1]))));
        double monthly = Double.parseDouble(getMonthlySaving(MandY[0], MandY[1]));
        if(monthly < 0)
            monthlySavings.setTextColor(getResources().getColor(R.color.colorRed));
        else monthlySavings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        totalSavings.setText(formatter.format(Double.parseDouble(getTotalSaving())));
        double total = Double.parseDouble(getTotalSaving());
        if(total < 0)
            totalSavings.setTextColor(getResources().getColor(R.color.colorRed));
        else totalSavings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        pieDataSet = new PieDataSet(getData(MandY[0],MandY[1]), "");
        pieDataSet.setColors(colors);
        dataSet = new PieData(labels, pieDataSet);
        dataSet.setValueTextSize(9f);
        dataSet.setValueTextColor(Color.WHITE);
        pieChart.setData(dataSet);
        pieChart.highlightValues(null);
        pieChart.setDescription("");
        pieChart.invalidate();
    }
}
