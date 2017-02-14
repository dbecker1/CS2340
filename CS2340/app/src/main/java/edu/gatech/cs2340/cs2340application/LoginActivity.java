package edu.gatech.cs2340.cs2340application;

import android.content.Intent;
import android.content.SharedPreferences;
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
        username.setText("");
        password.setText("");
    }

    protected void onSubmitPressed(View view) {
        if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")){
            SharedPreferences settings = getSharedPreferences("Preferences", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("username", username.getText().toString());

            editor.commit();
            Intent next = new Intent(this, HomeScreenActivity.class);
            startActivity(next);
            finish();
        } else {
            errorView.setText("Error: Incorrect Username or Password");
        }
    }

    protected void onCancelPressed(View view) {
        onBackPressed();
    }

}
