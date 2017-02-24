package com.codeconsole.gmapmarker.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codeconsole.gmapmarker.Bean.MarkerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23-02-2017.
 */

public class MarkerTable {
    private DBHelper helper;

    public MarkerTable(Context context) {
        helper = new DBHelper(context);
    }

    public void insertMarker(MarkerBean bean) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.MARKER_ALPHA, bean.getAlpha());
        values.put(DBHelper.MARKER_ANCHOR_U, bean.getAnchorU());
        values.put(DBHelper.MARKER_ANCHOR_V, bean.getAnchorV());
        values.put(DBHelper.MARKER_DRAGGABLE, bean.isDraggable() ? 1 : 0);
        values.put(DBHelper.MARKER_FLAT, bean.isFlat() ? 1 : 0);
        values.put(DBHelper.MARKER_INFO_WINDOW_ANCHOR, bean.getInfoWindowAnchor());
        //values.put(DBHelper.MARKER_LOCATION_ID, bean.getLocationId());
        values.put(DBHelper.MARKER_LABEL_ID, bean.getLabelId());
        values.put(DBHelper.MARKER_ROTATION, bean.getRotation());
        values.put(DBHelper.MARKER_SNIPPET, bean.getSnippet());
        values.put(DBHelper.MARKER_TITLE, bean.getTitle());
        values.put(DBHelper.MARKER_VISIBLE, bean.isVisible() ? 1 : 0);
        values.put(DBHelper.MARKER_Z_INDEX, bean.getzIndex());
        values.put(DBHelper.MARKER_ICON, bean.getIconPath());
        values.put(DBHelper.MARKER_LATITUDE, bean.getLatitude());
        values.put(DBHelper.MARKER_LONGITUDE, bean.getLongitude());

        db.insert(DBHelper.TABLE_MARKER, null, values);
        db.close();
    }

    public void updateMarker(MarkerBean bean) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.MARKER_ALPHA, bean.getAlpha());
        values.put(DBHelper.MARKER_ANCHOR_U, bean.getAnchorU());
        values.put(DBHelper.MARKER_ANCHOR_V, bean.getAnchorV());
        values.put(DBHelper.MARKER_DRAGGABLE, bean.isDraggable() ? 1 : 0);
        values.put(DBHelper.MARKER_FLAT, bean.isFlat() ? 1 : 0);
        values.put(DBHelper.MARKER_INFO_WINDOW_ANCHOR, bean.getInfoWindowAnchor());
        //values.put(DBHelper.MARKER_LOCATION_ID, bean.getLocationId());
        values.put(DBHelper.MARKER_LABEL_ID, bean.getLabelId());
        values.put(DBHelper.MARKER_ROTATION, bean.getRotation());
        values.put(DBHelper.MARKER_SNIPPET, bean.getSnippet());
        values.put(DBHelper.MARKER_TITLE, bean.getTitle());
        values.put(DBHelper.MARKER_VISIBLE, bean.isVisible() ? 1 : 0);
        values.put(DBHelper.MARKER_Z_INDEX, bean.getzIndex());
        values.put(DBHelper.MARKER_ICON, bean.getIconPath());
        values.put(DBHelper.MARKER_LATITUDE, bean.getLatitude());
        values.put(DBHelper.MARKER_LONGITUDE, bean.getLongitude());


        db.update(DBHelper.TABLE_MARKER, values, DBHelper.MARKER_ID + "=?",
                new String[]{String.valueOf(bean.getId())});
        db.close();
    }

    public void updateMarkerList(List<MarkerBean> list) {
        SQLiteDatabase db = helper.getWritableDatabase();

        for (MarkerBean bean : list) {
            ContentValues values = new ContentValues();
            //values.put(DBHelper.MARKER_ALPHA, bean.getAlpha());
            values.put(DBHelper.MARKER_ALPHA, 1.0f);
            values.put(DBHelper.MARKER_ANCHOR_U, bean.getAnchorU());
            values.put(DBHelper.MARKER_ANCHOR_V, bean.getAnchorV());
            //values.put(DBHelper.MARKER_DRAGGABLE, bean.isDraggable() ? 1 : 0);
            values.put(DBHelper.MARKER_DRAGGABLE, 1);
            //values.put(DBHelper.MARKER_FLAT, bean.isFlat() ? 1 : 0);
            values.put(DBHelper.MARKER_FLAT, 1);
            values.put(DBHelper.MARKER_INFO_WINDOW_ANCHOR, bean.getInfoWindowAnchor());
            //values.put(DBHelper.MARKER_LOCATION_ID, bean.getLocationId());
            values.put(DBHelper.MARKER_LABEL_ID, bean.getLabelId());
            //values.put(DBHelper.MARKER_ROTATION, bean.getRotation());
            values.put(DBHelper.MARKER_ROTATION, 90.0);
            values.put(DBHelper.MARKER_SNIPPET, bean.getSnippet());
            values.put(DBHelper.MARKER_TITLE, bean.getTitle());
            //values.put(DBHelper.MARKER_VISIBLE, bean.isVisible() ? 1 : 0);
            values.put(DBHelper.MARKER_VISIBLE, 1);
            //values.put(DBHelper.MARKER_Z_INDEX, bean.getzIndex());
            values.put(DBHelper.MARKER_Z_INDEX, 0);
            values.put(DBHelper.MARKER_ICON, bean.getIconPath());
            values.put(DBHelper.MARKER_LATITUDE, bean.getLatitude());
            values.put(DBHelper.MARKER_LONGITUDE, bean.getLongitude());

            db.update(DBHelper.TABLE_MARKER, values, DBHelper.MARKER_ID + "=?",
                    new String[]{String.valueOf(bean.getId())});
        }
        db.close();
    }

    @SuppressLint("Recycle")
    public List<MarkerBean> getAllMarker() {
        List<MarkerBean> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT * FROM " + DBHelper.TABLE_MARKER;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //valid data
            do {
                MarkerBean bean = new MarkerBean();

                bean.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.MARKER_ID)));
                bean.setLabelId(cursor.getInt(cursor.getColumnIndex(DBHelper.MARKER_LABEL_ID)));
                bean.setAlpha(cursor.getFloat(cursor.getColumnIndex(DBHelper.MARKER_ALPHA)));
                bean.setAnchorU(cursor.getFloat(cursor.getColumnIndex(DBHelper.MARKER_ANCHOR_U)));
                bean.setAnchorV(cursor.getFloat(cursor.getColumnIndex(DBHelper.MARKER_ANCHOR_V)));
                if (cursor.getInt(cursor.getColumnIndex(DBHelper.MARKER_DRAGGABLE)) > 0) {
                    //true
                    bean.setDraggable(true);
                } else {
                    bean.setDraggable(false);
                }

                if (cursor.getInt(cursor.getColumnIndex(DBHelper.MARKER_FLAT)) > 0) {
                    //true
                    bean.setFlat(true);
                } else {
                    bean.setFlat(false);
                }

                bean.setInfoWindowAnchor(cursor.getFloat(cursor.getColumnIndex(DBHelper.MARKER_INFO_WINDOW_ANCHOR)));
                bean.setRotation(cursor.getFloat(cursor.getColumnIndex(DBHelper.MARKER_ROTATION)));
                bean.setSnippet(cursor.getString(cursor.getColumnIndex(DBHelper.MARKER_SNIPPET)));
                bean.setTitle(cursor.getString(cursor.getColumnIndex(DBHelper.MARKER_TITLE)));

                if (cursor.getInt(cursor.getColumnIndex(DBHelper.MARKER_VISIBLE)) > 0) {
                    //true
                    bean.setVisible(true);
                } else {
                    bean.setVisible(false);
                }

                bean.setzIndex(cursor.getFloat(cursor.getColumnIndex(DBHelper.MARKER_Z_INDEX)));
                //bean.setLocationId(cursor.getInt(cursor.getColumnIndex(DBHelper.MARKER_LOCATION_ID)));
                bean.setIconPath(cursor.getString(cursor.getColumnIndex(DBHelper.MARKER_ICON)));
                bean.setLatitude(cursor.getString(cursor.getColumnIndex(DBHelper.MARKER_LATITUDE)));
                bean.setLongitude(cursor.getString(cursor.getColumnIndex(DBHelper.MARKER_LONGITUDE)));

                list.add(bean);
            } while (cursor.moveToNext());

            db.close();
            return list;
        } else {
            db.close();
            return null;
        }
    }

    //delete operation
    public void deleteMarker(MarkerBean bean) {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(DBHelper.TABLE_MARKER, DBHelper.MARKER_ID + "=?",
                new String[]{String.valueOf(bean.getId())});
        db.close();
    }

    @SuppressLint("Recycle")
    public int getCount() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = "SELECT * FROM " + DBHelper.TABLE_MARKER;
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
}