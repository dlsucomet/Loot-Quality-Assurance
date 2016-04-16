package tapales.manto.bhuller.loot;

import android.app.DialogFragment;
import android.database.Cursor;
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
    private ImageView accountLevel;
    private DatabaseOpenHelper dbHelper;
    private RelativeLayout userBox;
    private Random random = new Random();
    private TextView level, userName, daysUsed, achievementsUnlocked, incomeItems, expenseItems, accountProgress;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_account, container, false);
        userBox = (RelativeLayout) v.findViewById(R.id.my_account_box);
        randomizeColors(random.nextInt((5 - 0 + 1) + 0));
        dbHelper = new DatabaseOpenHelper(v.getContext());
        level = (TextView) v.findViewById(R.id.account_level);
        accountProgress = (TextView) v.findViewById(R.id.account_progress);
        userName = (TextView) v.findViewById(R.id.account_user_name);
        daysUsed = (TextView) v.findViewById(R.id.account_days_used);
        achievementsUnlocked = (TextView) v.findViewById(R.id.account_unlocked_items);
        incomeItems = (TextView) v.findViewById(R.id.account_income_count);
        expenseItems = (TextView) v.findViewById(R.id.account_expense_count);
        accountLevel = (ImageView) v.findViewById(R.id.level_photo);
        barChart = (HorizontalBarChart) v.findViewById(R.id.experience_bar);
        experience.add(new BarEntry(100f, 0));
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
        String u = dbHelper.getUser().getName();
        userName.setText(u);
        daysUsed.setText(String.valueOf(dbHelper.getDaysUsed()));
        String achUnlocked = String.valueOf(dbHelper.getNoAchUnlocked());
        String achTotal = String.valueOf(dbHelper.getTotalNoAch());
        String ach = achUnlocked + "/" + achTotal;
        achievementsUnlocked.setText(ach);
        incomeItems.setText(String.valueOf(dbHelper.getNoIncomes()));
        expenseItems.setText(String.valueOf(dbHelper.getNoExpenses()));
        Cursor cursor = dbHelper.getAllLockedAchievements();
        ArrayList<Achievement> achList = new ArrayList<Achievement>();
        while (cursor.moveToNext()) {
            Achievement a = new Achievement();
            a.setId(cursor.getInt(cursor.getColumnIndex(Achievement.COL_ID)));
            a.setAchievementName(cursor.getString(cursor.getColumnIndex(Achievement.COL_NAME)));
            a.setAchievementDescription((cursor.getString(cursor.getColumnIndex(Achievement.COL_DESC))));
            a.setPointValue((cursor.getInt(cursor.getColumnIndex(Achievement.COL_POINTS))));
            a.setLocked(cursor.getInt(cursor.getColumnIndex(Achievement.COL_LOCKED)));
            achList.add(a);
        }
        for (Achievement a : achList) {
            if (a.getAchievementName().equalsIgnoreCase("Looter")) {
                dbHelper.updateAchievement(a.getId(), 0);
                DialogFragment df = new AchievementDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", a.getAchievementName());
                bundle.putInt("points", a.getPointValue());
                df.setArguments(bundle);
                df.show(getActivity().getFragmentManager(), null);
            }
            if (a.getAchievementName().equalsIgnoreCase("First expense"))
                if (dbHelper.getNoExpenses() == 1) {
                    dbHelper.updateAchievement(a.getId(), 0);
                    DialogFragment df = new AchievementDialogFragment();
                    df.show(getActivity().getFragmentManager(), null);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", a.getAchievementName());
                    bundle.putInt("points", a.getPointValue());
                    df.setArguments(bundle);
                }
            if (a.getAchievementName().equalsIgnoreCase("First income"))
                if (dbHelper.getNoIncomes() == 1) {
                    dbHelper.updateAchievement(a.getId(), 0);
                    DialogFragment df = new AchievementDialogFragment();
                    df.show(getActivity().getFragmentManager(), null);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", a.getAchievementName());
                    bundle.putInt("points", a.getPointValue());
                    df.setArguments(bundle);
                }
            if (a.getAchievementName().equalsIgnoreCase("Super saver")) {
                String s = getTotalSaving();
                int val = Integer.parseInt(s);
                if (val >= 500) {
                    dbHelper.updateAchievement(a.getId(), 0);
                    DialogFragment df = new AchievementDialogFragment();
                    df.show(getActivity().getFragmentManager(), null);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", a.getAchievementName());
                    bundle.putInt("points", a.getPointValue());
                    df.setArguments(bundle);
                }
            }
        }
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment df = new AchievementDialogFragment();
                df.show(getActivity().getFragmentManager(), null);
            }
        });
        level.setText("Level " + String.valueOf(dbHelper.getUser().getLevel()));
        accountProgress.setText(String.valueOf(dbHelper.getNoAchUnlockedPts()) + "/100");
        return v;
    }

    public void onResume() {
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
        Cursor cursor = dbHelper.getAllLockedAchievements();
        ArrayList<Achievement> achList = new ArrayList<Achievement>();
        while (cursor.moveToNext()) {
            Achievement a = new Achievement();
            a.setId(cursor.getInt(cursor.getColumnIndex(Achievement.COL_ID)));
            a.setAchievementName(cursor.getString(cursor.getColumnIndex(Achievement.COL_NAME)));
            a.setAchievementDescription((cursor.getString(cursor.getColumnIndex(Achievement.COL_DESC))));
            a.setPointValue((cursor.getInt(cursor.getColumnIndex(Achievement.COL_POINTS))));
            a.setLocked(cursor.getInt(cursor.getColumnIndex(Achievement.COL_LOCKED)));
            achList.add(a);
        }
        String u = dbHelper.getUser().getName();
        userName.setText(u + " " + randomizeSmileys(random.nextInt((5 - 0 + 1) + 0)));
        daysUsed.setText(String.valueOf(dbHelper.getDaysUsed()));
        String achUnlocked = String.valueOf(dbHelper.getNoAchUnlocked());
        String achTotal = String.valueOf(dbHelper.getTotalNoAch());
        String ach = achUnlocked + "/" + achTotal;
        achievementsUnlocked.setText(ach);
        incomeItems.setText(String.valueOf(dbHelper.getNoIncomes()));
        expenseItems.setText(String.valueOf(dbHelper.getNoExpenses()));
        level.setText("Level " + String.valueOf(dbHelper.getUser().getLevel()));
        accountProgress.setText(String.valueOf(dbHelper.getNoAchUnlockedPts() + "/100"));
    }

    public String getTotalSaving() {
        String totalS = "";
        int totalI, totalE, totalSav;
        int sumE = 0;
        int sumI = 0;
        Cursor cursorE = dbHelper.getAllExpenses();
        Cursor cursorI = dbHelper.getAllIncome();
        while (cursorE.moveToNext()) {
            String expense = cursorE.getString(cursorE.getColumnIndex(Expense.COL_SPENT_AMOUNT));
            int e = Integer.parseInt(expense);
            sumE += e;
        }
        cursorE.close();
        while (cursorI.moveToNext()) {
            String income = cursorI.getString(cursorI.getColumnIndex(Income.COL_INCOME_AMOUNT));
            int i = Integer.parseInt(income);
            sumI += i;
        }
        cursorI.close();
        totalSav = sumI - sumE;
        totalS = String.valueOf(totalSav);
        return totalS;
    }

    public void randomizeColors(int random) {
        int colors[] = new int[6];
        colors[0] = Color.parseColor("#2196F3");
        colors[1] = Color.parseColor("#FFA000");
        colors[2] = Color.parseColor("#FF1744");
        colors[3] = Color.parseColor("#3F51B5");
        colors[4] = Color.parseColor("#388E3C");
        colors[5] = Color.parseColor("#1976D2");
        userBox.setBackgroundColor(colors[random]);
    }

    public String randomizeSmileys(int random) {
        switch (random) {
            case 0:
                return ":)";
            case 1:
                return "b\"d";
            case 2:
                return ":D";
            case 3:
                return ":o";
            case 4:
                return ":|";
            case 5:
                return "B)";
        }
        return ":|";
    }
}