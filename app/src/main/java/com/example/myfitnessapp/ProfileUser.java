package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class ProfileUser extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private Button btnUpdate;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView t1, t2, t3, t4, t5;
    private EditText t6, t7;
    protected User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        back = (ImageView) findViewById(R.id.image_back);
        back.setOnClickListener(this);

        btnUpdate = (Button) findViewById(R.id.button17);
        btnUpdate.setOnClickListener(this);

        t1 = (TextView) findViewById(R.id.textView18);  //email
        t2 = (TextView) findViewById(R.id.textView19);  //day
        t3 = (TextView) findViewById(R.id.textView26);  //month
        t4 = (TextView) findViewById(R.id.textView27);  //year
        t5 = (TextView) findViewById(R.id.textView20);  //height
        t6 = (EditText) findViewById(R.id.textView21);  //weight
        t7 = (EditText) findViewById(R.id.textView22);  //target weight


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
                            t5.setText(String.valueOf(user.getHeight()));
                            t6.setText(String.valueOf(user.getWeight()));
                            t7.setText(String.valueOf(user.getTargetWeight()));
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int _WEIGHT = Integer.parseInt(t6.getText().toString());
                    int _TARGETWEIGHT = Integer.parseInt(t7.getText().toString());
                    //String key = databaseReference.child("Users").push().getKey();

                    HashMap hashMap = new HashMap();
                    hashMap.put("weight", _WEIGHT);
                    hashMap.put("targetWeight", _TARGETWEIGHT);

                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(ProfileUser.this, "Data successfully updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                startActivity(new Intent(ProfileUser.this, HomePageActivity.class));
                break;
            case R.id.button17:
                startActivity(new Intent(ProfileUser.this, ProfileUser.class));
                break;

        }
    }
}


