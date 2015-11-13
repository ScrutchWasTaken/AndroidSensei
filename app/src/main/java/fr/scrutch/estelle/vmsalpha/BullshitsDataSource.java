package fr.scrutch.estelle.vmsalpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gael on 23/10/15.
 * BullshitsDataSource represent the DB
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

    public Bullshit createBullshit(long time) {
        ContentValues values = new ContentValues();
        values.put(MeasureDatabase.COLUMN_NAME, time);  // WARNING
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

    /**
     * Let us display a summary of the perf
     * @return min, max, and average write time
     */
    public String getPerf() {
        System.out.println("Initializing performance...");

        long min = 100000, max = 0, avg = 0;
        long current;

        System.out.println("Printing all the bullshit");
        System.out.println(this.getAllTheBullshit().toString());

        System.out.println("Going throw calculation...");
        for(int i=0 ; i< this.getAllTheBullshit().size() ; i++){
            current = this.getAllTheBullshit().get(i).time;
            if(current > max)
                max = current;
            if(current < min )
                min = current;
            avg = avg + current;
        }
        System.out.println("Summarizing...");
        avg = avg/this.getAllTheBullshit().size();
        System.out.println("Printing...");
        return "Write time / min : " + min + " ms / max : " + " ms / avg : " + avg + " ms." ;
    }
}
