package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfitnessapp.Manager.ManagerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login, back;
    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = findViewById(R.id.button3);
        login.setOnClickListener(this);

        back = findViewById(R.id.button4);
        back.setOnClickListener(this);

        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button3:
                login();
                break;

            case R.id.button4:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void login()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.requestFocus();
            editTextEmail.setError("Please enter email!");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextEmail.requestFocus();
            editTextEmail.setError("Invalid email!");
            return;
        }

        if(password.isEmpty())
        {
            editTextPassword.requestFocus();
            editTextPassword.setError("Please enter password!");
            return;
        }

        if(password.length()<6)
        {
            editTextPassword.requestFocus();
            editTextPassword.setError("Invalid password!(Min.6 chars)");
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(email.equals("manager@gmail.com")) {
                        startActivity(new Intent(LoginActivity.this, ManagerActivity.class));
                    }
                    else
                        { startActivity(new Intent(LoginActivity.this,HomePageActivity.class)); }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login failed! Please verify your credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
