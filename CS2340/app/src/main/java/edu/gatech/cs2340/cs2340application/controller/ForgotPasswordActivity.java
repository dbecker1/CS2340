package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.cs2340.cs2340application.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email = (EditText) findViewById(R.id.enterForgottenEmail);
    }

    public void onSubmit(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    successfulySent();
                } else {
                    unsuccessful();
                }
            }
        });
    }

    private void successfulySent() {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
        Toast result = Toast.makeText(getApplicationContext(),
                "Email successfully sent.", Toast.LENGTH_LONG);
        result.show();
    }

    private void unsuccessful() {
        Toast result = Toast.makeText(getApplicationContext(),
                "Email address not found.", Toast.LENGTH_LONG);
        result.show();
    }



}
