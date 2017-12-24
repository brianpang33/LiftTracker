package com.example.brian.lifttracker;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brian.lifttracker.database.TaskConstants;
import com.example.brian.lifttracker.database.TaskHelper;

import java.util.ArrayList;

public class TrackingScreen extends AppCompatActivity {

    private TaskHelper helper;
    private ListView taskListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_screen);
        update();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_lift,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_add_exercise:
                showDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean showDialog(){

        final EditText taskEditText = new EditText(this);
        AlertDialog popup = new AlertDialog.Builder(this)
                .setTitle("Add a new exercise")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        String task = String.valueOf(taskEditText.getText());
                        SQLiteDatabase d = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(TaskConstants.TaskEntry.TITLE, task);
                        d.insertWithOnConflict(TaskConstants.TaskEntry.TABLE, null, values,SQLiteDatabase.CONFLICT_REPLACE);
                        d.close();
                        update();

                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
        popup.show();
        return true;


    }

    private void update(){
        ArrayList<String> tasks = new ArrayList<>();
        helper = new TaskHelper(this);
        taskListView = (ListView) findViewById(R.id.lift_tracker);
        SQLiteDatabase d = helper.getReadableDatabase();
        Cursor cursor = d.query(TaskConstants.TaskEntry.TABLE,new String[]{TaskConstants.TaskEntry._ID,
                TaskConstants.TaskEntry.TITLE},null, null, null, null, null);

        while (cursor.moveToNext()){
            int i = cursor.getColumnIndex(TaskConstants.TaskEntry.TITLE);
            tasks.add(cursor.getString(i));
        }

        if(adapter == null){
            adapter = new ArrayAdapter<>(this,R.layout.lift_tracker,R.id.task_title, tasks);
            taskListView.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(tasks);
            adapter.notifyDataSetChanged();
        }

        cursor.close();
        d.close();
    }

    public void deleteTask(View view){
        View v = (View) view.getParent();
        TextView textView = (TextView) v.findViewById(R.id.task_title);
        String task = String.valueOf(textView.getText());
        SQLiteDatabase d = helper.getWritableDatabase();
        d.delete(TaskConstants.TaskEntry.TABLE,TaskConstants.TaskEntry.TITLE + " = ?",new String[]{task});
        d.close();
        update();
    }

}
