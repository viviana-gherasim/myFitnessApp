package com.example.myfitnessapp.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfitnessapp.MainActivity;
import com.example.myfitnessapp.R;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout cityHome, nutritionistHome;
    private Button logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_layout);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(this);

        cityHome = (RelativeLayout) findViewById(R.id.addCity);
        cityHome.setOnClickListener(this);

        nutritionistHome = (RelativeLayout) findViewById(R.id.addNutritionist);
        nutritionistHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addCity:
                startActivity(new Intent(ManagerActivity.this, AddCity.class));
                break;
            case R.id.addNutritionist:
                startActivity(new Intent(ManagerActivity.this, AddNutritionist.class));
                break;
            case R.id.logout:
                startActivity(new Intent(ManagerActivity.this, MainActivity.class ));
                break;
        }
    }
}
