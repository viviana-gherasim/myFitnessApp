package com.example.myfitnessapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    EditText dateFormat;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_date);

        back = (ImageView) findViewById(R.id.backButtonHistory);
        back.setOnClickListener(this);

        final java.util.Calendar calendar = java.util.Calendar.getInstance();

        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH);
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

        dateFormat = findViewById(R.id.dateFormat);
        dateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Calendar.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                dateFormat.setText(date);
            }
        };

        Button searchFoodHistory= findViewById(R.id.buttonSearchHistory);

        searchFoodHistory.setOnClickListener(view -> {
            Intent intent = new Intent(this, ViewHistory.class);
            intent.putExtra("date", dateFormat.getText().toString());
            startActivity(intent);
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backButtonHistory:
                startActivity(new Intent(this, HomePageActivity.class));
                break;
        }
    }
}
