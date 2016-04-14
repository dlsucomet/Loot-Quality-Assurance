package tapales.manto.bhuller.loot;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Random;

public class MyAccount extends Fragment {
    private BarData data;
    private BarDataSet dataset;
    private ArrayList<String> labels = new ArrayList<String>();
    private HorizontalBarChart barChart;
    private ArrayList<BarEntry> experience = new ArrayList<>();
    private Random random = new Random();
    private ImageView accountLevel;
    private TextView level, userName, daysUsed, achievementsUnlocked, incomeItems, expenseItems;
    private RelativeLayout userBox;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_account, container, false);
        userBox = (RelativeLayout) v.findViewById(R.id.my_account_box);
        randomizeColors(random.nextInt((5 - 0 + 1) + 0));
        level = (TextView) v.findViewById(R.id.account_level);
        userName = (TextView) v.findViewById(R.id.account_user_name);
        userName.setText("Chino "+randomizeSmileys(random.nextInt((5 - 0 + 1) + 0)));
        daysUsed = (TextView) v.findViewById(R.id.account_level);
        achievementsUnlocked = (TextView) v.findViewById(R.id.account_unlocked_items);
        incomeItems = (TextView) v.findViewById(R.id.account_income_count);
        expenseItems = (TextView) v.findViewById(R.id.account_expense_count);
        accountLevel = (ImageView) v.findViewById(R.id.level_photo);
        barChart = (HorizontalBarChart) v.findViewById(R.id.experience_bar);
        experience.add(new BarEntry(70f, 0));
        dataset = new BarDataSet(experience, "");
        dataset.setColor(Color.parseColor("#FFC107"));
        labels.add("");
        data = new BarData(labels, dataset);
        barChart.animateY(2500);
        barChart.setDescription("");
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.setDrawBarShadow(true);
        barChart.setClickable(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setData(data);
        barChart.getData().setHighlightEnabled(!barChart.getData().isHighlightEnabled());
        barChart.getData().setValueTextSize(0);
        return v;
    }
    public void onResume(){
        super.onResume();
        randomizeColors(random.nextInt((5 - 0 + 1) + 0));
        dataset = new BarDataSet(experience, "");
        dataset.setColor(Color.parseColor("#FFC107"));
        data = new BarData(labels, dataset);
        barChart.animateY(2500);
        barChart.setDescription("");
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.setDrawBarShadow(true);
        barChart.setClickable(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setData(data);
        barChart.getData().setHighlightEnabled(!barChart.getData().isHighlightEnabled());
        barChart.getData().setValueTextSize(0);
        barChart.invalidate();
    }
    public void randomizeColors(int random){
        int colors[] = new int[6];
        colors[0] = Color.parseColor("#2196F3");
        colors[1] = Color.parseColor("#FFA000");
        colors[2] = Color.parseColor("#FF1744");
        colors[3] = Color.parseColor("#3F51B5");
        colors[4] = Color.parseColor("#4CAF50");
        colors[5] = Color.parseColor("#1976D2");
        userBox.setBackgroundColor(colors[random]);
    }
    public String randomizeSmileys(int random){
        switch(random){
            case 0:
                return ":)";
            case 1:
                return ":(";
            case 2:
                return ":D";
            case 3:
                return ":o";
            case 4:
                return ":|  ";
            case 5:
                return "B)";
        }
        return ":|";
    }
}