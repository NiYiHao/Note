package com.example.administrator.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_NOTE = "note";
    private static final String DATABASE_NAME = "Contact";
    private static final String TABLE_NAME = "member";
    private static final int DATABASE_VERSION = 1;
    public DbHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +KEY_ID + " integer PRIMARY KEY autoincrement," + KEY_DATE + "," +KEY_NOTE +");";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
