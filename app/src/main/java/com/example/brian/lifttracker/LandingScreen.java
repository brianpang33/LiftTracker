package com.example.brian.lifttracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);
        Button tracking = findViewById(R.id.button); //button for tracking screen
        tracking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onButtonClicked();
            }
        });

        Button timer = findViewById(R.id.timer_button);
        timer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onTimerClicked();
            }
        });

    }

    private void onButtonClicked(){
        Intent intent = new Intent(this,TrackingScreen.class);
        startActivity(intent);
    }

    private void onTimerClicked(){
        Intent intent = new Intent(this,TimerScreen.class);
        startActivity(intent);
    }
}

