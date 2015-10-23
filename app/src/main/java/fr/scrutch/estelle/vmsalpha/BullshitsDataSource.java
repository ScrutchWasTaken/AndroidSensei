package fr.scrutch.estelle.vmsalpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by gael on 23/10/15.
 */
public class BullshitsDataSource {
    // DB Fields
    private SQLiteDatabase db;
    private MeasureDatabase dbHelper;
    private String[] allColumns = {
            MeasureDatabase.COLUMN_ID,
            MeasureDatabase.COLUMN_NAME};

    public BullshitsDataSource(Context context) {
        dbHelper = new MeasureDatabase(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Bullshit createBullshit(Date time) {
        ContentValues values = new ContentValues();
        values.put(MeasureDatabase.COLUMN_NAME, time.toString());
        long insertId = db.insert(MeasureDatabase.TABLE_NAME, null, values);

        Cursor cursor = db.query(MeasureDatabase.TABLE_NAME,
                allColumns, MeasureDatabase.COLUMN_ID
                        + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Bullshit newBullshit = cursorToBullshit(cursor);
        cursor.close();
        return newBullshit;
    }

    public List<Bullshit> getAllTheBullshit() {
        List<Bullshit> bullshits = new ArrayList<Bullshit>();

        Cursor cursor = db.query(MeasureDatabase.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Bullshit bullshit = cursorToBullshit(cursor);
            bullshits.add(bullshit);
            cursor.moveToNext();
        }
        cursor.close();
        return bullshits;
    }

    private Bullshit cursorToBullshit(Cursor cursor) {
        return null;
    }

    @Override
    public String toString() {
        return "BullshitsDataSource{" +
                "allColumns=" + Arrays.toString(allColumns) +
                '}';
    }
}
