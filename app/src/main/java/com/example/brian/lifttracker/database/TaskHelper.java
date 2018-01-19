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
    public static final String EXERCISE_COLUMN_SET = "sets";
    public static final String EXERCISE_COLUMN_REPETITION = "rep";

    public TaskHelper(Context context){
        super(context, EXERCISE_TABLE_NAME,null,3);
    }

    @Override

    public void onCreate(SQLiteDatabase d){
        String table = "create table exercise" + "(id integer primary key autoincrement, name text, weight text, sets text, rep text)";

        d.execSQL(table);

    }

    public boolean insertExercise(String name, String weight, String set, String rep){
        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_COLUMN_NAME,name);
        contentValues.put(EXERCISE_COLUMN_WEIGHT,weight);
        contentValues.put(EXERCISE_COLUMN_SET,set);
        contentValues.put(EXERCISE_COLUMN_REPETITION,rep);

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

    public void updateExercise(Integer id, String name, String weight, String set, String rep){
        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("weight", weight);
        contentValues.put("set", set);
        contentValues.put("rep",rep);

        d.update("exercise", contentValues,"id = ? ",new String[]{Integer.toString(id)});
    }

    public void deleteExercise(Integer id){
        SQLiteDatabase d = this.getWritableDatabase();
        d.delete("exercise","id = ? ", new String[]{Integer.toString(id)});
    }





}
