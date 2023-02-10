package com.example.sqltutorial.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBcomments extends DbHelper{
    Context mContext;
    public DBcomments(@Nullable Context context) {
        super(context);
        this.mContext=context;
    }
    public long insertaComment(String nombre,String comment){
        long id = 0;
        try {
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("comment",comment);
        values.put("nombre",nombre);
        id = db.insert(COMMENTABLE, null, values);
        }
       catch (Exception e){
           Log.println(Log.ASSERT,"error","no se ha podido conectar a la tabla");
       }

        return id;
    }

    @SuppressLint("Range")
    public String SelectComment(String CommentName){
        String comment="";
        try {
            DbHelper dbHelper = new DbHelper(mContext);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            try {
                Cursor c = db.rawQuery("SELECT * FROM comments WHERE nombre = '"+CommentName.trim()+"'", null);
                c.moveToNext();
                comment = c.getString(c.getColumnIndex("comment"));
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        catch (Exception e){
            Log.println(Log.ASSERT,"error","no se ha podido conectar a a la tabla");
        }
        System.out.println(comment);
    return comment;

    }
}
