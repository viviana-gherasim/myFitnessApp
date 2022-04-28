package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NutritionDetails extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_tips_details);

        textView = findViewById(R.id.txt);
        String details = getIntent().getStringExtra("story");
        textView.setText(details);
    }

    public void goBack(View view) {
        Intent intent = new Intent(NutritionDetails.this, NutritionActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NutritionDetails.this, NutritionActivity.class);
        startActivity(intent);
        finish();
    }
}
