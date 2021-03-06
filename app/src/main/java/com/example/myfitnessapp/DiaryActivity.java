package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class DiaryActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView home, addBreakfast, addLunch, addDinner, addSnack;
    private ListView breakfastList, lunchList, dinnerList, snacksList;
    protected FoodDiary foodDiary;
    protected User userApp;
    private TextView target, sum;
    private DatabaseReference databaseReference, databaseReferenceUsers;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary);

        ArrayList<FoodDiary> breakfastArray = new ArrayList<>();
        ArrayList<FoodDiary> lunchArray = new ArrayList<>();
        ArrayList<FoodDiary> dinnerArray = new ArrayList<>();
        ArrayList<FoodDiary> snacksArray = new ArrayList<>();

        addBreakfast = findViewById(R.id.moreBreakfast);
        addBreakfast.setOnClickListener(this);

        addLunch = findViewById(R.id.moreLunch);
        addLunch.setOnClickListener(this);

        addDinner = findViewById(R.id.moreDinner);
        addDinner.setOnClickListener(this);

        addSnack = findViewById(R.id.moreSnacks);
        addSnack.setOnClickListener(this);

        target = findViewById(R.id.textViewTargetCalorii);
        sum = findViewById(R.id.textViewSumCalorii);

        home = findViewById(R.id.backToHome);
        home.setOnClickListener(this);

        breakfastList = findViewById(R.id.breakfastListView);
        lunchList = findViewById(R.id.lunchListView);
        dinnerList = findViewById(R.id.dinnerListView);
        snacksList = findViewById(R.id.snacksListView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null) {
            String userEmail = user.getEmail();

            database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
            databaseReference = database.getReference().child("Diary");
            databaseReference.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int sumaCalorii = 0;
                    int calorii = 0;
                    float fcalorii = 0;

                    for(DataSnapshot d: snapshot.getChildren()) {

                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                        int day = calendar.get(Calendar.DATE);
                        int month = calendar.get(Calendar.MONTH)+1;
                        int year = calendar.get(Calendar.YEAR);

                        String stringDay = String.valueOf(day);
                        String stringMonth = String.valueOf(month);
                        String stringYear = String.valueOf(year);

                        foodDiary = d.getValue(FoodDiary.class);

                        if((foodDiary.getCategory().equals("Breakfast")) && (foodDiary.getDay().equals(stringDay)) && (foodDiary.getMonth().equals(stringMonth)) && (foodDiary.getYear().equals(stringYear)) && (foodDiary.getEmail().equals(userEmail))) {

                            breakfastArray.add(foodDiary);
                            fcalorii = Float.parseFloat(foodDiary.getCalories());
                            calorii = (int) fcalorii;
                            sumaCalorii = sumaCalorii + calorii;
                        }
                        else
                        if((foodDiary.getCategory().equals("Lunch")) && (foodDiary.getDay().equals(stringDay)) && (foodDiary.getMonth().equals(stringMonth)) && (foodDiary.getYear().equals(stringYear)) && (foodDiary.getEmail().equals(userEmail))) {
                            lunchArray.add(foodDiary);
                            fcalorii = Float.parseFloat(foodDiary.getCalories());
                            calorii = (int) fcalorii;
                            sumaCalorii = sumaCalorii + calorii;
                        }
                        else
                        if((foodDiary.getCategory().equals("Dinner")) && (foodDiary.getDay().equals(stringDay)) && (foodDiary.getMonth().equals(stringMonth)) && (foodDiary.getYear().equals(stringYear)) && (foodDiary.getEmail().equals(userEmail))) {
                            dinnerArray.add(foodDiary);
                            fcalorii = Float.parseFloat(foodDiary.getCalories());
                            calorii = (int) fcalorii;
                            sumaCalorii = sumaCalorii + calorii;
                        }
                        else
                        if((foodDiary.getCategory().equals("Snacks")) && (foodDiary.getDay().equals(stringDay)) && (foodDiary.getMonth().equals(stringMonth)) && (foodDiary.getYear().equals(stringYear)) && (foodDiary.getEmail().equals(userEmail))) {
                            snacksArray.add(foodDiary);
                            fcalorii = Float.parseFloat(foodDiary.getCalories());
                            calorii = (int) fcalorii;
                            sumaCalorii = sumaCalorii + calorii;
                        }
                    }

                    DiaryActivityAdapter adapterBreakfast = new DiaryActivityAdapter(DiaryActivity.this, R.layout.adapter_diary_view, breakfastArray);
                    breakfastList.setAdapter(adapterBreakfast);
                    adapterBreakfast.notifyDataSetChanged();

                    DiaryActivityAdapter adapterLunch = new DiaryActivityAdapter(DiaryActivity.this, R.layout.adapter_diary_view, lunchArray);
                    lunchList.setAdapter(adapterLunch);
                    adapterLunch.notifyDataSetChanged();

                    DiaryActivityAdapter adapterDinner = new DiaryActivityAdapter(DiaryActivity.this, R.layout.adapter_diary_view, dinnerArray);
                    dinnerList.setAdapter(adapterDinner);
                    adapterDinner.notifyDataSetChanged();

                    DiaryActivityAdapter adaptertSnacks = new DiaryActivityAdapter(DiaryActivity.this, R.layout.adapter_diary_view, snacksArray);
                    snacksList.setAdapter(adaptertSnacks);
                    adaptertSnacks.notifyDataSetChanged();

                    String strSumaCalorii = String.valueOf(sumaCalorii);
                    sum.setText(strSumaCalorii);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            databaseReferenceUsers = database.getReference().child("Users");
            databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot d:snapshot.getChildren()) {
                        userApp = d.getValue(User.class);

                        if(userApp.getEmail().equals(userEmail)) {

                            double BMR;
                            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                            int year = calendar.get(Calendar.YEAR);
                            int greutate = Integer.parseInt(String.valueOf(userApp.getWeight()));
                            int inaltime = Integer.parseInt(String.valueOf(userApp.getHeight()));
                            int varsta = year - Integer.parseInt(userApp.getYear());
                            BMR = 88.362 + (13.397 * greutate) + (4.799 * inaltime) + (5.677 * varsta);
                            double caloriiTarget = BMR * 1.2;
                            int intCaloriiTarget = (int)caloriiTarget;
                            String stringCaloriiTarget = String.valueOf(intCaloriiTarget);
                            target.setText(stringCaloriiTarget);

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.moreBreakfast:
                startActivity(new Intent(this, SearchFoodActivity.class));
                break;

            case R.id.moreLunch:
                startActivity(new Intent(this, SearchFoodActivity.class));
                break;

            case R.id.moreDinner:
                startActivity(new Intent(this, SearchFoodActivity.class));
                break;

            case R.id.moreSnacks:
                startActivity(new Intent(this, SearchFoodActivity.class));
                break;

            case R.id.backToHome:
                startActivity(new Intent(this, HomePageActivity.class));
                break;
        }
    }
}
