package fr.scrutch.estelle.vmsalpha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by gael on 13/11/15.
 */
public class DBSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbtest.db";
    private static final int DB_VERSION = 1;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MEASURE = "time" ;
    public static final String TABLE_NAME= "perf";

    public DBSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating the DB...");
        db.execSQL(
                "create table "
                        + TABLE_NAME + " ( " + COLUMN_ID + " integer primary key autoincrement, "
                        + COLUMN_MEASURE + " real);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
