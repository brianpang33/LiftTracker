package com.example.brian.lifttracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by brian on 2017-12-23.
 */

public class TaskHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "myDataBase";
    public static final String EXERCISE_TABLE_NAME = "exercise";
    public static final String EXERCISE_COLUMN_ID = "id";
    public static final String EXERCISE_COLUMN_NAME = "name";
    public static final String EXERCISE_COLUMN_WEIGHT = "weight";

    public TaskHelper(Context context){
        super(context, EXERCISE_TABLE_NAME,null,2);
    }

    @Override

    public void onCreate(SQLiteDatabase d){
        String table = "CREATE TABLE " + EXERCISE_TABLE_NAME  + " (id integer primary key, name TEXT,weight TEXT)";

        d.execSQL(table);

    }

    public boolean insertExercise(String name, String weight){
        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_COLUMN_NAME,name);
        contentValues.put(EXERCISE_COLUMN_WEIGHT,weight);
        d.insert("exercise",null,contentValues);
        return true;
    }

    public Cursor getInfo(){
        SQLiteDatabase d = this.getReadableDatabase();
        Cursor c = d.rawQuery("SELECT * FROM " + EXERCISE_TABLE_NAME, null);
        return c;
    }


    @Override

    public void onUpgrade(SQLiteDatabase d, int oldVersion, int newVersion){
        d.execSQL("DROP TABLE IF EXISTS exercise");
        onCreate(d);
    }

    public Cursor getItemID(String name){
        SQLiteDatabase d = this.getWritableDatabase();
        Cursor data = d.rawQuery("SELECT "+ EXERCISE_COLUMN_ID + " FROM " + EXERCISE_TABLE_NAME +
                " WHERE " + EXERCISE_COLUMN_NAME + " = '" + name + "'",null);

        return data;
    }

    public void updateName(String newName,int id,String oldName){
        SQLiteDatabase d = this.getWritableDatabase();
        d.execSQL("UPDATE "+ EXERCISE_TABLE_NAME + " SET " + EXERCISE_COLUMN_NAME +
        " = '" + newName + "' WHERE " + EXERCISE_COLUMN_ID + " = '" + id + "'" + " AND " + EXERCISE_COLUMN_NAME
        + " = '" + oldName + "'");
    }

    public void deleteName(int id, String name){
        SQLiteDatabase d = this.getWritableDatabase();
        d.execSQL("DELETE FROM " + EXERCISE_TABLE_NAME + " WHERE " + EXERCISE_COLUMN_ID + " = '" + id + "'" +
        " AND " + EXERCISE_COLUMN_NAME + " = '" + name + "'");
    }

    public void updateWeight(String newWeight,int id,String oldWeight){
        SQLiteDatabase d = this.getWritableDatabase();
        d.execSQL("UPDATE "+ EXERCISE_TABLE_NAME + " SET " + EXERCISE_COLUMN_WEIGHT +
                " = '" + newWeight + "' WHERE " + EXERCISE_COLUMN_ID + " = '" + id + "'" + " AND " + EXERCISE_COLUMN_WEIGHT
                + " = '" + oldWeight + "'");
    }

    public void deleteWeight(int id, String weight){
        SQLiteDatabase d = this.getWritableDatabase();
        d.execSQL("DELETE FROM " + EXERCISE_TABLE_NAME + " WHERE " + EXERCISE_COLUMN_ID + " = '" + id + "'" +
                " AND " + EXERCISE_COLUMN_WEIGHT + " = '" + weight + "'");
    }



}
