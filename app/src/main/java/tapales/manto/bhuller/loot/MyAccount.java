package tapales.manto.bhuller.loot;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MyAccount extends Fragment {
    private BarData data;
    private BarDataSet dataset;
    private ArrayList<String> labels = new ArrayList<String>();
    private HorizontalBarChart barChart;
    private ArrayList<BarEntry> experience = new ArrayList<>();
    private ImageView accountLevel;
    private TextView level;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_account, container, false);
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
}