package com.example.brian.lifttracker;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TimerScreen extends AppCompatActivity {

    private TextView timeDisplay;
    private EditText seconds;
    private Button start;
    private Button cancel;
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);
        start = findViewById(R.id.start);
        cancel = findViewById(R.id.cancel);
        seconds = findViewById(R.id.enter_seconds);
        timeDisplay = findViewById(R.id.time);
        timeDisplay.setText("0");


        start.setOnClickListener(new View.OnClickListener() { //listener for start button

            @Override
            public void onClick(View view) {

                if (countDownTimer == null) {
                    String getSeconds = seconds.getText().toString();
                    try {

                        Integer numOfSeconds = Integer.parseInt(getSeconds);

                        if (numOfSeconds > 0) {
                            start(numOfSeconds);

                        }

                    } catch (NumberFormatException e) {
                        timeDisplay.setText("Invalid Number!");
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() { //listener for cancel button

            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }

    private void start(int seconds) {

        timeDisplay.setText("");

        countDownTimer = new CountDownTimer(seconds * 1000, 1000) {

            @Override
            public void onTick(long millisecondUntilFinished) {
                timeDisplay.setText("" + millisecondUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                timeDisplay.setText("Rest Over!");
                countDownTimer = null;

            }
        }.start();

    }

    private void cancel() {

        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
            timeDisplay.setText("0");
        }

    }


}
