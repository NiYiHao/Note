package com.example.administrator.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {
    public static final String KEY_ID = "_id";
    public static final String KEY_DATA = "data";
    public static final String KEY_NOTE = "note";
    private static final String DATABASE_NAME = "Contact";
    private static final String TABLE_NAME = "member";
    private final Context mCtx;
    private DbHelper mDbHelper;
    private SQLiteDatabase mdb;

    public DbAdapter(Context mCtx) {
        this.mCtx = mCtx;
        open();

    }
    public void open(){
        mDbHelper = new DbHelper( mCtx );
        mdb = mDbHelper.getWritableDatabase();
    }
    public void close(){
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }
}
