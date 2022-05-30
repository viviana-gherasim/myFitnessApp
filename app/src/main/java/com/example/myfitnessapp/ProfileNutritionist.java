package com.example.myfitnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileNutritionist extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView t1,t2,t3,t4;
    protected Nutritionist nutritionist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booking_step2);

        back = (ImageView) findViewById(R.id.image_back);
        back.setOnClickListener(this);

        t1 = (TextView) findViewById(R.id.textView188); //name
        t2 = (TextView) findViewById(R.id.textView184); //email
        t3 = (TextView) findViewById(R.id.textView185); //address
        t4 = (TextView) findViewById(R.id.textView186); //phone

        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference().child("City");
    }

    @Override
    public void onClick(View v) {

    }
}
