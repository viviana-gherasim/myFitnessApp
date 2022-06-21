package com.example.myfitnessapp.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfitnessapp.HomePageActivity;
import com.example.myfitnessapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCity extends AppCompatActivity implements View.OnClickListener {

    private Button add;
    private EditText name;
    private ImageView back;
    private DatabaseReference database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);

        add = (Button) findViewById(R.id.buttonAddCity);
        add.setOnClickListener(this);

        back = (ImageView) findViewById(R.id.backButton);
        back.setOnClickListener(this);

        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("City");

        name = (EditText) findViewById(R.id.editTextTextCityName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddCity:
                addCityToDatabase();

            case R.id.backButton:
                startActivity(new Intent(this, ManagerActivity.class));
                break;
        }
    }

    private void addCityToDatabase() {
        String cityName = name.getText().toString().trim();

        if(cityName.isEmpty()) {
            name.setError("City name missing!");
            name.requestFocus();
            return;
        }

        City cities = new City(cityName);

        database.push().setValue(cities).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(AddCity.this, "City inserted successfully into database", Toast.LENGTH_LONG).show();
                    //startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                }
                else{
                    Toast.makeText(AddCity.this, "Insertion city failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
