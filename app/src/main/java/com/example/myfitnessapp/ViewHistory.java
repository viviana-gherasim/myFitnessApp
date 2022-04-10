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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class ViewHistory extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private TextView target, sum;
    private ListView foodHistoryList;
    private ArrayList<FoodDiary> foodHistoryArray = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference databaseReference, databaseReferenceUser;
    protected User userApp;
    protected FoodDiary foodDiary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_screen);

        Bundle extras = getIntent().getExtras();
        String date = extras.getString("date");
        String[] parts = date.split("/");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        back = (ImageView) findViewById(R.id.backToHomeButtonHistory);
        back.setOnClickListener(this);

        target = (TextView) findViewById(R.id.textTargetCaloriiHistory);
        sum = (TextView) findViewById(R.id.textSumCaloriiHistory);

        foodHistoryList = (ListView) findViewById(R.id.foodHistoryListView);

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
                    float fCalorii = 0;

                    for(DataSnapshot d:snapshot.getChildren()) {
                        foodDiary = d.getValue(FoodDiary.class);
                        if(foodDiary.getDay().equals(day) && foodDiary.getMonth().equals(month) && foodDiary.getYear().equals(year) && foodDiary.getEmail().equals(userEmail)) {
                            foodHistoryArray.add(foodDiary);
                            fCalorii = Float.parseFloat(foodDiary.getCalories());
                            calorii = (int) fCalorii;
                            sumaCalorii = sumaCalorii + calorii;
                        }
                    }

                    ViewHistoryAdapter adapter = new ViewHistoryAdapter(ViewHistory.this, R.layout.adapter_history_view, foodHistoryArray);
                    foodHistoryList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    String strSumaCalorii = String.valueOf(sumaCalorii);
                    sum.setText(strSumaCalorii);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //target-ul de calorii
            databaseReferenceUser = database.getReference().child("Users");
            databaseReferenceUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot d:snapshot.getChildren()) {
                        userApp = d.getValue(User.class);
                        if(userApp.getEmail().equals(userEmail)){
                            double BMR;
                            java.util.Calendar calendar= java.util.Calendar.getInstance(TimeZone.getDefault());
                            int year=calendar.get(Calendar.YEAR);
                            int iInaltime=Integer.parseInt(userApp.getHeight());
                            int iGreutate=Integer.parseInt(userApp.getWeight());
                            int iVarsta=year - Integer.parseInt(userApp.getYear());
                            BMR=88.362+(13.397*iGreutate)+(4.799*iInaltime)+(5.677*iVarsta);
                            double caloriiTarget=BMR*1.2;
                            int iCaloriiTarget=(int)caloriiTarget;
                            String strCaloriiTarget=String.valueOf(iCaloriiTarget);
                            target.setText(strCaloriiTarget);
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
        switch (v.getId()) {
            case R.id.backToHomeButtonHistory:
                startActivity(new Intent(ViewHistory.this, HomePageActivity.class));
                break;
            case R.id.buttonSearchHistory:
                startActivity(new Intent(ViewHistory.this, ViewHistory.class));
                break;
        }
    }
}
