package edu.gatech.cs2340.cs2340application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView errorView;
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        errorView = (TextView) findViewById(R.id.errorView);
        username = (EditText) findViewById(R.id.usernameTextField);
        password = (EditText) findViewById(R.id.passwordTextField);
        username.setText("");
        password.setText("");
        mAuth = FirebaseAuth.getInstance();
    }

    protected void onSubmitPressed(View view) {
        if (!username.getText().toString().equals("") && !password.getText().toString().equals("")){
            String email = username.getText().toString();
            String pwd = password.getText().toString();
            mAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                errorView.setText("Error: Invalid username or password");
                            } else {
                                Intent next = new Intent(LoginActivity.this, HomeScreenActivity.class);
                                startActivity(next);
                                finish();
                            }
                        }
                    });
        } else {
            errorView.setText("Error: Please enter a username and password");
        }
    }

    protected void onCancelPressed(View view) {
        onBackPressed();
    }

    protected void onRegisterPressed(View view) {
        Intent registration = new Intent(this, RegistrationActivity.class);
        startActivity(registration);
    }

}
