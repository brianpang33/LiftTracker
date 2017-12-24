package com.example.brian.lifttracker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class TrackingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_screen);
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

    public void showDialog(){

        final EditText taskEditText = new EditText(this);
        AlertDialog popup = new AlertDialog.Builder(this)
                .setTitle("Add a new exercise")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
        popup.show();
    }



}
