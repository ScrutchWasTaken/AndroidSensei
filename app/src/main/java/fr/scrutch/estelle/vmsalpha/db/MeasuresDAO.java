package fr.scrutch.estelle.vmsalpha.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.lang.reflect.Array;
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
            VMSSQLiteHelper.COLUMN_SENSORNAME,
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

    public Measure createMeasure(String sensorName, String campaignName, long time, float v1, float v2, float v3) {
        ContentValues values = new ContentValues();
        values.put(VMSSQLiteHelper.COLUMN_SENSORNAME, sensorName);
        values.put(VMSSQLiteHelper.COLUMN_CAMPAIGNNAME, campaignName);
        values.put(VMSSQLiteHelper.COLUMN_TIME, time);
        values.put(VMSSQLiteHelper.COLUMN_VALUE1, v1);
        values.put(VMSSQLiteHelper.COLUMN_VALUE2, v2);
        values.put(VMSSQLiteHelper.COLUMN_VALUE3, v3);

        long insertId = db.insert(VMSSQLiteHelper.TABLE_MEASURES, null, values);

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_MEASURES, allColumns, VMSSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Measure newMeasure = cursorToMeasure(cursor);
        cursor.close();
        return newMeasure;
    }

    public Measure createMeasure(Measure measure) {
        ContentValues values = new ContentValues();
        values.put(VMSSQLiteHelper.COLUMN_SENSORNAME, measure.getSensorName());
        values.put(VMSSQLiteHelper.COLUMN_CAMPAIGNNAME, measure.getCampaignName());
        values.put(VMSSQLiteHelper.COLUMN_TIME, measure.getTime());
        values.put(VMSSQLiteHelper.COLUMN_VALUE1, measure.getValue1());
        values.put(VMSSQLiteHelper.COLUMN_VALUE2, measure.getValue2());
        values.put(VMSSQLiteHelper.COLUMN_VALUE3, measure.getValue3());

        long insertId = db.insert(VMSSQLiteHelper.TABLE_MEASURES, null, values);

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_MEASURES, allColumns, VMSSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Measure newMeasure = cursorToMeasure(cursor);
        cursor.close();
        return newMeasure;
    }

    /**
     * Add measures in the DB 100 by 100 this is faster
     * than adding the measure one by one.
     * Principle : we prepare a request :
     *      "insert into TABLE ( column1, col2, ...)
     *      values ( , , ), ( , , )..."
     * @param measures
     */
    public void createMeasures(ArrayList<Measure> measures) {
        StringBuffer request = new StringBuffer();

        int size = measures.size();
        if(size==0) {
            return;
        }
        int bcl = size/100;
        int left = size%100;
        System.out.println("Adding " + size + " measures...");
        for(int i=0; i<bcl+1 ; i++) {
            if(i!=bcl) {
                /** Normal loop * Begin of the request **/
                request.append("insert into " + VMSSQLiteHelper.TABLE_MEASURES);
                request.append(" (" + VMSSQLiteHelper.COLUMN_SENSORNAME + ", "
                + VMSSQLiteHelper.COLUMN_CAMPAIGNNAME + ", "
                + VMSSQLiteHelper.COLUMN_TIME + ", "
                + VMSSQLiteHelper.COLUMN_VALUE1 + ", "
                + VMSSQLiteHelper.COLUMN_VALUE2 + ", "
                + VMSSQLiteHelper.COLUMN_VALUE3 + ") values ");

                /** VALUES of the request **/
                for(int j=0 ; j<99; j++) {
                    Measure m = measures.get((i+1)*j);
                    request.append("(" + m.getSensorName() + ", ");
                    request.append(m.getCampaignName() + ", ");
                    request.append(m.getTime() + ", ");
                    request.append(m.getValue1() + ", ");
                    request.append(m.getValue2() + ", ");
                    request.append(m.getValue3() + "), ");
                }
                /** Conclusion of the request **/
                Measure m = measures.get((i+1)*100);
                request.append("(" + m.getSensorName() + ", ");
                request.append(m.getCampaignName() + ", ");
                request.append(m.getTime() + ", ");
                request.append(m.getValue1() + ", ");
                request.append(m.getValue2() + ", ");
                request.append(m.getValue3() + ") ");
            } else {
                /** Normal loop complete ones
                 * Begin of the request **/
                request.append("insert into " + VMSSQLiteHelper.TABLE_MEASURES);
                request.append(" (" + VMSSQLiteHelper.COLUMN_SENSORNAME + ", "
                        + VMSSQLiteHelper.COLUMN_CAMPAIGNNAME + ", "
                        + VMSSQLiteHelper.COLUMN_TIME + ", "
                        + VMSSQLiteHelper.COLUMN_VALUE1 + ", "
                        + VMSSQLiteHelper.COLUMN_VALUE2 + ", "
                        + VMSSQLiteHelper.COLUMN_VALUE3 + ") values ");
                /** VALUES of the request for the last VALUES **/
                for(int j=0 ; j<left-1; j++) {
                    Measure m = measures.get((bcl*100)+ j );
                    request.append("(" + m.getSensorName() + ", ");
                    request.append(m.getCampaignName() + ", ");
                    request.append(m.getTime() + ", ");
                    request.append(m.getValue1() + ", ");
                    request.append(m.getValue2() + ", ");
                    request.append(m.getValue3() + "), ");
                }
                /** Conclusion of the request **/
                Measure m = measures.get(size-1);
                request.append("(" + m.getSensorName() + ", ");
                request.append(m.getCampaignName() + ", ");
                request.append(m.getTime() + ", ");
                request.append(m.getValue1() + ", ");
                request.append(m.getValue2() + ", ");
                request.append(m.getValue3() + ") ");
            }
        }
        System.out.println("Finished to add the " + size + " measures...");
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

    public ArrayList<Measure> getMeasuresForCampaign(String campaignName) {
        ArrayList<Measure> measures = new ArrayList<>();

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_MEASURES, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Measure measure = cursorToMeasure(cursor);
            if(measure.getCampaignName().equals(campaignName)) {
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
        Measure s = new Measure(cursor.getInt(0),  //id
                cursor.getString(1),                //name
                cursor.getString(2),                //campain Name
                cursor.getLong(3),                  //time
                cursor.getFloat(4),                  //value 1
                cursor.getFloat(5),                  //value 2
                cursor.getFloat(6)                   //value 3
                );
        return s;
    }
}
