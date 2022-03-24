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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button11:
                startActivity(new Intent(HomePageActivity.this, MainActivity.class ));
            case R.id.button8:
                startActivity(new Intent(HomePageActivity.this, ProfileUser.class));
            case R.id.button9:
                startActivity(new Intent(HomePageActivity.this, AddFood.class));
        }
    }
}
