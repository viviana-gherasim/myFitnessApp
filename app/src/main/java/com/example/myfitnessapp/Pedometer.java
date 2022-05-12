package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Pedometer extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedometer);
    }

//    public void navigateToStepCounterActivity(View v) {
//        Intent intent = new Intent(this, StepCounterActivity.class);
//        startActivity(intent);
//    }
//
//    public void navigateToStepHistoryActivity(View v) {
//        Intent intent = new Intent(this, StepHistoryActivity.class);
//        startActivity(intent);
//    }
//
//    public void navigateToResultsActivity(View v) {
//        Intent intent = new Intent(this, StepResultsActivity.class);
//        startActivity(intent);
//    }
}
