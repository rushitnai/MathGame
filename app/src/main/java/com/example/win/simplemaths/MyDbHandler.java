package com.example.win.simplemaths;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Struct;

import static com.example.win.simplemaths.R.id.level;

public class MyDbHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME="db_highscore";
    public static String TABLE_NAME ="table_highscore";
    public static  String _ID ="_id";
    public static String COL_ADD ="addition";
    public static String COL_SUB ="substraction";
    public static String COL_MUL ="multiplication";
    public static String COL_DIV ="devision";
    public static int DATABASE_VERSION =1;
    int level ,type;

    int highscore;
    Cursor cursor;

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

   public void setType(int type)
   {
       this.type=type;
   }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+ TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY," + COL_ADD + " TEXT," + COL_SUB + " TEXT,"+
                  COL_MUL +" TEXT," + COL_DIV +" TEXT" + ");" );

        insertdata(1,0,0,0,0, db);
        insertdata(2,0,0,0,0, db);
        insertdata(3,0,0,0,0, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertdata(int i, int addition_data, int substraction_data, int multiplication_data,int devision_data,SQLiteDatabase db)
    {

        ContentValues values= new ContentValues();
        values.put(_ID,i);
        values.put(COL_ADD,addition_data);
        values.put(COL_SUB,substraction_data);
        values.put(COL_MUL,multiplication_data);
        values.put(COL_DIV,devision_data);
        db.insert(TABLE_NAME , null ,values) ;


    }
    public int get_Highscore(){

        String query;
        SQLiteDatabase db ;
        switch (type) {

            case 1:
                query= " SELECT " + (COL_ADD) + " FROM " + (TABLE_NAME) + " WHERE " + (_ID) +"="+ (level) +";";
                break;
            case  2:
                query = " SELECT " + (COL_SUB) + " FROM " + (TABLE_NAME) + " WHERE " + (_ID) +"="+ (level) +";";
                break;
            case 3:
                query = " SELECT " + (COL_MUL) + " FROM " + (TABLE_NAME) + " WHERE " + (_ID) +"="+ (level) +";";
                break;
            case 4:
                query=" SELECT " + (COL_DIV) + " FROM " + (TABLE_NAME) + " WHERE " + (_ID) +"="+ (level) +";";
                break;
                default:
                    query="";
                    break;
        }

        db = this.getWritableDatabase();
        try {


            cursor = db.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            highscore = Integer.parseInt(cursor.getString(0));

        }
        catch (Exception e)
        {
            highscore=111;
        }
        return highscore;
    }

    public void setHighscore(int highscore){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        switch (type)
        {
            case 1:
                values.put(COL_ADD,highscore);
                break;
            case 2:
                values.put(COL_SUB,highscore);
                break;
            case 3:
                values.put(COL_MUL,highscore);
                break;
            case 4:
                values.put(COL_DIV,highscore);
                break;
        }

        db.update(TABLE_NAME, values ," _id = "+level,null);
    }


}
