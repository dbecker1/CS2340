package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.cs2340.cs2340application.R;


public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Intent next = new Intent(WelcomeActivity.this, HomeScreenActivity.class);
            startActivity(next);
            finish();
        }
    }

    /**
     * When the Login button is pressed, the user is taken to the Login Screen
     *
     * @param view The current screen of the Welcome Screen from activity_welcome.xml
     */
    protected void onLoginPressed(View view) {

        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);

    }

    /**
     * When Register button is pressed, the user is taken to the Registration Screen.
     *
     * @param view The current screen of the Welcome Screen from activity_welcome.xml
     */
    protected void onRegisterPressed(View view) {
        Intent register = new Intent(this, RegistrationActivity.class);
        startActivity(register);
    }

    @Override
    public void onBackPressed() {
    }
}
