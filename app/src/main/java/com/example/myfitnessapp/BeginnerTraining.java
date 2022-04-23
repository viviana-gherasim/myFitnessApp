package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BeginnerTraining extends AppCompatActivity implements View.OnClickListener{

    private Button back;
    int [] newArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beginner_training);

        back = (Button) findViewById(R.id.backToTrainingActivity);
        back.setOnClickListener(this);

        newArray = new int[] {
                R.id.pose1, R.id.pose2, R.id.pose3, R.id.pose4, R.id.pose5, R.id.pose6, R.id.pose7, R.id.pose8, R.id.pose9, R.id.pose10,
        };
    }

    public void ImageButtonClicked(View view) {

        for(int i=0; i< newArray.length; i++) {
            if(view.getId() == newArray[i]) {
                int value = i+1;
                Log.i("First", String.valueOf(value));
                Intent intent = new Intent(BeginnerTraining.this, ExerciseBeginner.class);
                intent.putExtra("value", String.valueOf(value));
                startActivity(intent);
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToTrainingActivity:
                startActivity(new Intent(BeginnerTraining.this, TrainingActivity.class));
                break;
        }
    }
}
