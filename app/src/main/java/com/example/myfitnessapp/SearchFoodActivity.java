package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SearchFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView home, search, scan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food);

        home = (ImageView) findViewById(R.id.backToHome);
        home.setOnClickListener(this);

        search = (ImageView) findViewById(R.id.search_loupe);
        search.setOnClickListener(this);

        scan = (ImageView) findViewById(R.id.scan_code);
        scan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.backToHome:
                startActivity(new Intent(SearchFoodActivity.this, DiaryActivity.class));
                break;
            case R.id.search_loupe:
                startActivity(new Intent(SearchFoodActivity.this, SearchFoodName.class));
                break;
            case R.id.scan_code:
                startActivity(new Intent(SearchFoodActivity.this, SearchFoodBarcode.class));
        }
    }
}
