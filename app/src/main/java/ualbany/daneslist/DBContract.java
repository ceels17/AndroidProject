package ualbany.daneslist;

import android.provider.BaseColumns;

/**
 * Created by cmagnus on 2/28/16.
 * based on:
 * http://developer.android.com/training/basics/data-storage/databases.html
 */
public final class DBContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DBContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ThingEntry implements BaseColumns {
        public static final String TABLE_NAME = "Product";
        public static final String COLUMN_ITEM_ID = "ItemID";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ONOFF = "onOff";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_CATEGORY = "category";
    }

    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_CATEGORY_ID = "categoryID";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ONOFF = "onOff";

        //public static final String COLUMN_NOTE = "note";
    }

    /*public static abstract class ThingList implements BaseColumns {
        public static final String TABLE_NAME = "thingList";
        public static final String COLUMN_THING_ID = "id";
        public static final String COLUMN_THING = "thingID";
        public static final String COLUMN_CATEGORY = "categoryID";
    }*/

}

