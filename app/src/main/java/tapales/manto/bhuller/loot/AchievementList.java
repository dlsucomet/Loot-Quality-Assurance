package tapales.manto.bhuller.loot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class AchievementList extends Fragment{
    RecyclerView rvAchievements;
    AchievementAdapter achievementAdapter;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.achievement_list, container, false);
        List<Achievement> achievements = new ArrayList<>();
        achievements.add(new Achievement(1, 15, "Big Spender", "Spent more than 10,000 Pesos in a day!", true));
        achievements.add(new Achievement(2, 20, "Buck", "Saved 1 Peso in a total of 3 days.", true));
        achievements.add(new Achievement(3, 30, "Cha-ching", "Saved more than 10,000 Pesos in a day!", false));
        achievementAdapter = new AchievementAdapter(achievements);
        rvAchievements = (RecyclerView) v.findViewById(R.id.recycler_achievements);
        rvAchievements.setAdapter(achievementAdapter);
        rvAchievements.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;

    }
}