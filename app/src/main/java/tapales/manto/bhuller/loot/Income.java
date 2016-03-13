package tapales.manto.bhuller.loot;

public class Income{
    public static final String TABLE_NAME = "Income";
    public static final String COL_ID = "incomeID";
    public static final String COL_INCOME_NAME = "incName";
    public static final String COL_INCOME_AMOUNT = "incomeAmount";
    public static final String COL_TIME_INTERVAL = "timeInterval";
    private float incomeAmount;
    private String incomeName, timeInterval;
    private int id;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public Income(){
    }
    public Income(String incomeName, float incomeAmount, String timeInterval, int id){
        this.incomeName = incomeName;
        this.incomeAmount = incomeAmount;
        this.timeInterval = timeInterval;
        this.id = id;
    }
    public Income(String incomeName, float incomeAmount, String timeInterval){
        this.incomeName = incomeName;
        this.incomeAmount = incomeAmount;
        this.timeInterval = timeInterval;
    }
    public String getIncomeName(){
        return incomeName;
    }
    public void setIncomeName(String incomeName){
        this.incomeName = incomeName;
    }
    public float getIncomeAmount(){
        return incomeAmount;
    }
    public void setIncomeAmount(float incomeAmount){
        this.incomeAmount = incomeAmount;
    }
    public String getTimeInterval(){
        return timeInterval;
    }
    public void setTimeInterval(String timeInterval){
        this.timeInterval = timeInterval;
    }
}
