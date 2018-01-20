package com.example.brian.lifttracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.brian.lifttracker.database.TaskHelper;

import java.util.ArrayList;

public class TrackingScreen extends AppCompatActivity {

    private TaskHelper helper;
    private ListView taskListView;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_screen);
        taskListView = findViewById(R.id.lift_tracker);
        helper = new TaskHelper(this);
        update();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_lift, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, AddExerciseScreen.class);
        startActivity(intent);
        return true;
    }


    private void update() {

        Cursor data = helper.getInfo();
        ArrayList<String> listData = new ArrayList<>();


        while (data.moveToNext()) {
            String s = "Exercise: " + data.getString(1) + ", " + "Weight: " + data.getString(2) + ", " + "Sets: " + data.getString(3) +
                    ", " + "Reps: " + data.getString(4);
            listData.add(s);

        }


        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.my_text_view, listData);
        taskListView.setAdapter(adapter);

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = adapterView.getItemAtPosition(i).toString().split(",")[0].split(":")[1].trim();

                Cursor data = helper.getItemID(name);

                int itemID = -1;

                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {

                    intent = new Intent(TrackingScreen.this, EditExerciseScreen.class);
                    intent.putExtra("id", itemID);

                    startActivity(intent);

                }
            }
        });


    }


}
