package com.example.myfitnessapp.Training;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfitnessapp.R;

public class AdvancedTraining extends AppCompatActivity implements View.OnClickListener {

    private Button back;
    int [] newArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_training);

        back = (Button) findViewById(R.id.backToTrainingActivity);
        back.setOnClickListener(this);

        newArray = new int[] {
                R.id.poz1, R.id.poz2, R.id.poz3, R.id.poz4, R.id.poz5, R.id.poz6, R.id.poz7, R.id.poz8, R.id.poz9, R.id.poz10, R.id.poz11, R.id.poz12, R.id.poz13, R.id.poz14, R.id.poz15,
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

    }
}
