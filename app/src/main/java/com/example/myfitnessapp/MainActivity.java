package com.example.myfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.button2);
        register.setOnClickListener(this);

        login = findViewById(R.id.button);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.button2:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.button:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}