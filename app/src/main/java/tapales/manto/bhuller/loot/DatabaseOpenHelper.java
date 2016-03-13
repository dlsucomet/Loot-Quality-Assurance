package tapales.manto.bhuller.loot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper{
    public static final String SCHEMA = "loot";
    public DatabaseOpenHelper(Context context){
        super(context, SCHEMA, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + User.TABLE_NAME  + " ("
                + User.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + User.COL_NAME + " TEXT, "
                + User.COL_PINCODE + " INTEGER);";
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
                + Income.COL_INCOME_NAME + "TEXT, "
                + Income.COL_INCOME_AMOUNT + " FLOAT, "
                + Income.COL_TIME_INTERVAL + " TEXT);";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS " + User.TABLE_NAME;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + Expense.TABLE_NAME;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + Income.TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }
    public long insertUser(User u){
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_NAME, u.getName());
        contentValues.put(User.COL_PINCODE, u.getPincode());
        long id =  db.insert(User.TABLE_NAME, null, contentValues);
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
        }
        cursor.close();
        return u;
    }
    public int updateUsername(User updatedUser){
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_NAME, updatedUser.getName());
        return  getWritableDatabase().update(User.TABLE_NAME,
                contentValues,
                User.COL_ID + " = 1 ",
                null);
    }
    public int updatePincode(User updatedUser){
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_PINCODE, updatedUser.getPincode());
        return  getWritableDatabase().update(User.TABLE_NAME,
                contentValues,
                User.COL_ID + " = 1 ",
                null);
    }
    public void deleteUser(SQLiteDatabase db){
        String sql = "DROP TABLE IF EXISTS " + User.TABLE_NAME;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + Expense.TABLE_NAME;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + Income.TABLE_NAME;
        db.execSQL(sql);
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
    public Cursor getAllExpenses(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(Expense.TABLE_NAME, null, null, null, null, null, null);
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
    public long insertIncome(Income i){
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Income.COL_INCOME_NAME, i.getIncomeName());
        contentValues.put(Income.COL_INCOME_AMOUNT, i.getIncomeAmount());
        contentValues.put(Income.COL_TIME_INTERVAL, i.getTimeInterval());
        long id =  db.insert(Income.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }
    public int updateIncome(Income updatedIncome){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Income.COL_INCOME_NAME, updatedIncome.getIncomeName());
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
            i.setIncomeName(cursor.getString(cursor.getColumnIndex(Income.COL_INCOME_NAME)));
            i.setIncomeAmount(cursor.getFloat(cursor.getColumnIndex(Income.COL_INCOME_AMOUNT)));
            i.setTimeInterval(cursor.getString(cursor.getColumnIndex(Income.COL_TIME_INTERVAL)));
            i.setId(id);
        }
        cursor.close();
        return i;
    }
}
