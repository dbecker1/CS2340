package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.EditText;
import android.view.View;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.gatech.cs2340.cs2340application.R;

public class LoginActivity extends AppCompatActivity {

    private TextView errorView;
    private EditText username;
    private EditText password;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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

    /**
     * When the Submit button is pressed, the method extracts the entered username in
     * usernameTextField and  password in passwordTextField to pass to fire base to sign in.
     * If sign in is unsuccessful, an error message is shown. If sign in is successful, then the
     * user is taken to the Home Screen.
     *
     * @param view The current screen of the Login Screen from activity_login.xml
     */

    public void onSubmitPressed(View view) {
        LoginProviderInterface provider = new FirebaseLogin();
        login(username.getText().toString(), password.getText().toString(), provider, new LoginResultInterface() {
            @Override
            public void success() {
                Intent next = new Intent(LoginActivity.this, HomeScreenActivity.class);
                startActivity(next);
                finish();
            }

            @Override
            public void failure(String failureMessage) {
                errorView.setText(failureMessage);
            }
        });
    }

    public void login(String username, String password, LoginProviderInterface provider, LoginResultInterface handler) {
        if (username.length() == 0) {
            handler.failure("Username cannot be empty.");
            return;
        }
        if (password.length() == 0) {
            handler.failure("Password cannot be empty.");
            return;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            handler.failure("Username must be valid email.");
            return;
        }
        provider.login(username, password, handler);
    }

    /**
     * Go back to the Welcome Screen using onBackPressed() when the cancel button is pressed
     *
     * @param view The current view of Login Screen from activity_login.xml
     */

    public void onCancelPressed(View view) {
        onBackPressed();
    }

    /**
     * Go to the Registration screen when the Register button is pressed
     *
     * @param view The current screen of Login Screen from activity_login.xml
     */

    public void onRegisterPressed(View view) {
        Intent registration = new Intent(this, RegistrationActivity.class);
        startActivity(registration);
    }

    public void onForgotPasswordClicked(View view) {
        Intent forgot = new Intent(this, ForgotPasswordActivity.class);
        startActivity(forgot);
    }

}
