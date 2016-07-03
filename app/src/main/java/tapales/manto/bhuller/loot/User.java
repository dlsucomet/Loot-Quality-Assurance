package tapales.manto.bhuller.loot;

public class User{
    public static final String TABLE_NAME = "User";
    public static final String COL_ID = "userID";
    public static final String COL_NAME = "name";
    //public static final String COL_PINCODE = "pincode";
    public static final String COL_LEVEL = "level";
    public static final String COL_XP = "xp";
    private String name;
    private int pincode;
    private int level, xp;
    public User(){
    }
    public User(String name, int level, int xp){
        this.name = name;
        //this.pincode = pincode;
        this.level = level;
        this.xp = xp;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    /*public int getPincode(){
        return pincode;
    }
    public void setPincode(int pincode){
        this.pincode = pincode;
    }*/
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}
