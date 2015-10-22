package fr.scrutch.estelle.vmsalpha;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gael on 22/10/15.
 */
public class measureDatabase extends SQLiteOpenHelper {
    static final private int DB_VERSION = 1;
    static final private String DB_NAME = "measures";
    
    public measureDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
