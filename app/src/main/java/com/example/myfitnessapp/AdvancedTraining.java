package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdvancedTraining extends AppCompatActivity implements View.OnClickListener{

    private Button back;
    int [] newArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_training);

        back = (Button) findViewById(R.id.backToTrainingActivity);
        back.setOnClickListener(this);

        newArray = new int[] {
                R.id.position1, R.id.position2, R.id.position3, R.id.position4, R.id.position5, R.id.position6, R.id.position7, R.id.position8, R.id.position9, R.id.position10, R.id.position11, R.id.position12, R.id.position13, R.id.position14,  R.id.position15,
        };
    }

    public void ImageButtonClicked(View view) {

        for(int i=0; i< newArray.length; i++) {
            if(view.getId() == newArray[i]) {
                int value = i+1;
                Log.i("First", String.valueOf(value));
                Intent intent = new Intent(AdvancedTraining.this, ExerciseAdvanced.class);
                intent.putExtra("value", String.valueOf(value));
                startActivity(intent);
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToTrainingActivity:
                startActivity(new Intent(AdvancedTraining.this, TrainingActivity.class));
                break;
        }
    }
}
