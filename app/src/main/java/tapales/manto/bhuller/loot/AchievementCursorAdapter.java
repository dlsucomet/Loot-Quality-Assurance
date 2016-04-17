package tapales.manto.bhuller.loot;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

public class AchievementCursorAdapter extends CursorRecyclerViewAdapter3<AchievementCursorAdapter.AchievementViewHolder> {
    private Context context;
    public AchievementCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }
    public void onBindViewHolder(final AchievementViewHolder viewHolder, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(Achievement.COL_NAME));
        String desc = cursor.getString(cursor.getColumnIndex(Achievement.COL_DESC));
        int points = cursor.getInt(cursor.getColumnIndex(Achievement.COL_POINTS));
        int locked = cursor.getInt(cursor.getColumnIndex(Achievement.COL_LOCKED));
        int achID = cursor.getInt(cursor.getColumnIndex(Achievement.COL_ID));
        Achievement a = new Achievement(achID,points,title,desc,locked);
        viewHolder.isLocked.setImageResource(a.getLockedInt());
        viewHolder.achievementTitle.setText(a.getAchievementName());
        viewHolder.achievementDescription.setText(a.getAchievementDescription());
        viewHolder.pointValue.setText(String.valueOf(a.getPointValue()));
        viewHolder.container.setTag(achID);
        final int isLocked = Integer.valueOf(cursor.getString(cursor.getColumnIndex(Achievement.COL_LOCKED)));
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocked == 0) {
                    SnackbarManager.show(
                            Snackbar.with(v.getContext())
                                    .text(viewHolder.achievementTitle.getText().toString() + " Already Unlocked")
                                    .duration(1000));
                } else {
                    SnackbarManager.show(
                            Snackbar.with(v.getContext())
                                    .text(viewHolder.achievementTitle.getText().toString() + " is Locked")
                                    .duration(1000));                }
            }
        });
    }
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_item, parent, false);
        return new AchievementViewHolder(v);
    }
    public class AchievementViewHolder extends RecyclerView.ViewHolder{
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
}
