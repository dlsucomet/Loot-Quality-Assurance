package tapales.manto.bhuller.loot;

public class User{
    public static final String TABLE_NAME = "User";
    public static final String COL_ID = "userID";
    public static final String COL_NAME = "name";
    public static final String COL_PINCODE = "pincode";
    private String name;
    private int pincode;
    public User(){
    }
    public User(String name, int pincode){
        this.name = name;
        this.pincode = pincode;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getPincode(){
        return pincode;
    }
    public void setPincode(int pincode){
        this.pincode = pincode;
    }
}
