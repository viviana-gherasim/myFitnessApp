package com.example.myfitnessapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.TimeZone;

public class SearchFoodBarcode extends AppCompatActivity implements View.OnClickListener{

    private ImageView back, scanner;
    private Button add;
    private EditText quantity, barcode;
    private Spinner category;
    protected Food food;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food_by_barcode);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);

        scanner = (ImageView) findViewById(R.id.scan_search_by_barcode);
        scanner.setOnClickListener(this);

        add = (Button) findViewById(R.id.button15);
        add.setOnClickListener(this);

        quantity = (EditText) findViewById(R.id.editText2);

        barcode = (EditText) findViewById(R.id.editText);

        category = (Spinner) findViewById(R.id.spinner);

        String codBareSmech;
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            codBareSmech = bundle.getString("Camera");
            if (codBareSmech != null) {
                barcode.setText(codBareSmech);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                startActivity(new Intent(this, SearchFoodActivity.class));
                break;
            case R.id.scan_search_by_barcode:
                startActivity(new Intent(this, ScannerDiaryActivity.class));
                break;
            case R.id.button15:
                addFoodToDiary();
                break;
        }
    }

    private void addFoodToDiary() {

        DatabaseReference databaseReference, databaseReferenceDiary;
        FirebaseDatabase database;

        String codBare = barcode.getText().toString().trim();
        String cantitate = quantity.getText().toString().trim();
        String categorie = category.getSelectedItem().toString().trim();

        if(codBare.isEmpty()) {
            barcode.setError("Barcode is required");
            barcode.requestFocus();
            return;
        }

        if(cantitate.isEmpty()) {
            quantity.setError("Quantity in grams is required");
            quantity.requestFocus();
            return;
        }

        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference().child("Food");
        databaseReferenceDiary = database.getReference().child("Diary");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot d : snapshot.getChildren()) {
                    food = d.getValue(Food.class);
                    if (food.getBarcode().equals(codBare)) {
                        i = 1;
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                        int day = calendar.get(Calendar.DATE);
                        int month = calendar.get(Calendar.MONTH) + 1;
                        int year = calendar.get(Calendar.YEAR);

                        String strDay = String.valueOf(day);
                        String strMonth = String.valueOf(month);
                        String strYear = String.valueOf(year);
                        String calorii = food.getCalories();
                        String numeMancare = food.getFood();
                        String numeBrand = food.getBrand();

                        float fCalorii = Float.parseFloat(calorii);
                        float fCantitate = Float.parseFloat(cantitate);
                        float coef = (fCantitate / 100);
                        String caloriiJurnal = String.valueOf(coef * fCalorii);

                        float fProteine = Float.parseFloat(food.getProteins());
                        String proteineJurnal = String.valueOf(coef * fProteine);

                        float fGrasimi = Float.parseFloat(food.getFats());
                        String grasimiJurnal = String.valueOf(coef * fGrasimi);

                        float fCarbo = Float.parseFloat(food.getCarbs());
                        String carboJurnal = String.valueOf(coef * fCarbo);

                        String codDeBare = food.getBarcode();

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String userEmail = user.getEmail();
                            FoodDiary foodDiary = new FoodDiary(userEmail, strDay, strMonth, strYear, cantitate, numeMancare, categorie, numeBrand, caloriiJurnal, carboJurnal, grasimiJurnal, proteineJurnal, codBare);
                            databaseReferenceDiary.push().setValue(foodDiary).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SearchFoodBarcode.this, "Food inserted successfully into diary", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), DiaryActivity.class));
                                    } else {
                                        Toast.makeText(SearchFoodBarcode.this, "Food could not be inserted successfully into diary", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                }
                if (i == 0) {
                    Toast.makeText(SearchFoodBarcode.this, "No food with that bar code!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}