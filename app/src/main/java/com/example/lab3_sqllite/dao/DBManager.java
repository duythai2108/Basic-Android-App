package com.example.lab3_sqllite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {

    public static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "classmanagementdb";
    public static final String CLASSROOM_TABLE_NAME = "tblclassroom";
    public static final String CLASSROOM_ID = "id";
    public static final String CLASSROOM_NAME = "name";
    public static final String CLASSROOM_TEACHER = "teacher";
    public static final String CLASSROOM_AMOUNT = "amount";

    public DBManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("DBManager","DBManager : ok !");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String sqlQuery = "CREATE TABLE " + CLASSROOM_TABLE_NAME + " ("
                    + CLASSROOM_ID + " TEXT PRIMARY KEY, "
                    + CLASSROOM_NAME + " TEXT, "
                    + CLASSROOM_TEACHER + " TEXT, "
                    + CLASSROOM_AMOUNT + " TEXT" + ");";
            db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CLASSROOM_TABLE_NAME);
            onCreate(db);
    }
}
