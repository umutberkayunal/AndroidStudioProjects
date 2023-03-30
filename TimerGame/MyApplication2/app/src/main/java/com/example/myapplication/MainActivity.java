package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 60000;
    private TextView timerCounter;
    private Button topButton;
    private Button bottomButton;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private boolean topbuttonactive = false;
    private boolean bottombuttonactive = true;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topButton = findViewById(R.id.button_top);

        bottomButton = findViewById(R.id.button_bottom);

        timerCounter = findViewById(R.id.counter_middle);

        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning) {
                    pauseTimer();
                    bottomButton.setText("START");
                    bottombuttonactive = false;
                } else{
                    startTimer();
                    bottomButton.setText("PAUSE");
                    bottombuttonactive = true;
                }
            }
        });

        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning) {
                    pauseTimer();
                    topButton.setText("START");
                    topbuttonactive = false;
                } else{
                    startTimer();
                    topButton.setText("PAUSE");
                    topbuttonactive = true;
                }

            }
        });
        updateCountDownText();
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 100) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                if(bottombuttonactive = true) {
                    bottomButton.setText("YOU LOSE");
                }
                if(topbuttonactive = true){
                    topButton.setText("YOU LOSE");
                }
            }
        }.start();
        mTimerRunning = true;
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        if(bottombuttonactive) {
            topButton.setVisibility(View.VISIBLE);
            bottomButton.setVisibility(View.INVISIBLE);
        } else {
            topButton.setVisibility(View.INVISIBLE);
            bottomButton.setVisibility(View.VISIBLE);
        }

    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        timerCounter.setText(timeLeftFormatted);
    }
}