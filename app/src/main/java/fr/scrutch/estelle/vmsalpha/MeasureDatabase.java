package fr.scrutch.estelle.vmsalpha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by gael on 22/10/15.
 */
public class MeasureDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "measures.db";

    protected static final String TABLE_NAME = "measures";
    protected static final String COLUMN_ID = "_id";
    protected static final String COLUMN_NAME = "time";

    public MeasureDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Called if the database is accessed but not yet created
     * @param db java representation of the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "
                        + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, "
                        + COLUMN_NAME + "integer);"
        );
    }

    /**
     * Called if the database version in our code is greater than the database one. Let us update
     * the database
     * @param db java representation of the database
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MeasureDatabase.class.getName(),"Upgrading db from version " + oldVersion
                + " to " + newVersion + ", which destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
