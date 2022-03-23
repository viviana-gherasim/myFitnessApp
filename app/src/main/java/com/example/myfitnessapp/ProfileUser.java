package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ProfileUser extends AppCompatActivity implements View.OnClickListener {

    private TextView back;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView t1, t2, t3, t4, t5, t6, t7;
    protected User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        back = (TextView) findViewById(R.id.button12);
        back.setOnClickListener(this);

        t1 = (TextView) findViewById(R.id.textView18);  //email
        t2 = (TextView) findViewById(R.id.textView19);  //day
        t3 = (TextView) findViewById(R.id.textView26);  //month
        t4 = (TextView) findViewById(R.id.textView27);  //year
        t5 = (TextView) findViewById(R.id.textView20);  //height
        t6 = (TextView) findViewById(R.id.textView21);  //weight
        t7 = (TextView) findViewById(R.id.textView22);  //target weight

        FirebaseUser userConnected = FirebaseAuth.getInstance().getCurrentUser();
        if(userConnected != null) {
            String userEmail = userConnected.getEmail();
            database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
            databaseReference = database.getReference().child("Users");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot d : snapshot.getChildren()) {
                        user = d.getValue(User.class);
                        if(user.getEmail().equals(userEmail)) {
                            t1.setText(user.getEmail());
                            t2.setText(user.getDay());
                            t3.setText(user.getMonth());
                            t4.setText(user.getYear());
                            t5.setText(user.getHeight());
                            t6.setText(user.getWeight());
                            t7.setText(user.getTargetWeight());
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
            case R.id.button12:
                startActivity(new Intent(ProfileUser.this, HomePageActivity.class));
        }
    }
}


