package com.example.administrator.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DbAdapter {
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_NOTE = "note";
    private static final String DATABASE_NAME = "Contact";
    private static final String TABLE_NAME = "member";
    private final Context mCtx;
    private DbHelper mDbHelper;
    private SQLiteDatabase mdb;
    private ContentValues values;

    public DbAdapter(Context mCtx) {
        this.mCtx = mCtx;
        open();

    }

    public void open() {
        mDbHelper = new DbHelper(mCtx);
        mdb = mDbHelper.getWritableDatabase();
        Log.i("db=",mdb.toString());
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public boolean deleteContacts(int id) {
        String[] args = {Integer.toString(id)};
        mdb.delete(TABLE_NAME, "_id= ?", args);
        return true;
    }

    public Cursor listContacts() {
        Cursor mCursor = mdb.query(TABLE_NAME, new String[]{KEY_ID, KEY_DATE, KEY_NOTE},
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor queryById(int ID) {
        Cursor c = null;
        c = mdb.query(true, TABLE_NAME, new String[]{KEY_ID, KEY_DATE, KEY_NOTE}, KEY_ID + " == " + ID,
                null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long createContacts(String data, String note) {
        try {
            values = new ContentValues();
            values.put(KEY_DATE, data);
            values.put(KEY_NOTE, note);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(mCtx, "新增成功", Toast.LENGTH_SHORT).show();
        }
        return mdb.insert(TABLE_NAME, null, values);
    }

    public long updateContacts(int ID, String data, String note) {
        try {
            values = new ContentValues();
            values.put(KEY_DATE, data);
            values.put(KEY_NOTE, note);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(mCtx, "更新成功" , Toast.LENGTH_SHORT).show();
        }
        return mdb.update(TABLE_NAME, values ,"_id="+ID,null);
    }
}
