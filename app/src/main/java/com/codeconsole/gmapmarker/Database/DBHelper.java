package com.codeconsole.gmapmarker.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 22-02-2017.
 */

class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "track_assist_db";
    private static int DB_VERSION = 1;

    //table name
    public static String TABLE_LABEL = "track_assist_table_label";
    public static String TABLE_MARKER = "track_assist_table_marker";

    //column names
    //label
    public static String LABEL_ID = "id";
    public static String LABEL_NAME = "name";
    public static String LABEL_DATE = "date";
    //marker
    public static String MARKER_ID = "id";
    public static String MARKER_LABEL_ID = "label_id";
    public static String MARKER_ALPHA = "alpha";
    public static String MARKER_ANCHOR_U = "anchor_u";
    public static String MARKER_ANCHOR_V = "anchor_v";
    public static String MARKER_DRAGGABLE = "draggable";
    public static String MARKER_FLAT = "flat";
    public static String MARKER_INFO_WINDOW_ANCHOR = "info_window_anchor";
    public static String MARKER_ROTATION = "rotation";
    public static String MARKER_SNIPPET = "snippet";
    public static String MARKER_TITLE = "title";
    public static String MARKER_VISIBLE = "visible";
    public static String MARKER_Z_INDEX = "z_index";
    public static String MARKER_LATITUDE = "latitude";
    public static String MARKER_LONGITUDE = "longitude";
    //public static String MARKER_LOCATION_ID = "location_id";
    public static String MARKER_ICON = "icon";

    //query for creating table
    private static String CREATE_LABEL_TABLE = "CREATE TABLE " + TABLE_LABEL + "(" + LABEL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + LABEL_NAME + " TEXT," + LABEL_DATE + " TEXT)";

    private static String CREATE_MARKER_TABLE = "CREATE TABLE " + TABLE_MARKER + "(" + MARKER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + MARKER_LABEL_ID + " INTEGER," + MARKER_ALPHA
            + " REAL," + MARKER_ANCHOR_U + " REAL," + MARKER_ANCHOR_V + " REAL," + MARKER_DRAGGABLE + " INTEGER," + MARKER_FLAT
            + " INTEGER," + MARKER_INFO_WINDOW_ANCHOR + " REAL," /*+ MARKER_LOCATION_ID + " INTEGER,"*/
            + MARKER_ROTATION + " REAL," + MARKER_SNIPPET + " REAL," + MARKER_TITLE + " TEXT,"
            + MARKER_LATITUDE + " TEXT," + MARKER_LONGITUDE + " TEXT," + MARKER_VISIBLE + " INTEGER," +
            MARKER_Z_INDEX + " REAL," + MARKER_ICON + " TEXT)";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LABEL_TABLE);
        db.execSQL(CREATE_MARKER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKER);

        // create new tables
        onCreate(db);
    }
}