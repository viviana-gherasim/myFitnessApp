package com.example.myfitnessapp;

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

import com.example.myfitnessapp.Scanner.ScannerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity implements View.OnClickListener {

    private EditText food, brand, barcode, calories, proteins, carbs, fats;
    private ImageView back, scanner;
    private Button add;
    private DatabaseReference database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);

        back = (ImageView) findViewById(R.id.backButton);
        back.setOnClickListener(this);

        scanner = (ImageView) findViewById(R.id.imageView);
        scanner.setOnClickListener(this);

        add = (Button) findViewById(R.id.button13);
        add.setOnClickListener(this);

        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Food");

        food = findViewById(R.id.editTextTextFoodName);
        brand = findViewById(R.id.editTextTextFoodName2);
        barcode = findViewById(R.id.editTextNumber12);
        calories = findViewById(R.id.editTextNumber8);
        proteins = findViewById(R.id.editTextNumber9);
        carbs = findViewById(R.id.editTextNumber10);
        fats = findViewById(R.id.editTextNumber11);

        String codBare;
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {

            codBare = bundle.getString("Camera");
            if(codBare!=null) {

                barcode.setText(codBare);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button13:
                addFoodToDatabase();
                break;

            case R.id.imageView:
                startActivity(new Intent(this, ScannerActivity.class));
                break;

            case R.id.backButton:
                startActivity(new Intent(this, FoodActivity.class));
                break;
        }
    }

    private void addFoodToDatabase() {

        String product = food.getText().toString().trim();
        String brandName = brand.getText().toString().trim();
        String code = barcode.getText().toString().trim();
        String calorii = calories.getText().toString().trim();
        String proteine = proteins.getText().toString().trim();
        String carbo = carbs.getText().toString().trim();
        String grasimi = fats.getText().toString().trim();

        if(product.isEmpty())
        {
            food.setError("Please introduce the name!");
            food.requestFocus();
            return;
        }

        if(brandName.isEmpty())
        {
            brand.setError("Please introduce the brand name!");
            brand.requestFocus();
            return;
        }

        if(code.isEmpty())
        {
            barcode.setError("Please introduce the barcode!");
            barcode.requestFocus();
            return;
        }

        if(calorii.isEmpty())
        {
            calories.setError("Please introduce the calories!");
            calories.requestFocus();
            return;
        }

        if(proteine.isEmpty())
        {
            proteins.setError("Please introduce the proteins!");
            proteins.requestFocus();
            return;
        }

        if(carbo.isEmpty())
        {
            carbs.setError("Please introduce the carbs!");
            carbs.requestFocus();
            return;
        }

        if(grasimi.isEmpty())
        {
            fats.setError("Please introduce the fats!");
            fats.requestFocus();
            return;
        }

        Food foods = new Food(product, brandName, code, calorii, proteine, carbo,grasimi);

        database.push().setValue(foods).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AddFood.this, "Food inserted successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                }
                else {

                    Toast.makeText(AddFood.this, "Insertion failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
