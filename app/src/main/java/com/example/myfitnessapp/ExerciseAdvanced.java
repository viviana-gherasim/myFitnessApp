package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseAdvanced extends AppCompatActivity {

    String buttonValue;
    Button startButton;
    private CountDownTimer countDownTimer;
    TextView mTextView;
    private long mTimeLeftInMills;
    private boolean mTimeRun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_advanced);

        Intent intent = getIntent();
        buttonValue = intent.getStringExtra("value");

        int intvalue = Integer.valueOf(buttonValue);

        switch (intvalue) {

            case 1:
                setContentView(R.layout.activity_pilates);
                break;

            case 2:
                setContentView(R.layout.activity_diamond_kicks);
                break;

            case 3:
                setContentView(R.layout.activity_deadlift_upright);
                break;

            case 4:
                setContentView(R.layout.activity_flutter_kick);
                break;

            case 5:
                setContentView(R.layout.activity_bicycle_crunch);
                break;

            case 6:
                setContentView(R.layout.activity_inner_thigh_lifts);
                break;

            case 7:
                setContentView(R.layout.activity_climbers);
                break;

            case 8:
                setContentView(R.layout.activity_side_leg_lifts);
                break;

            case 9:
                setContentView(R.layout.activity_squat_curl);
                break;

            case 10:
                setContentView(R.layout.activity_v_sit);
                break;

            case 11:
                setContentView(R.layout.activity_wall_bridge);
                break;

            case 12:
                setContentView(R.layout.activity_reverse_crunch);
                break;

            case 13:
                setContentView(R.layout.activity_booty_squeeze);
                break;

            case 14:
                setContentView(R.layout.activity_lunge_back);
                break;

            case 15:
                setContentView(R.layout.activity_plank_hipdips);
                break;

        }

        startButton = (Button) findViewById(R.id.startButton);
        mTextView = findViewById(R.id.timeButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimeRun){
                    stopTimer();

                }
                else {
                    startTimer();
                }
            }
        });
    }

    private void stopTimer() {
        countDownTimer.cancel();
        mTimeRun = false;
        startButton.setText("START");
    }

    private void startTimer() {
        final CharSequence value1 = mTextView.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);

        final int number = Integer.valueOf(num2) * 60 + Integer.valueOf(num3);
        mTimeLeftInMills = number * 1000;

        countDownTimer = new CountDownTimer(mTimeLeftInMills, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMills = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                int newValue = Integer.valueOf(buttonValue)+1;
                if(newValue <= 7) {
                    Intent intent = new Intent(ExerciseAdvanced.this, ExerciseAdvanced.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newValue));
                    startActivity(intent);
                }
                else {
                    newValue = 1;
                    Intent intent = new Intent(ExerciseAdvanced.this, ExerciseAdvanced.class);
                    intent.addFlags(Intent .FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newValue));
                    startActivity(intent);
                }
            }
        }.start();
        startButton.setText("Pause");
        mTimeRun = true;

    }

    private void updateTimer() {
        int minutes = (int) mTimeLeftInMills/60000;
        int seconds = (int) mTimeLeftInMills%60000 /1000;

        String timeLeftText = " ";
        if(minutes<10)
            timeLeftText="0";
        timeLeftText = timeLeftText + minutes + " ";
        if(seconds<10)
            timeLeftText+= "0";
        timeLeftText+= seconds;
        mTextView.setText(timeLeftText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
