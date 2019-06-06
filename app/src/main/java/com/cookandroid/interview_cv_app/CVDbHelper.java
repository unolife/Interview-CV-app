package com.cookandroid.interview_cv_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CVDbHelper extends SQLiteOpenHelper {
    private static CVDbHelper sInstance;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "coverletter.db";
    private static final String SQL_CREATE_ENTRIES =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                    CV.CVEntry.TABLE_NAME,
                    CV.CVEntry._ID,
                    CV.CVEntry.COLUMN_NAME_QUESTION,
                    CV.CVEntry.COLUMN_NAME_ANSWERS);

    public static CVDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CVDbHelper(context);
        }
        return sInstance;
    }

    private CVDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
