package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logout, diaryButton, profileButton, foodButton, historyButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        logout = (Button) findViewById(R.id.button11);
        logout.setOnClickListener(this);

        profileButton = (Button) findViewById(R.id.button8);
        profileButton.setOnClickListener(this);

        foodButton = (Button) findViewById(R.id.button9);
        foodButton.setOnClickListener(this);

        diaryButton = (Button) findViewById(R.id.button7);
        diaryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button11:
                startActivity(new Intent(HomePageActivity.this, MainActivity.class ));
                break;
            case R.id.button8:
                startActivity(new Intent(HomePageActivity.this, ProfileUser.class));
                break;
            case R.id.button9:
                startActivity(new Intent(HomePageActivity.this, FoodActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(HomePageActivity.this, DiaryActivity.class));
        }
    }
}
