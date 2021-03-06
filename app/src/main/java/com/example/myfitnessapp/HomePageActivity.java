package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout profileHome, foodHome, diaryHome, calendarHome, health;
    private Button logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(this);

        profileHome = (RelativeLayout) findViewById(R.id.profileHome);
        profileHome.setOnClickListener(this);

        foodHome = (RelativeLayout) findViewById(R.id.foodHome);
        foodHome.setOnClickListener(this);

        diaryHome = (RelativeLayout) findViewById(R.id.diaryHome);
        diaryHome.setOnClickListener(this);

        calendarHome = (RelativeLayout) findViewById(R.id.calendarHome);
        calendarHome.setOnClickListener(this);

        health = (RelativeLayout) findViewById(R.id.health);
        health.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.logout:
                startActivity(new Intent(HomePageActivity.this, MainActivity.class ));
                break;

            case R.id.profileHome:
                startActivity(new Intent(HomePageActivity.this, ProfileUser.class));
                break;

            case R.id.foodHome:
                startActivity(new Intent(HomePageActivity.this, FoodActivity.class));
                break;

            case R.id.diaryHome:
                startActivity(new Intent(HomePageActivity.this, DiaryActivity.class));
                break;

            case R.id.calendarHome:
                startActivity(new Intent(HomePageActivity.this, Calendar.class));
                break;

            case R.id.health:
                startActivity(new Intent(HomePageActivity.this, WeightCheck.class));
                break;

        }
    }
}
