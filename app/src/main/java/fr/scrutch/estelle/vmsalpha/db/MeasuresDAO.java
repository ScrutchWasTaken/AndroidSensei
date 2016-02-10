package fr.scrutch.estelle.vmsalpha.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import fr.scrutch.estelle.vmsalpha.model.Campaign;
import fr.scrutch.estelle.vmsalpha.model.Measure;
import fr.scrutch.estelle.vmsalpha.model.Sensor;

/**
 * Created by gael on 10/02/16.
 */
public class MeasuresDAO {
    /* DB FIELDS */
    private VMSSQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = {
      VMSSQLiteHelper.COLUMN_ID,
            VMSSQLiteHelper.COLUMN_NAME,
            VMSSQLiteHelper.COLUMN_CAMPAIGNNAME,
            VMSSQLiteHelper.COLUMN_TIME,
            VMSSQLiteHelper.COLUMN_VALUE1,
            VMSSQLiteHelper.COLUMN_VALUE2,
            VMSSQLiteHelper.COLUMN_VALUE3
    };

    public MeasuresDAO(Context context) {
        dbHelper = new VMSSQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Measure createMeasure(String name) {
        ContentValues values = new ContentValues();
        values.put(VMSSQLiteHelper.COLUMN_NAME, name);

        long insertId = db.insert(VMSSQLiteHelper.TABLE_MEASURES, null, values);

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_MEASURES, allColumns, VMSSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Measure newMeasure = cursorToMeasure(cursor);
        cursor.close();
        return newMeasure;
    }

    public ArrayList<Measure> getAllMeasures() {
        ArrayList<Measure> Measures = new ArrayList<>();

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_MEASURES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Measure Measure = cursorToMeasure(cursor);
            Measures.add(Measure);
            cursor.moveToNext();
        }
        cursor.close();
        return Measures;
    }

    public ArrayList<Measure> getMeasuresForCampaign(Campaign campaign) {
        ArrayList<Measure> measures = new ArrayList<>();

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_MEASURES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Measure measure = cursorToMeasure(cursor);
            if(measure.getCampaignName().equals(campaign.getName())) {
                measures.add(measure);
            }
            cursor.moveToNext();
        }
        cursor.close();
        return measures;
    }

    public ArrayList<Measure> getMeasuresForSensor(Sensor sensor) {
        ArrayList<Measure> measures = new ArrayList<>();

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_MEASURES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Measure measure = cursorToMeasure(cursor);
            if(measure.getCampaignName().equals(sensor.getName())) {
                measures.add(measure);
            }
            cursor.moveToNext();
        }
        cursor.close();
        return measures;
    }

    public ArrayList<Measure> getMeasuresFor(Campaign campaign, Sensor sensor) {
        ArrayList<Measure> measures = new ArrayList<>();

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_MEASURES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Measure measure = cursorToMeasure(cursor);
            if(measure.getCampaignName().equals(campaign.getName())) {
                if(measure.getSensorName().equals(sensor.getName())) {
                    measures.add(measure);
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        return measures;
    }

    private Measure cursorToMeasure(Cursor cursor) {
        Measure s = new Measure(cursor.getLong(0),  //id
                cursor.getString(1),                //name
                cursor.getString(2),                //campain Name
                cursor.getLong(3),                  //time
                cursor.getLong(4),                  //value 1
                cursor.getLong(5),                  //value 2
                cursor.getLong(6)                   //value 3
                );
        return s;
    }
}
