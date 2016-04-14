package tapales.manto.bhuller.loot;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>{
    private List<Achievement> achievements;
    public AchievementAdapter(List<Achievement> achievements){
        this.achievements = achievements;
    }
    public static class AchievementViewHolder extends RecyclerView.ViewHolder{
        TextView achievementTitle, achievementDescription, pointValue;
        ImageView isLocked;
        CardView container;
        public AchievementViewHolder(View itemView){
            super(itemView);
            achievementTitle = (TextView) itemView.findViewById(R.id.achievement_title);
            achievementDescription = (TextView) itemView.findViewById(R.id.achievement_description);
            pointValue = (TextView) itemView.findViewById(R.id.achievement_points);
            isLocked = (ImageView) itemView.findViewById(R.id.achievement_photo);
            container = (CardView) itemView.findViewById(R.id.achievement_cv);
        }
    }
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.achievement_item, parent, false);
        AchievementViewHolder viewHolder = new AchievementViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(AchievementViewHolder holder, int position){
        holder.achievementTitle.setText(achievements.get(position).getAchievementName());
        holder.achievementDescription.setText(achievements.get(position).getAchievementDescription());
        holder.pointValue.setText(String.valueOf(achievements.get(position).getPointValue()));
        holder.isLocked.setImageResource(achievements.get(position).getLockedInt());

        final boolean isLocked = achievements.get(position).isLocked();
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLocked){
                    Toast.makeText(v.getContext(), "Achievement Unlocked", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(v.getContext(), "Achievement Locked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public int getItemCount(){
        return achievements.size();
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
