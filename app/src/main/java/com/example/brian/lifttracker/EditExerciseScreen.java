package com.example.brian.lifttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brian.lifttracker.database.TaskHelper;

public class EditExerciseScreen extends AppCompatActivity {

    private Button delete, edit;
    private EditText editName, editWeight;

    TaskHelper helper;

    private String name;
    private String weight;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise_screen);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        editName =findViewById(R.id.editTextName);
        editWeight = findViewById(R.id.editTextWeight);
        helper = new TaskHelper(this);

        Intent intent = getIntent();

        id = intent.getIntExtra("id",-1);
        name = intent.getStringExtra("name");
        weight = intent.getStringExtra("weight");

        editName.setText(name);
        editWeight.setText(weight);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editName.getText().toString();
                String item1 = editWeight.getText().toString();
                if(!item.equals("")){

                    helper.updateName(item,id,name);
                    helper.updateWeight(item1,id,weight);
                    Intent intent = new Intent(EditExerciseScreen.this,TrackingScreen.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(EditExerciseScreen.this,"must fill in all fields",Toast.LENGTH_SHORT);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                helper.deleteName(id,name);
                helper.deleteWeight(id,weight);
                editName.setText("");
                Toast.makeText(EditExerciseScreen.this,"deleted",Toast.LENGTH_SHORT);
                Intent intent = new Intent(EditExerciseScreen.this,TrackingScreen.class);
                startActivity(intent);
            }
        });

    }

}
