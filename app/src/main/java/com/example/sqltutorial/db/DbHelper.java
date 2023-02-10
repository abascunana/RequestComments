package com.example.sqltutorial.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper  extends SQLiteOpenHelper {
    public static String DATABASENAME = "androidadvancesqlite";
    public final static String COMMENTABLE = "comments";

    Context c;



    public DbHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
        c = context;
    }
    public List<String> getAllLabels(){
        String selectQuery = "SELECT  nombre FROM " + COMMENTABLE;
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        try {


            // Select All Query


            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(0));//adding 2nd column data
                } while (cursor.moveToNext());
            }
        }
        catch (Exception e){

        }

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
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

