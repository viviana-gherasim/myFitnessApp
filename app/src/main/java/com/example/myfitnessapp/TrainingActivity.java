package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener{

    Button button1, button2, back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_ideas);

        button1 = (Button) findViewById(R.id.startTraining1);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.startTraining2);
        button2.setOnClickListener(this);

        back = (Button) findViewById(R.id.backToHome);
        back.setOnClickListener(this);
    }

    public void before18(View view) {

        Intent intent = new Intent(TrainingActivity.this, BeginnerTraining.class);
        startActivity(intent);

    }

    public void after18(View view) {

        Intent intent = new Intent(TrainingActivity.this, AdvancedTraining.class);
        startActivity(intent);

    }

    public void nutrition(View view) {

        Intent intent = new Intent(TrainingActivity.this, Nutrition.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTraining1:
                startActivity(new Intent(TrainingActivity.this, BeginnerTraining.class));
                break;

            case R.id.startTraining2:
                startActivity(new Intent(TrainingActivity.this, AdvancedTraining.class));
                break;

            case R.id.backToHome:
                startActivity(new Intent(TrainingActivity.this, HomePageActivity.class));
                break;
        }
    }
}
