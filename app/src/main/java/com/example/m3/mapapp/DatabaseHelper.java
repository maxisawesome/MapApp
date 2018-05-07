package com.example.m3.mapapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by celiachen on 3/7/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Bulletins.db";
    // name the table
    public static final String TABLE_NAME = "bulletin_table";
    // create the column names
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "TITLE";
    public static final String COL_4 = "LOCATION";
    public static final String COL_5 = "CONTENT";
    public static final String COL_6 = "TIME";
    public static final String COL_7 = "TYPE";

    

    // I want to save this table into a file named student.db
    // within the file, you have the table, within the table, you have the columns

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        // calling the constructor from the parent class
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating the table with the columns and their data type
        // SQL statement
        sqLiteDatabase.execSQL("create table " + TABLE_NAME
                + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, TITLE TEXT, LOCATION TEXT, " +
                "CONTENT TEXT, TIME TEXT, TYPE INTEGER)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // i -> old version, i1 -> new version
        // if the table exists, you delete the table and then create a new one
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // insert row
    // write a method that returns true if the data has been successfully inserted into the table
    public boolean insertData(String username, String title, String location, String content, String time, int type){

        // get the database you want to insert into
        SQLiteDatabase db = this.getWritableDatabase();

        // get the content values
        ContentValues cv = new ContentValues();
        // put the value into each column
        cv.put(COL_2, username);
        cv.put(COL_3, title);
        cv.put(COL_4, location);
        cv.put(COL_5, content);
        cv.put(COL_6, time);
        cv.put(COL_7, type);

        // call insert method
        long result = db.insert(TABLE_NAME, null, cv);
        // if it is correct, return true
        return (result != -1);

        // else false

    }

    // update row
    public boolean updateData(String id, String username, String title,
                              String content, String location, String time, int type){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, id);
        cv.put(COL_2, username);
        cv.put(COL_3, title);
        cv.put(COL_4, location);
        cv.put(COL_5, content);
        cv.put(COL_6, time);
        cv.put(COL_7, type);



        db.update(TABLE_NAME, cv, "ID = ?", new String[]{id});
        return true;
    }
    // show rows

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // delete rows
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

}