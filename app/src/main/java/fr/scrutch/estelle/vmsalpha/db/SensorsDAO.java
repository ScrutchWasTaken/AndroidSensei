package fr.scrutch.estelle.vmsalpha.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import fr.scrutch.estelle.vmsalpha.model.Sensor;

/**
 * Created by gael on 10/02/16.
 */
public class SensorsDAO {
    /* DB FIELDS */
    private VMSSQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = {
      VMSSQLiteHelper.COLUMN_ID,
            VMSSQLiteHelper.COLUMN_SENSORNAME
    };

    public SensorsDAO(Context context) {
        dbHelper = new VMSSQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Sensor createSensor(String name) {
        ContentValues values = new ContentValues();
        values.put(VMSSQLiteHelper.COLUMN_SENSORNAME, name);

        long insertId = db.insert(VMSSQLiteHelper.TABLE_SENSORS, null, values);

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_SENSORS, allColumns, VMSSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Sensor newSensor = cursorToSensor(cursor);
        cursor.close();
        return newSensor;
    }

    public ArrayList<Sensor> getAllSensors() {
        ArrayList<Sensor> sensors = new ArrayList<>();

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_SENSORS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Sensor sensor = cursorToSensor(cursor);
            sensors.add(sensor);
            cursor.moveToNext();
        }
        cursor.close();
        return sensors;
    }

    private Sensor cursorToSensor(Cursor cursor) {
        Sensor s = new Sensor(cursor.getLong(0),  //id
               cursor.getString(1));             //name
        return s;
    }


}
