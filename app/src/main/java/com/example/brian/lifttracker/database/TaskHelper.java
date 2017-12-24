package com.example.brian.lifttracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by brian on 2017-12-23.
 */

public class TaskHelper extends SQLiteOpenHelper{

    public TaskHelper(Context context){
        super(context, TaskConstants.DATABASE_NAME,null,TaskConstants.DATABASE_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase d){
        String table = "CREATE TABLE "  + TaskConstants.TaskEntry.TABLE + " (" +
                TaskConstants.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskConstants.TaskEntry.TITLE + " TEXT NOT NULL);";

        d.execSQL(table);

    }

    @Override

    public void onUpgrade(SQLiteDatabase d, int oldVersion, int newVersion){
        d.execSQL("DROP TABLE IF EXISTS " + TaskConstants.TaskEntry.TABLE);
        onCreate(d);
    }



}
