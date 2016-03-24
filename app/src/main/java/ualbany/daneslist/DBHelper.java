package ualbany.daneslist;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.DatabaseUtils;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabaseLockedException;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

        import java.lang.reflect.Array;
        import java.util.ArrayList;


public class DBHelper extends SQLiteAssetHelper {//SQLiteOpenHelper{

    //public static final String DATABASE_NAME = "food.db";
    private static final String DATABASE_NAME = "DanesList.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("dbhelper", "constructor called");
    }

    /*
    // Supported by SQLiteOpenHelper but not SQLiteAssetHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Thing table
        this.createTable(db, DBContract.ThingEntry.TABLE_NAME,
                DBContract.ThingEntry.COLUMN_THING_ID + " integer primary key, " +
                DBContract.ThingEntry.COLUMN_NAME + " text, " +
                DBContract.ThingEntry.COLUMN_ONOFF + " text, " +
                DBContract.ThingEntry.COLUMN_NOTE + " text, " +
                DBContract.ThingEntry.COLUMN_CATEGORY + " text"
                );
        // Category table
        this.createTable(db, DBContract.CategoryEntry.TABLE_NAME,
                DBContract.CategoryEntry.COLUMN_CATEGORY_ID + " integer primary key, " +
                        DBContract.CategoryEntry.COLUMN_NAME + " text, " +
                        DBContract.CategoryEntry.COLUMN_ONOFF + " text"
        );
    }*/

    // helper function to create tables

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTable(db, DBContract.ThingEntry.TABLE_NAME);
        dropTable(db, DBContract.CategoryEntry.TABLE_NAME);
        onCreate(db);
    }

    // helper function to drop tables
    public void dropTable(SQLiteDatabase db, String tableName) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }


    public boolean insertCategory(String name, String onOff )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.CategoryEntry.COLUMN_NAME, name);
        contentValues.put(DBContract.CategoryEntry.COLUMN_ONOFF, onOff);
        db.insert(DBContract.CategoryEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertThing(String name, String onOff, String note, String price, String phone, String email, String category) {
        // Add thing in thing table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.ThingEntry.COLUMN_NAME, name);
        contentValues.put(DBContract.ThingEntry.COLUMN_ONOFF, onOff);
        contentValues.put(DBContract.ThingEntry.COLUMN_NOTE, note);
        contentValues.put(DBContract.ThingEntry.COLUMN_CATEGORY, category);
        contentValues.put(DBContract.ThingEntry.COLUMN_PRICE, price);
        contentValues.put(DBContract.ThingEntry.COLUMN_PHONE, phone);
        contentValues.put(DBContract.ThingEntry.COLUMN_EMAIL, email);
        db.insert(DBContract.ThingEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getCursor(String table, int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " + table + " where id=" + id + "", null );
    }

    // given table name, gets number of rows
    public int numberOfRows(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, tableName);
    }

    public boolean updateCategory (Integer id, String name, String onOff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.CategoryEntry.COLUMN_NAME, name);
        contentValues.put(DBContract.CategoryEntry.COLUMN_ONOFF, onOff);
        db.update(DBContract.CategoryEntry.TABLE_NAME, contentValues, "id = ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateThing (Integer id, String name, String onOff, String note, String price, String phone, String email, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.ThingEntry.COLUMN_NAME, name);
        contentValues.put(DBContract.ThingEntry.COLUMN_ONOFF, onOff);
        contentValues.put(DBContract.ThingEntry.COLUMN_NOTE, note);
        contentValues.put(DBContract.ThingEntry.COLUMN_CATEGORY, category);
        contentValues.put(DBContract.ThingEntry.COLUMN_PRICE, price);
        contentValues.put(DBContract.ThingEntry.COLUMN_PHONE, phone);
        contentValues.put(DBContract.ThingEntry.COLUMN_EMAIL, email);

        db.update(DBContract.ThingEntry.TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    //TODO: add ability to check thingList table and move things to uncategorized category (id 1)
    public Integer deleteCategory (Integer id) {
        return this.delete(DBContract.CategoryEntry.TABLE_NAME, id);
    }

    public Integer deleteThing (Integer id) {
        return this.delete(DBContract.ThingEntry.TABLE_NAME, id);
    }

    // helper function for deleting from tables
    public Integer delete (String tableName, Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<DataModel.Category> getAllCategories() {
        ArrayList<DataModel.Category> categoryList = new ArrayList<>();
        DataModel.Category tmp;// = new DataModel().new Category();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " +
                DBContract.CategoryEntry.TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            tmp = new DataModel().new Category();
            tmp.setId(res.getInt(res.getColumnIndex(DBContract.CategoryEntry.COLUMN_CATEGORY_ID)));
            tmp.setName(res.getString(res.getColumnIndex(DBContract.CategoryEntry.COLUMN_NAME)));
            tmp.setOnOff(res.getInt(res.getColumnIndex(DBContract.CategoryEntry.COLUMN_ONOFF)));
            Log.d("getAllCategories", "onOff: " + res.getString(res.getColumnIndex(DBContract.CategoryEntry.COLUMN_ONOFF)));
            categoryList.add(tmp);
            res.moveToNext();
            res.close();
        }
        return categoryList;

        //return getAll(DBContract.CategoryEntry.TABLE_NAME, DBContract.CategoryEntry.COLUMN_NAME);
    }

    public ArrayList<DataModel.Thing> getAllThings() {
        ArrayList<DataModel.Thing> categoryList = new ArrayList<>();
        DataModel.Thing tmp;// = new DataModel().new Thing();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " +
                DBContract.ThingEntry.TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            tmp = new DataModel().new Thing();
            tmp.setId(res.getInt(res.getColumnIndex(DBContract.ThingEntry.COLUMN_ITEM_ID)));
            tmp.setName(res.getString(res.getColumnIndex(DBContract.ThingEntry.COLUMN_NAME)));
            tmp.setOnOff(res.getInt(res.getColumnIndex(DBContract.ThingEntry.COLUMN_ONOFF)));
            tmp.setNote(res.getString(res.getColumnIndex(DBContract.ThingEntry.COLUMN_NOTE)));
            tmp.setParent(res.getInt(res.getColumnIndex(DBContract.ThingEntry.COLUMN_CATEGORY)));
            tmp.setParent(res.getInt(res.getColumnIndex(DBContract.ThingEntry.COLUMN_PRICE)));
            tmp.setParent(res.getInt(res.getColumnIndex(DBContract.ThingEntry.COLUMN_PHONE)));
            tmp.setParent(res.getInt(res.getColumnIndex(DBContract.ThingEntry.COLUMN_EMAIL)));

            categoryList.add(tmp);
            res.moveToNext();
        }
        return categoryList;
        //return getAll(DBContract.ThingEntry.TABLE_NAME, DBContract.ThingEntry.COLUMN_NAME);
    }

    public ArrayList<DataModel.Thing> getAllThingsInCategory(int category)
    {
        ArrayList<DataModel.Thing> allThings = getAllThings();
        ArrayList<DataModel.Thing> allThingsInCategory = new ArrayList<>();


        for (int i = 0; i < allThings.size(); i++) {
            if (allThings.get(i).getId() == category)
                allThingsInCategory.add(allThings.get(i));
        }

        return(allThingsInCategory);
    }

    // helper function for getAll___ methods
    public ArrayList<String> getAll(String tableName, String firstColumnName) {
        ArrayList<String> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + tableName, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(firstColumnName)));
            res.moveToNext();
        }
        return array_list;
    }

    // use cursor to extract specific data
    public String getCategoryName(int id) {
        return this.getCellFromTable(DBContract.CategoryEntry.TABLE_NAME,
                DBContract.CategoryEntry.COLUMN_NAME,
                id);
    }

    public String getCategoryOnOff(int id) {
        return this.getCellFromTable(DBContract.CategoryEntry.TABLE_NAME,
                DBContract.CategoryEntry.COLUMN_ONOFF,
                id);
    }

    public String getThingName(int id) {
        return this.getCellFromTable(DBContract.ThingEntry.TABLE_NAME,
                DBContract.ThingEntry.COLUMN_NAME,
                id);
    }

    public String getThingNote(int id) {
        return this.getCellFromTable(DBContract.ThingEntry.TABLE_NAME,
                DBContract.ThingEntry.COLUMN_NOTE,
                id);
    }

    public String getThingOnOff(int id) {
        return this.getCellFromTable(DBContract.ThingEntry.TABLE_NAME,
                DBContract.ThingEntry.COLUMN_ONOFF,
                id);
    }

    // helper function to get data from a given column of a given table
    public String getCellFromTable(String tableName, String column, int id){
        Cursor curs = this.getCursor(tableName, id);
        return curs.getString(curs.getColumnIndex(column));
    }
}
