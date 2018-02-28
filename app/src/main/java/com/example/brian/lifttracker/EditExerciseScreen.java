package com.example.brian.lifttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brian.lifttracker.database.TaskHelper;

public class EditExerciseScreen extends AppCompatActivity {

    TaskHelper helper;
    private Button delete, edit;
    private EditText editName, editWeight, editSets, editReps;
    private String name, weight, sets, reps;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise_screen);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        editName = findViewById(R.id.editTextName);
        editWeight = findViewById(R.id.editTextWeight);
        editSets = findViewById(R.id.editTextSets);
        editReps = findViewById(R.id.editTextReps);
        helper = new TaskHelper(this);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", -1);
        name = intent.getStringExtra("name");
        weight = intent.getStringExtra("weight");
        sets = intent.getStringExtra("sets");
        reps = intent.getStringExtra("reps");

        editName.setText(name);
        editWeight.setText(weight);
        editSets.setText(sets);
        editReps.setText(reps);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editName.getText().toString();
                String item1 = editWeight.getText().toString();
                String item2 = editSets.getText().toString();
                String item3 = editReps.getText().toString();

                if (!item.equals("") && !item1.equals("") && !item2.equals("") && !item3.equals("")) {

                    helper.updateExercise(id, item, item1, item2, item3);
                    Intent intent = new Intent(EditExerciseScreen.this, TrackingScreen.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(EditExerciseScreen.this, "please fill in all fields", Toast.LENGTH_SHORT);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.deleteExercise(id);
                editName.setText("");
                editReps.setText("");
                editWeight.setText("");
                editSets.setText("");
                Toast.makeText(EditExerciseScreen.this, "deleted", Toast.LENGTH_SHORT);
                Intent intent = new Intent(EditExerciseScreen.this, TrackingScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
