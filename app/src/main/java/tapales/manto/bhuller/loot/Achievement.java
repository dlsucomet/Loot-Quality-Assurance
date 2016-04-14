package tapales.manto.bhuller.loot;

public class Achievement{
    private int id, pointValue;
    private String achievementName;
    private String achievementDescription;
    private boolean isLocked;

    public Achievement(int id, int pointValue, String achievementName, String achievementDescription, boolean isLocked) {
        this.id = id;
        this.pointValue = pointValue;
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.isLocked = isLocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public void setAchievementDescription(String achievementDescription) {
        this.achievementDescription = achievementDescription;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }


    public int getLockedInt(){
        if(isLocked){
            return R.drawable.lock;
        }
        else return R.drawable.unlocked;
    }
}
