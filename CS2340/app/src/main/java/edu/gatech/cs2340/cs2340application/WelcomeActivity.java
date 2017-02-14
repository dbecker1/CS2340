package edu.gatech.cs2340.cs2340application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.view.View;


public class WelcomeActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        loginButton = (Button) findViewById(R.id.login_button);
        registerButton = (Button) findViewById(R.id.register_button);
    }

    protected void onLoginPressed(View view) {

        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);

    }

    protected void onRegisterPressed(View view) {

        Intent register = new Intent(this, LoginActivity.class);
        startActivity(register);

    }

    @Override
    public void onBackPressed() {
        return;
    }
}
