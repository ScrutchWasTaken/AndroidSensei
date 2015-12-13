package fr.scrutch.estelle.vmsalpha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gael on 13/11/15.
 */
public class MeasuresDataSource {
    // Database fields
    private SQLiteDatabase db;
    private DBSQLiteHelper dbHelper;
    private String[] allColumns = {
            DBSQLiteHelper.COLUMN_ID,
            DBSQLiteHelper.COLUMN_MEASURE
    };

    public MeasuresDataSource(Context context) {
        dbHelper = new DBSQLiteHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Measure createMeasure(long date) {
        // Used to store a set of values that the ContentResolver can process.
        ContentValues values = new ContentValues();
        values.put(DBSQLiteHelper.COLUMN_MEASURE, date);

        long insertID = db.insert(DBSQLiteHelper.TABLE_NAME, null, values);

        Cursor cursor = db.query(DBSQLiteHelper.TABLE_NAME,
                allColumns, DBSQLiteHelper.COLUMN_ID + " = " + insertID, null, null, null, null);

        cursor.moveToFirst();
        Measure newMeasure = cursorToMeasure(cursor);
        cursor.close();

        return newMeasure;
    }

    public List<Measure> getAllMeasures() {
        List<Measure> measures = new ArrayList<Measure>();

        Cursor cursor = db.query(DBSQLiteHelper.TABLE_NAME, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Measure measure = cursorToMeasure(cursor);
            measures.add(measure);
            cursor.moveToNext();
        }
        cursor.close();
        return measures;

    }

    private Measure cursorToMeasure(Cursor cursor) {
        Measure measure = new Measure();
        // Get the content of the current line
        measure.setId(cursor.getLong(0));
        measure.setTime(cursor.getLong(1));

        return measure;
    }
}
