package edu.gatech.cs2340.cs2340application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.EditText;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

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
    }

    protected void onSubmitPressed(View view) {
        if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")){
            Intent next = new Intent(this, HomeScreenActivity.class);
            startActivity(next);
        } else {
            errorView.setText("Error: Incorrect Username or Password");
        }
    }

    protected void onCancelPressed(View view) {
        Intent back = new Intent(this, WelcomeActivity.class);
        startActivity(back);
    }

}
