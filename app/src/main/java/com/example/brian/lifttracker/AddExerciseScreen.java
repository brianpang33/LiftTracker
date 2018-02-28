package com.example.brian.lifttracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brian.lifttracker.database.TaskHelper;


public class AddExerciseScreen extends AppCompatActivity {

    EditText name;
    EditText weight;
    EditText sets;
    EditText reps;
    private TaskHelper taskHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_screen);

        name = findViewById(R.id.addTextName);
        weight = findViewById(R.id.addTextWeight);
        sets = findViewById(R.id.addTextSets);
        reps = findViewById(R.id.addTextReps);

        taskHelper = new TaskHelper(this);

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkName = name.getText().toString();
                String checkWeight = weight.getText().toString();
                String checkSets = sets.getText().toString();
                String checkReps = reps.getText().toString();

                if (checkName.length() != 0 && checkWeight.length() != 0) {
                    AddExercise(checkName, checkWeight, checkSets, checkReps);
                }else{
                    Toast.makeText(AddExerciseScreen.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void AddExercise(String name, String weight, String sets, String reps) {
        boolean addData = taskHelper.insertExercise(name, weight, sets, reps);

        if (addData) {
            Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, TrackingScreen.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "not added", Toast.LENGTH_SHORT).show();
        }
    }


}
