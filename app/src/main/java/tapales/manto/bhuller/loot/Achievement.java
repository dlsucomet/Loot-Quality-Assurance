package tapales.manto.bhuller.loot;

public class Achievement{
    public static final String TABLE_NAME = "Achievement";
    public static final String COL_ID = "achID";
    public static final String COL_NAME = "achName";
    public static final String COL_DESC = "achDesc";
    public static final String COL_POINTS = "points";
    public static final String COL_LOCKED = "locked";
    private int id, pointValue;
    private String achievementName;
    private String achievementDescription;
    private int isLocked;
    public Achievement(){
    }
    public Achievement(int id, int pointValue, String achievementName, String achievementDescription,int isLocked){
        this.id = id;
        this.pointValue = pointValue;
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.isLocked = isLocked;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getPointValue(){
        return pointValue;
    }
    public void setPointValue(int pointValue){
        this.pointValue = pointValue;
    }
    public String getAchievementName(){
        return achievementName;
    }
    public void setAchievementName(String achievementName){
        this.achievementName = achievementName;
    }
    public String getAchievementDescription(){
        return achievementDescription;
    }
    public void setAchievementDescription(String achievementDescription){
        this.achievementDescription = achievementDescription;
    }
    public int isLocked(){
        return isLocked;
    }
    public void setLocked(int locked){
        isLocked = locked;
    }
    public int getLockedInt(){
        if(isLocked == 1){
            return R.drawable.lock;
        }
        else return R.drawable.unlocked;
    }
}
