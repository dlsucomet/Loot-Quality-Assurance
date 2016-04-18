package tapales.manto.bhuller.loot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseOpenHelper extends SQLiteOpenHelper{
    public static final String SCHEMA = "loot";
    private int DummyDataInserted = 0;

    public DatabaseOpenHelper(Context context){
        super(context, SCHEMA, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + User.TABLE_NAME  + " ("
                + User.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + User.COL_NAME + " TEXT, "
                + User.COL_PINCODE + " INTEGER, "
                + User.COL_LEVEL + " INTEGER, "
                + User.COL_XP + " INTEGER);";
        db.execSQL(sql);
        sql = "CREATE TABLE " + Expense.TABLE_NAME  + " ("
                + Expense.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Expense.COL_EXPENSE_NAME + " TEXT, "
                + Expense.COL_SPENT_AMOUNT + " FLOAT, "
                + Expense.COL_PAYMENT_TYPE + " INTEGER, "
                + Expense.COL_CATEGORY + " TEXT, "
                + Expense.COL_DATE + " TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE " + Income.TABLE_NAME  + " ("
                + Income.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Income.COL_NAME + " TEXT, "
                + Income.COL_INCOME_AMOUNT + " FLOAT, "
                + Income.COL_TIME_INTERVAL + " TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE " + Achievement.TABLE_NAME  + " ("
                + Achievement.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Achievement.COL_NAME + " TEXT, "
                + Achievement.COL_DESC + " TEXT, "
                + Achievement.COL_POINTS + " INTEGER, "
                + Achievement.COL_LOCKED + " INTEGER);";
        db.execSQL(sql);

    }

    public void deleteAllExpenses(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + Expense.TABLE_NAME);
    }

    public void insertDummyData(){

        //inserting dummy user
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_NAME, "User");
        contentValues.put(User.COL_PINCODE, 1234);
        long id =  db.insert(User.TABLE_NAME, null, contentValues);

        //inserting dummy expenses
        contentValues = new ContentValues();
        contentValues.put(Expense.COL_EXPENSE_NAME, "Burger");
        contentValues.put(Expense.COL_SPENT_AMOUNT, 100);
        contentValues.put(Expense.COL_PAYMENT_TYPE, 1);
        contentValues.put(Expense.COL_CATEGORY, "Food");
        contentValues.put(Expense.COL_DATE, "3/13/2016");
        id =  db.insert(Expense.TABLE_NAME, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(Expense.COL_EXPENSE_NAME, "Taxi");
        contentValues.put(Expense.COL_SPENT_AMOUNT, 150);
        contentValues.put(Expense.COL_PAYMENT_TYPE, 1);
        contentValues.put(Expense.COL_CATEGORY, "Transportation");
        contentValues.put(Expense.COL_DATE, "2/13/2016");
        id =  db.insert(Expense.TABLE_NAME, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(Expense.COL_EXPENSE_NAME, "Electricity");
        contentValues.put(Expense.COL_SPENT_AMOUNT, 1000);
        contentValues.put(Expense.COL_PAYMENT_TYPE, 2);
        contentValues.put(Expense.COL_CATEGORY, "Bills");
        contentValues.put(Expense.COL_DATE, "3/10/2016");
        id =  db.insert(Expense.TABLE_NAME, null, contentValues);


        contentValues = new ContentValues();
        contentValues.put(Expense.COL_EXPENSE_NAME, "Pizza");
        contentValues.put(Expense.COL_SPENT_AMOUNT, 250);
        contentValues.put(Expense.COL_PAYMENT_TYPE, 1);
        contentValues.put(Expense.COL_CATEGORY, "Food");
        contentValues.put(Expense.COL_DATE, "3/05/2016");
        id =  db.insert(Expense.TABLE_NAME, null, contentValues);


        contentValues = new ContentValues();
        contentValues.put(Expense.COL_EXPENSE_NAME, "Uber");
        contentValues.put(Expense.COL_SPENT_AMOUNT, 80);
        contentValues.put(Expense.COL_PAYMENT_TYPE, 1);
        contentValues.put(Expense.COL_CATEGORY, "Transportation");
        contentValues.put(Expense.COL_DATE, "2/25/2016");
        id =  db.insert(Expense.TABLE_NAME, null, contentValues);


        contentValues = new ContentValues();
        contentValues.put(Expense.COL_EXPENSE_NAME, "Notebook");
        contentValues.put(Expense.COL_SPENT_AMOUNT, 100);
        contentValues.put(Expense.COL_PAYMENT_TYPE, 1);
        contentValues.put(Expense.COL_CATEGORY, "Others");
        contentValues.put(Expense.COL_DATE, "3/02/2016");
        id =  db.insert(Expense.TABLE_NAME, null, contentValues);

        db.close();

    }

    public void insertDummyIncome()
    {
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Income.COL_NAME, "Salary");
        contentValues.put(Income.COL_INCOME_AMOUNT, 1000);
        contentValues.put(Income.COL_TIME_INTERVAL, "Monthly");
        long id =  db.insert(Income.TABLE_NAME, null, contentValues);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS " + User.TABLE_NAME;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + Expense.TABLE_NAME;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + Income.TABLE_NAME;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + Achievement.TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

    public void deleteAllData(){
//        SQLiteDatabase db =  getWritableDatabase();
//        String sql = "DROP TABLE IF EXISTS " + User.TABLE_NAME;
//        db.execSQL(sql);
//        sql = "DROP TABLE IF EXISTS " + Expense.TABLE_NAME;
//        db.execSQL(sql);
//        sql = "DROP TABLE IF EXISTS " + Income.TABLE_NAME;
//        db.execSQL(sql);
//        onCreate(db);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Expense.TABLE_NAME, null, null);
        db.delete(Income.TABLE_NAME, null, null);
        updateLevel(1);
        updateXP(0);
        lockAllAchievements();
        //achievement to be fixed
    }

    public long insertUser(User u){
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_NAME, u.getName());
        contentValues.put(User.COL_PINCODE, u.getPincode());
        contentValues.put(User.COL_LEVEL, u.getLevel());
        contentValues.put(User.COL_XP, u.getXp());
        long id = db.insert(User.TABLE_NAME, null, contentValues);
        insertAllAchievements();
        db.close();
        return id;
    }
    public User getUser(){
        User u = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(User.TABLE_NAME,
                null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            u = new User();
            u.setName(cursor.getString(cursor.getColumnIndex(User.COL_NAME)));
            u.setPincode(cursor.getInt(cursor.getColumnIndex(User.COL_PINCODE)));
            u.setLevel(cursor.getInt(cursor.getColumnIndex(User.COL_LEVEL)));
            u.setXp(cursor.getInt(cursor.getColumnIndex(User.COL_XP)));
        }
        cursor.close();
        return u;
    }
    public int updateUsername(String username){
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_NAME, username);
        return  getWritableDatabase().update(User.TABLE_NAME,
                contentValues,
                User.COL_ID + " = 1 ",
                null);
    }
    public int updatePasscode(int passcode){
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_PINCODE, passcode);
        return  getWritableDatabase().update(User.TABLE_NAME,
                contentValues,
                User.COL_ID + " = 1 ",
                null);
    }
    public int updateXP(int xp){
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_XP, xp);
        return  getWritableDatabase().update(User.TABLE_NAME,
                contentValues,
                User.COL_ID + " = 1 ",
                null);
    }
    public int updateLevel(int level){
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_LEVEL, level);
        return  getWritableDatabase().update(User.TABLE_NAME,
                contentValues,
                User.COL_ID + " = 1 ",
                null);
    }

    public long insertExpense(Expense e){
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Expense.COL_EXPENSE_NAME, e.getExpName());
        contentValues.put(Expense.COL_SPENT_AMOUNT, e.getSpentAmount());
        contentValues.put(Expense.COL_PAYMENT_TYPE, e.getPaymentType());
        contentValues.put(Expense.COL_CATEGORY, e.getCategory());
        contentValues.put(Expense.COL_DATE, e.getDate().toString());
        long  id =  db.insert(Expense.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public Expense getExpense(int id){
        Expense e = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Expense.TABLE_NAME,
                null,
                Expense.COL_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);
        if(cursor.moveToFirst()){
            e = new Expense();
            e.setExpName(cursor.getString(cursor.getColumnIndex(Expense.COL_EXPENSE_NAME)));
            e.setSpentAmount(cursor.getFloat(cursor.getColumnIndex(Expense.COL_SPENT_AMOUNT)));
            e.setPaymentType(cursor.getInt(cursor.getColumnIndex(Expense.COL_PAYMENT_TYPE)));
            e.setCategory(cursor.getString(cursor.getColumnIndex(Expense.COL_CATEGORY)));
            e.setDate(cursor.getString(cursor.getColumnIndex(Expense.COL_DATE)));
            e.setId(id);
        }
        cursor.close();
        return e;
    }

    public Cursor getAllExpensesByMonth(String month, String year){
        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor =  db.query(Expense.TABLE_NAME, null, null, null, null, null, null);
        Cursor cursor =  db.query(Expense.TABLE_NAME,
                null,
                Expense.COL_DATE + " LIKE '%" + month + "%' AND " +  Expense.COL_DATE + " LIKE '%" + year + "%' " ,
                null,
                null, null, null);
        return cursor;
    }

    public Cursor getAllExpenses(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Expense.TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }
    public Cursor getAllExpensesByCategoryandMonth(String category, String month, String year){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Expense.TABLE_NAME,
                null,
                Expense.COL_CATEGORY + " =? AND " +
                        Expense.COL_DATE + " LIKE '%" + month + "%' AND " +  Expense.COL_DATE + " LIKE '%" + year + "%' " ,
                new String[]{category},
                null, null, null);
        return cursor;
    }

    public Cursor getAllExpensesByCategory(String category){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Expense.TABLE_NAME,
                null,
                Expense.COL_CATEGORY + " =? ",
                new String[]{category},
                null, null, null);
        return cursor;
    }


    public Cursor getAllExpensesByPaymentType(int paymentType){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Expense.TABLE_NAME,
                null,
                Expense.COL_PAYMENT_TYPE + " =? ",
                new String[]{String.valueOf(paymentType)},
                null, null, null);
        return cursor;
    }
    public Cursor getAllExpensesByTimeInterval(String interval, int month){
        Cursor cursor = null;
        if(interval.equalsIgnoreCase("monthly")){
            SQLiteDatabase db = getReadableDatabase();
            cursor =  db.query(Expense.TABLE_NAME,
                    null,
                    Expense.COL_DATE + " LIKE \"?%\" ",
                    new String[]{String.valueOf(month)},
                    null, null, null);
            return cursor;
        }
        else if(interval.equalsIgnoreCase("daily")){
            cursor = getAllExpenses();
        }
        return cursor;
    }
    public int updateExpense(Expense updatedExpense){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Expense.COL_EXPENSE_NAME, updatedExpense.getExpName());
        contentValues.put(Expense.COL_SPENT_AMOUNT, updatedExpense.getSpentAmount());
        contentValues.put(Expense.COL_PAYMENT_TYPE, updatedExpense.getPaymentType());
        contentValues.put(Expense.COL_CATEGORY, updatedExpense.getCategory());
        contentValues.put(Expense.COL_DATE, updatedExpense.getDate().toString());
        return  getWritableDatabase().update(Expense.TABLE_NAME,
                contentValues,
                Expense.COL_ID + " =? ",
                new String[]{String.valueOf(updatedExpense.getId())});
    }
    public int deleteExpense(int id){
        return getWritableDatabase().delete(Expense.TABLE_NAME,
                Expense.COL_ID + " =? ",
                new String[]{String.valueOf(id)});
    }
    public Cursor getAllIncome(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Income.TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllIncomeByMonth(String month, String year){
        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor =  db.query(Income.TABLE_NAME, null, null, null, null, null, null);
        Cursor cursor =  db.query(Income.TABLE_NAME,
                null,
                Income.COL_TIME_INTERVAL + " LIKE '%" + month + "%' AND " +  Income.COL_TIME_INTERVAL + " LIKE '%" + year + "%' " ,
                null,
                null, null, null);
        return cursor;
    }
    public long insertIncome(Income i){
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Income.COL_NAME, i.getIncomeName());
        contentValues.put(Income.COL_INCOME_AMOUNT, i.getIncomeAmount());
        contentValues.put(Income.COL_TIME_INTERVAL, i.getTimeInterval());
        long id =  db.insert(Income.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }
    public int updateIncome(Income updatedIncome){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Income.COL_NAME, updatedIncome.getIncomeName());
        contentValues.put(Income.COL_INCOME_AMOUNT, updatedIncome.getIncomeAmount());
        contentValues.put(Income.COL_TIME_INTERVAL, updatedIncome.getTimeInterval());
        return  getWritableDatabase().update(Income.TABLE_NAME,
                contentValues,
                Income.COL_ID + " =? ",
                new String[]{String.valueOf(updatedIncome.getId())});
    }
    public Income getIncome(int id){
        Income i = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Income.TABLE_NAME,
                null,
                Income.COL_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);
        if(cursor.moveToFirst()){
            i = new Income();
            i.setIncomeName(cursor.getString(cursor.getColumnIndex(Income.COL_NAME)));
            i.setIncomeAmount(cursor.getFloat(cursor.getColumnIndex(Income.COL_INCOME_AMOUNT)));
            i.setTimeInterval(cursor.getString(cursor.getColumnIndex(Income.COL_TIME_INTERVAL)));
            i.setId(id);
        }
        cursor.close();
        return i;
    }
    public int deleteIncome(int id){
        return getWritableDatabase().delete(Income.TABLE_NAME,
                Income.COL_ID + " =? ",
                new String[]{String.valueOf(id)});
    }

    public Cursor getAllAchievements(){
        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor =  db.query(Income.TABLE_NAME, null, null, null, null, null, null);
        Cursor cursor =  db.query(Achievement.TABLE_NAME,
                null,null, null,
                null, null, null);
        return cursor;
    }

    public long insertAchievement(Achievement a){
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Achievement.COL_NAME, a.getAchievementName());
        contentValues.put(Achievement.COL_DESC, a.getAchievementDescription());
        contentValues.put(Achievement.COL_POINTS, a.getPointValue());
        contentValues.put(Achievement.COL_LOCKED, a.isLocked());
        long id =  db.insert(Achievement.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public Achievement getAchievement(int id){
        Achievement a = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Achievement.TABLE_NAME,
                null,
                Achievement.COL_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null);
        if(cursor.moveToFirst()){
            a = new Achievement();
            a.setId(id);
            a.setAchievementName(cursor.getString(cursor.getColumnIndex(Achievement.COL_NAME)));
            a.setAchievementDescription((cursor.getString(cursor.getColumnIndex(Achievement.COL_DESC))));
            a.setPointValue((cursor.getInt(cursor.getColumnIndex(Achievement.COL_POINTS))));
            a.setLocked(cursor.getInt(cursor.getColumnIndex(Achievement.COL_LOCKED)));

        }
        cursor.close();
        return a;
    }

    public int updateAchievement(int id, int locked){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Achievement.COL_LOCKED, locked);
        return  getWritableDatabase().update(Achievement.TABLE_NAME,
                contentValues,
                Achievement.COL_ID + " =? ",
                new String[]{String.valueOf(id)});
    }
    public int getDaysUsed(){
        int days = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT DISTINCT " + Expense.COL_DATE + " FROM " + Expense.TABLE_NAME , null);
        while(cursor.moveToNext())
        {
            days++;
        }

        return days;
    }

    public int getNoAchUnlocked() {
        int count = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Achievement.TABLE_NAME,
                null,
                Achievement.COL_LOCKED + " =? ",
                new String[]{String.valueOf(0)},
                null, null, null);

        while (cursor.moveToNext()) {
            count++;
        }
        return count;
    }

    public int getTotalNoAch()
    {
        int count = 0;
        Cursor cursor = null;
        cursor = getAllAchievements();
        while (cursor.moveToNext()) {
            count++;
        }
        return count;
    }

    public int getNoExpenses(){
        int count = 0;
        Cursor cursor = null;
        cursor = getAllExpenses();
        while (cursor.moveToNext()) {
            count++;
        }
        return count;

    }
    public int getNoIncomes(){
        int count = 0;
        Cursor cursor = null;
        cursor = getAllIncome();
        while (cursor.moveToNext()) {
            count++;
        }
        return count;
    }

    public void insertAllAchievements()
    {
            Achievement a1 = new Achievement();
            a1.setAchievementName("Looter");
            a1.setAchievementDescription("Opened the app first time");
            a1.setPointValue(50);
            a1.setLocked(1);
            insertAchievement(a1);
            Achievement a = new Achievement();
            a.setAchievementName("First Expense");
            a.setAchievementDescription("Added the first expense");
            a.setPointValue(20);
            a.setLocked(1);
            insertAchievement(a);
            Achievement a2 = new Achievement();
            a2.setAchievementName("First Income");
            a2.setAchievementDescription("Added the first income");
            a2.setPointValue(20);
            a2.setLocked(1);
            insertAchievement(a2);
            Achievement a3 = new Achievement();
            a3.setAchievementName("Super Saver");
            a3.setAchievementDescription("First Saving of more than 500");
            a3.setPointValue(30);
            a3.setLocked(1);
            insertAchievement(a3);
            Achievement a4 = new Achievement();
            a4.setAchievementName("Big Income");
            a4.setAchievementDescription("Added 5 income items");
            a4.setPointValue(40);
            a4.setLocked(1);
            insertAchievement(a4);
            Achievement a5 = new Achievement();
            a5.setAchievementName("Big Spender");
            a5.setAchievementDescription("Added 5 expense items");
            a5.setPointValue(40);
            a5.setLocked(1);
            insertAchievement(a5);
    }

    public int getNoAchUnlockedPts() {
        int pts = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Achievement.TABLE_NAME,
                null,
                Achievement.COL_LOCKED + " =? ",
                new String[]{String.valueOf(0)},
                null, null, null);

        while (cursor.moveToNext()) {
            int val = cursor.getInt(cursor.getColumnIndex(Achievement.COL_POINTS));
            pts += val;
        }
        updateXP(pts);
        return pts;
    }

    public Cursor getAllLockedAchievements()
    {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Achievement.TABLE_NAME,
                null,
                Achievement.COL_LOCKED + " =? ",
                new String[]{String.valueOf(1)},
                null, null, null);
        return cursor;
    }
    public int lockAllAchievements()
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Achievement.COL_LOCKED, 1);
        return  getWritableDatabase().update(Achievement.TABLE_NAME,
                contentValues,
                null,
                null);
    }
}