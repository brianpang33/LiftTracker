package com.example.brian.lifttracker.database;
import android.provider.BaseColumns;

/**
 * Created by brian on 2017-12-23.
 */

public class TaskConstants {

    public static final String DATABASE_NAME = "myDataBase";
    public static final int DATABASE_VERSION = 1;


    public class TaskEntry implements BaseColumns{
        public static final String TABLE = "exercises";
        public static final String TITLE = "title";
    }
}
