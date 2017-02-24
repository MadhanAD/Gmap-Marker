package com.codeconsole.gmapmarker.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.codeconsole.gmapmarker.Bean.LabelBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22-02-2017.
 */

public class LabelTable {
    private static final String TAG = "LabelTable";
    private DBHelper helper;

    public LabelTable(Context context) {
        helper = new DBHelper(context);
    }

    public void insertLabel(LabelBean bean) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.LABEL_NAME, bean.getName());
        values.put(DBHelper.LABEL_DATE, bean.getDate());
        db.insert(DBHelper.TABLE_LABEL, null, values);
        db.close();
    }

    @SuppressLint("Recycle")
    public List<LabelBean> getAllLabel() {

        String query = "SELECT * FROM " + DBHelper.TABLE_LABEL;
        SQLiteDatabase db = helper.getReadableDatabase();

        List<LabelBean> labelBeanList = new ArrayList<>();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                LabelBean bean = new LabelBean();
                bean.setLabelId(cursor.getInt(cursor.getColumnIndex(DBHelper.LABEL_ID)));
                bean.setName(cursor.getString(cursor.getColumnIndex(DBHelper.LABEL_NAME)));
                bean.setDate(cursor.getString(cursor.getColumnIndex(DBHelper.LABEL_DATE)));

                labelBeanList.add(bean);
            } while (cursor.moveToNext());
            db.close();
            return labelBeanList;
        } else {
            db.close();
            return null;
        }
    }

    public int getCount() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM " + DBHelper.TABLE_LABEL;
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    public void updateLabel(LabelBean bean) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBHelper.LABEL_ID, bean.getLabelId());
        values.put(DBHelper.LABEL_NAME, bean.getName());
        values.put(DBHelper.LABEL_DATE, bean.getDate());

        db.update(DBHelper.TABLE_LABEL, values, DBHelper.LABEL_ID + "=?",
                new String[]{String.valueOf(bean.getLabelId())});
        db.close();
    }

    public void updateLabelList(List<LabelBean> labelBeanList) {
        SQLiteDatabase db = helper.getWritableDatabase();

        for (LabelBean bean : labelBeanList) {
            ContentValues values = new ContentValues();

            values.put(DBHelper.LABEL_ID, bean.getLabelId());
            values.put(DBHelper.LABEL_NAME, bean.getName());
            values.put(DBHelper.LABEL_DATE, bean.getDate());

            db.update(DBHelper.TABLE_LABEL, values, DBHelper.LABEL_ID + "=?",
                    new String[]{String.valueOf(bean.getLabelId())});
        }
        db.close();
    }

    public void deleteLabel(LabelBean bean) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(DBHelper.TABLE_LABEL, DBHelper.LABEL_ID + "=?",
                new String[]{String.valueOf(bean.getLabelId())});
        db.close();
    }
}
