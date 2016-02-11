package fr.scrutch.estelle.vmsalpha.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by gael on 04/02/16.
 * Contains the SQLite DB model
 */
public class VMSSQLiteHelper extends SQLiteOpenHelper {

    /* SHARED */
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "vms.db";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CAMPAIGNNAME = "campaign_name";
    public static final String COLUMN_SENSORNAME = "sensor_name";

    /* TABLE MEASURES */
    public static final String TABLE_MEASURES = "measures";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_VALUE1 = "value1";
    public static final String COLUMN_VALUE2 = "value2";
    public static final String COLUMN_VALUE3 = "value3";

    /* TABLE CAMPAIGNS */
    public static final String TABLE_CAMPAIGNS = "campaigns";
    public static final String COLUMN_STARTDATE = "startDate";
    public static final String COLUMN_ENDDATE = "endDate";
    public static final String COLUMN_FAV = "fav";

    /* TABLE SENSORS */
    public static final String TABLE_SENSORS = "sensors";

    /* TABLES CREATION */
    private static final String CREATE_SENSORS_TABLE = "create table "
            + TABLE_SENSORS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_SENSORNAME + " text unique not null);";

    private static final String CREATE_MEASURES_TABLE = "create table "
            + TABLE_MEASURES + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_SENSORNAME + " text, "
            + COLUMN_CAMPAIGNNAME + " text, "
            + COLUMN_TIME + " long, "
            + COLUMN_VALUE1 + " float, "
            + COLUMN_VALUE2 + " float, "
            + COLUMN_VALUE3 + " float);";

    private static final String CREATE_CAMPAIGN_TABLE = "create table "
            + TABLE_CAMPAIGNS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CAMPAIGNNAME + " text, "
            + COLUMN_SENSORNAME + " text, "
            + COLUMN_STARTDATE + " long, "
            + COLUMN_ENDDATE + " long, "
            + COLUMN_FAV + " boolean);";

    public VMSSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_CAMPAIGN_TABLE);
        database.execSQL(CREATE_MEASURES_TABLE);
        database.execSQL(CREATE_SENSORS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(VMSSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASURES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAMPAIGNS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENSORS);
        onCreate(db);
    }

}

