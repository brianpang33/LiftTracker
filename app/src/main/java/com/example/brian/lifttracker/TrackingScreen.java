package com.example.brian.lifttracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Button add = findViewById(R.id.action_add_exercise);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onAddButtonClicked();
            }
        });

    }

    private void onAddButtonClicked() {
        Intent intent = new Intent(this, AddExerciseScreen.class);
        startActivity(intent);

    }



    private void update() {

        Cursor data = helper.getInfo();
        ArrayList<String> listData = new ArrayList<>();


        while (data.moveToNext()) {
            String name = data.getString(1) + "," +  data.getString(2) + "," + data.getString(3) +
                    ","+ data.getString(4) + "," + data.getString(5);
            listData.add(name);


        }


        ArrayAdapter adapter = new CustomArrayAdapter(this, listData);
        taskListView.setAdapter(adapter);


        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String name = adapterView.getItemAtPosition(i).toString().split(",")[0].trim();
                String weight = adapterView.getItemAtPosition(i).toString().split(",")[1].trim();
                String sets = adapterView.getItemAtPosition(i).toString().split(",")[2].trim();
                String reps = adapterView.getItemAtPosition(i).toString().split(",")[3].trim();
                String comments = adapterView.getItemAtPosition(i).toString().split(",")[4].trim();

              Cursor data = helper.getItemID(sets);

                int itemID = -1;

                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {

                    intent = new Intent(TrackingScreen.this, EditExerciseScreen.class);
                    intent.putExtra("id", itemID);
                    intent.putExtra("name",name);
                    intent.putExtra("weight",weight);
                    intent.putExtra("sets",sets);
                    intent.putExtra("reps",reps);
                    intent.putExtra("comments", comments);

                    startActivity(intent);


                }
            }
        });


    }


}
