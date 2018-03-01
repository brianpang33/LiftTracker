package com.example.brian.lifttracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by brian on 2017-12-23.
 */

public class TaskHelper extends SQLiteOpenHelper {

    public static final String EXERCISE_TABLE_NAME = "exercise";
    public static final String EXERCISE_COLUMN_ID = "id";
    public static final String EXERCISE_COLUMN_NAME = "name";
    public static final String EXERCISE_COLUMN_WEIGHT = "weight";
    public static final String EXERCISE_COLUMN_SETS = "sets";
    public static final String EXERCISE_COLUMN_REPETITIONS = "reps";
    public static final String EXERCISE_COLUMN_COMMENTS = "comments";

    public TaskHelper(Context context) {
        super(context, EXERCISE_TABLE_NAME, null, 16);
    }

    @Override

    public void onCreate(SQLiteDatabase d) {
        String table = "create table exercise" + "(id integer primary key autoincrement, name text, weight text, sets text, reps text, comments text)";

        d.execSQL(table);

    }

    public boolean insertExercise(String name, String weight, String sets, String reps, String comments) {
        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_COLUMN_NAME, name);
        contentValues.put(EXERCISE_COLUMN_WEIGHT, weight);
        contentValues.put(EXERCISE_COLUMN_SETS, sets);
        contentValues.put(EXERCISE_COLUMN_REPETITIONS, reps);
        contentValues.put(EXERCISE_COLUMN_COMMENTS,comments);

        d.insert("exercise", null, contentValues);
        return true;
    }

    public Cursor getInfo() {
        SQLiteDatabase d = this.getReadableDatabase();
        Cursor c = d.rawQuery("SELECT * FROM " + EXERCISE_TABLE_NAME, null);
        return c;
    }


    @Override

    public void onUpgrade(SQLiteDatabase d, int oldVersion, int newVersion) {
        d.execSQL("DROP TABLE IF EXISTS exercise");
        onCreate(d);
    }

    public Cursor getItemID(String sets) {
        SQLiteDatabase d = this.getWritableDatabase();
        Cursor data = d.rawQuery("SELECT " + EXERCISE_COLUMN_ID + " FROM " + EXERCISE_TABLE_NAME +
                " WHERE " + EXERCISE_COLUMN_SETS + " = '" + sets + "'", null);

        return data;
    }


    public void updateExercise(Integer id, String name, String weight, String sets, String reps, String comments) {
        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("weight", weight);
        contentValues.put("sets", sets);
        contentValues.put("reps", reps);
        contentValues.put("comments", comments);

        d.update("exercise", contentValues, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void deleteExercise(Integer id) {
        SQLiteDatabase d = this.getWritableDatabase();
        d.delete("exercise", "id = ? ", new String[]{Integer.toString(id)});
    }


}
