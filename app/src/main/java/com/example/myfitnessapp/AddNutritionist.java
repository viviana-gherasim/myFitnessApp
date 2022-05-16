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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddNutritionist extends AppCompatActivity implements View.OnClickListener{

    private Button add;
    private EditText name, email, address, phone, cityN;
    private ImageView back;
    protected City city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_nutritionist);

        add = (Button) findViewById(R.id.buttonAddNutritionist);
        add.setOnClickListener(this);

        back = (ImageView) findViewById(R.id.backButton);
        back.setOnClickListener(this);


        name = (EditText) findViewById(R.id.editTextNutritionistName);
        email = (EditText) findViewById(R.id.editTextNutritionistEmail);
        address = (EditText) findViewById(R.id.editTextNutritionistAddress);
        phone = (EditText) findViewById(R.id.editTextNutritionistPhone);
        cityN = (EditText) findViewById(R.id.editTextNutritionistCity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddNutritionist:
                addNutritionistToDatabase();
            case R.id.backButton:
                startActivity(new Intent(this, HomePageActivity.class));
                break;
        }
    }

    private void addNutritionistToDatabase() {

        DatabaseReference databaseReference;
        FirebaseDatabase database;

        String nutritionistName = name.getText().toString().trim();
        String nutritionistEmail = email.getText().toString().trim();
        String nutritionistAddress = address.getText().toString().trim();
        String nutritionistPhone = phone.getText().toString().trim();
        String nutritionistCity = cityN.getText().toString().trim();

        if(nutritionistName.isEmpty()) {
            name.setError("Name is missing!");
            name.requestFocus();
            return;
        }

        if(nutritionistEmail.isEmpty()) {
            email.setError("Email is missing!");
            email.requestFocus();
            return;
        }

        if(nutritionistAddress.isEmpty()) {
            address.setError("Address is missing!");
            address.requestFocus();
            return;
        }

        if(nutritionistPhone.isEmpty()) {
            phone.setError("Phone is missing!");
            phone.requestFocus();
            return;
        }

        if(nutritionistCity.isEmpty()) {
            cityN.setError("City is missing!");
            cityN.requestFocus();
            return;
        }

        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference().child("City");



                        Query cityByNameQuery = databaseReference.orderByChild("name").equalTo(nutritionistCity);
                        cityByNameQuery.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                String id = snapshot.getKey();
                                Nutritionist nutritionist = new Nutritionist(nutritionistName, nutritionistEmail, nutritionistAddress, nutritionistPhone, nutritionistCity);

                                DatabaseReference newRef = databaseReference.child(id).child("Nutritionists").push();
                                newRef.setValue(nutritionist);
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


    }
}
