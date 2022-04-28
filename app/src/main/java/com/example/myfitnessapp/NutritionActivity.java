package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NutritionActivity extends AppCompatActivity{

    ListView list;
    Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_and_tips);

        String[] titles = getResources().getStringArray(R.array.title_nutrition_and_tips);
        final String[] details = getResources().getStringArray(R.array.details_nutrition_and_tips);


        list = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.nutrition_row, R.id.rowText, titles);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String t = details[position];
                Intent intent = new Intent(NutritionActivity.this, NutritionDetails.class);
                intent.putExtra("story", t);
                startActivity(intent);
            }
        });
    }

    public void nutritionBack(View view) {
        Intent intent = new Intent(NutritionActivity.this, TrainingActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NutritionActivity.this, NutritionDetails.class);
        startActivity(intent);
        finish();
    }

}
