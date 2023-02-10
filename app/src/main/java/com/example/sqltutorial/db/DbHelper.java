package com.example.sqltutorial.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper  extends SQLiteOpenHelper {
    public static String DATABASENAME = "androidadvancesqlite";
    public final static String COMMENTABLE = "comments";

    Context c;



    public DbHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL("CREATE TABLE " + COMMENTABLE+ "("+  "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "comment TEXT NOT NULL,"
                    + "nombre TEXT NOT NULL)");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COMMENTABLE);
        onCreate(db);
    }



}

