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
public class CampaignsDAO {
    /* DB FIELDS */
    private VMSSQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = {
      VMSSQLiteHelper.COLUMN_ID,
            VMSSQLiteHelper.COLUMN_CAMPAIGNNAME,
            VMSSQLiteHelper.COLUMN_STARTDATE,
            VMSSQLiteHelper.COLUMN_ENDDATE,
            VMSSQLiteHelper.COLUMN_SENSORNAME,
            VMSSQLiteHelper.COLUMN_FAV
    };

    public CampaignsDAO(Context context) {
        dbHelper = new VMSSQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Campaign createCampaign(String name, long startDate, long endDate, String sensorName, boolean isFav) {
        ContentValues values = new ContentValues();
        values.put(VMSSQLiteHelper.COLUMN_CAMPAIGNNAME, name);
        values.put(VMSSQLiteHelper.COLUMN_STARTDATE, startDate);
        values.put(VMSSQLiteHelper.COLUMN_ENDDATE, endDate);
        values.put(VMSSQLiteHelper.COLUMN_SENSORNAME, sensorName);
        values.put(VMSSQLiteHelper.COLUMN_FAV, isFav);

        long insertId = db.insert(VMSSQLiteHelper.TABLE_CAMPAIGNS, null, values);

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_CAMPAIGNS, allColumns, VMSSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Campaign newCampaign = cursorToCampaign(cursor);
        cursor.close();
        return newCampaign;
    }

    public Campaign createCampaign(String name, long startDate, String sensorName, boolean isFav) {
        ContentValues values = new ContentValues();
        values.put(VMSSQLiteHelper.COLUMN_CAMPAIGNNAME, name);
        values.put(VMSSQLiteHelper.COLUMN_STARTDATE, startDate);
        values.put(VMSSQLiteHelper.COLUMN_SENSORNAME, sensorName);
        values.put(VMSSQLiteHelper.COLUMN_FAV, isFav);

        long insertId = db.insert(VMSSQLiteHelper.TABLE_CAMPAIGNS, null, values);

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_CAMPAIGNS, allColumns, VMSSQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Campaign newCampaign = cursorToCampaign(cursor);
        cursor.close();
        return newCampaign;
    }

    /**
     * Return unique campaign name to be shown in a list
     * @return
     */
    public ArrayList<String> getAllCampaigns() {
        ArrayList<Campaign>campaigns = new ArrayList<>();
        ArrayList<String>uniqueCampaigns = new ArrayList<>();

        Cursor cursor = db.query(VMSSQLiteHelper.TABLE_CAMPAIGNS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Campaign campaign = cursorToCampaign(cursor);
            campaigns.add(campaign);
            cursor.moveToNext();
        }
        cursor.close();

        for(int i=0 ; i<campaigns.size() ; i++) {
            String currentCampaignName = campaigns.get(i).getName();
            if(!uniqueCampaigns.contains(currentCampaignName)){
                uniqueCampaigns.add(currentCampaignName);
            }
        }
        return uniqueCampaigns;
    }

    private Campaign cursorToCampaign(Cursor cursor) {
        Campaign s = new Campaign(cursor.getLong(0),    //id
                cursor.getString(1),                    //name
                cursor.getLong(2),                      //startDate
                cursor.getLong(3),                      //endDate
                cursor.getString(4),                    //sensor
                Boolean.getBoolean(cursor.getString(5)) //fav
                );
        return s;
    }
}
