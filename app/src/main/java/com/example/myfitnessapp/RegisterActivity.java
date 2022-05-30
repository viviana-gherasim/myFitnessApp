package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signup, back;
    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword, editTextWeight, editTextHeight, editTextTargetWeight, daySpinner, monthSpinner, yearSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        back = (TextView) findViewById(R.id.button6);
        back.setOnClickListener(this);

        signup = (TextView) findViewById(R.id.button5);
        signup.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword2);
        editTextWeight = (EditText) findViewById(R.id.editTextNumber2);
        editTextHeight = (EditText) findViewById(R.id.editTextNumber3);
        editTextTargetWeight = (EditText) findViewById(R.id.editTextNumber4);
        daySpinner = (EditText) findViewById(R.id.editTextNumber);
        monthSpinner = ( EditText) findViewById(R.id.editTextNumber5);
        yearSpinner = (EditText) findViewById(R.id.editTextNumber6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.button5:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email =editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        Long height=Long.parseLong(editTextHeight.getText().toString().trim());
        Long weight=Long.parseLong(editTextWeight.getText().toString().trim());
        Long tWeight=Long.parseLong(editTextTargetWeight.getText().toString().trim());
        String day=daySpinner.getText().toString().trim();
        String month=monthSpinner.getText().toString().trim();
        String year=yearSpinner.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextEmail.setError("Email has a wrong format!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            editTextPassword.setError("Password is to short");
            editTextPassword.requestFocus();
            return;
        }

        if(editTextHeight.getText().length() == 0){
            editTextHeight.setError("Height is required!");
            editTextHeight.requestFocus();
            return;
        }
        if(editTextWeight.getText().length() == 0){
            editTextWeight.setError("Weight is required!");
            editTextWeight.requestFocus();
            return;
        }
        if(editTextTargetWeight.getText().length() == 0){
            editTextTargetWeight.setError("Target weight is required!");
            editTextTargetWeight.requestFocus();
            return;
        }

        if(day.isEmpty()){
            daySpinner.setError("Day is required!");
            daySpinner.requestFocus();
            return;
        }
        if(month.isEmpty()){
            monthSpinner.setError("Month is required!");
            monthSpinner.requestFocus();
            return;
        }
        if(year.isEmpty()){
            yearSpinner.setError("Year is required!");
            yearSpinner.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user = new User(email,password, height,weight,tWeight, day, month, year);

                            FirebaseDatabase.getInstance(" https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app").getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast toast=Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class ));
                                    }
                                    else
                                    {
                                        Toast toast=Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG);;
                                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                    }
                                }
                            });
                        }else
                        {
                            Toast toast=Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG);;
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                        }
                    }
                });
    }
}
