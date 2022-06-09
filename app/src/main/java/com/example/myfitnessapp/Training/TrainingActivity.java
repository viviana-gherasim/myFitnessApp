package com.example.myfitnessapp.Training;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfitnessapp.HomePageActivity;
import com.example.myfitnessapp.R;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener{

    Button button1, button2, button3, back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_ideas);

        button1 = (Button) findViewById(R.id.startTraining1);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.startTraining2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.startTraining3);
        button3.setOnClickListener(this);

        back = (Button) findViewById(R.id.backToHome);
        back.setOnClickListener(this);
    }

    public void before18(View view) {

        Intent intent = new Intent(TrainingActivity.this, BeginnerTraining.class);
        startActivity(intent);

    }

    public void during18(View view) {

        Intent intent = new Intent(TrainingActivity.this, MediumTraining.class);
        startActivity(intent);

    }

    public void after18(View view) {

        Intent intent = new Intent(TrainingActivity.this, AdvancedTraining.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTraining1:
                startActivity(new Intent(TrainingActivity.this, BeginnerTraining.class));
                break;

            case R.id.startTraining2:
                startActivity(new Intent(TrainingActivity.this, MediumTraining.class));
                break;

            case R.id.startTraining3:
                startActivity(new Intent(TrainingActivity.this, AdvancedTraining.class));
                break;

            case R.id.backToHome:
                startActivity(new Intent(TrainingActivity.this, HomePageActivity.class));
                break;
        }
    }
}
