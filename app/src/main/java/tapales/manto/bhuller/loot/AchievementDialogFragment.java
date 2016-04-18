package tapales.manto.bhuller.loot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class AchievementDialogFragment extends DialogFragment {
    View v;
    BarData data;
    BarDataSet dataset;
    ArrayList<String> labels = new ArrayList<String>();
    HorizontalBarChart barChart;
    ArrayList<BarEntry> experience = new ArrayList<>();
    TextView achievementName;
    TextView pointsAdded;
    Button btnOk;
    public Dialog onCreateDialog(Bundle savedInstanceState){
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_unlocked, null);
        achievementName = (TextView) v.findViewById(R.id.dialog_unlocked_title);
        pointsAdded = (TextView) v.findViewById(R.id.dialog_plus_points);
        achievementName.setText(getArguments().getString("name") + " unlocked");
        pointsAdded.setText("+" + getArguments().getInt("points"));
        btnOk = (Button) v.findViewById(R.id.btn_Ok);
        barChart = (HorizontalBarChart) v.findViewById(R.id.dialog_experience_bar);
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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setView(v);
        Dialog d = dialogBuilder.create();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return d;
    }
}