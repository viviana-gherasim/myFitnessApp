package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    Button add;
    private Food food;
    private ListView foodList;
    private ArrayList<Food> foodArray = new ArrayList<>();
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        back = (ImageView) findViewById(R.id.backToHomePage);
        back.setOnClickListener(this);

        add = (Button) findViewById(R.id.addFoodButton);
        add.setOnClickListener(this);

        foodList = (ListView) findViewById(R.id.foodListView);
        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference=database.getReference().child("Food");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()) {
                    food = d.getValue(Food.class);
                    foodArray.add(food);
                }
                FoodListAdapter adapter = new FoodListAdapter(FoodActivity.this, R.layout.adapter_food_view,foodArray);
                foodList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToHomePage:
                startActivity(new Intent(this, HomePageActivity.class));
                break;
            case R.id.addFoodButton:
                startActivity(new Intent(this, AddFood.class));
                break;
        }
    }
}
