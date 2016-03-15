package tapales.manto.bhuller.loot;

public class Expense{
    public static final String TABLE_NAME = "Expense";
    public static final String COL_ID = "expenseID";
    public static final String COL_EXPENSE_NAME = "expenseName";
    public static final String COL_SPENT_AMOUNT = "spentAmount";
    public static final String COL_PAYMENT_TYPE = "paymentType";
    public static final String COL_CATEGORY = "category";
    public static final String COL_DATE = "date";
    private int id;
    private String expName;
    private Float spentAmount;
    private int paymentType;
    private String category;
    private String date;
    public Expense(){
    }
    public Expense(int id, String expName, Float spentAmount, int paymentType, String category, String date) {
        this.expName = expName;
        this.spentAmount = spentAmount;
        this.paymentType = paymentType;
        this.category = category;
        this.date = date;
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getExpName(){
        return expName;
    }
    public void setExpName(String expName){
        this.expName = expName;
    }
    public Float getSpentAmount(){
        return spentAmount;
    }
    public void setSpentAmount(Float spentAmount){
        this.spentAmount = spentAmount;
    }
    public int getPaymentType(){
        return paymentType;
    }
    public void setPaymentType(int paymentType){
        this.paymentType = paymentType;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public int getCategoryInt(){
        if(category.equalsIgnoreCase("Food")){
            return R.drawable.food;
        }
        else if(category.equalsIgnoreCase("Leisure")){
            return R.drawable.leisure;
        }
        else if(category.equalsIgnoreCase("Transportation")){
            return R.drawable.transportation;
        }
        else if(category.equalsIgnoreCase("Bills")){
            return R.drawable.bills;
        }
        else if(category.equalsIgnoreCase("Debt")){
            return R.drawable.debt;
        }
        else if(category.equalsIgnoreCase("Others")){
            return R.drawable.others;
        }
        else return R.drawable.about;
    }
}
