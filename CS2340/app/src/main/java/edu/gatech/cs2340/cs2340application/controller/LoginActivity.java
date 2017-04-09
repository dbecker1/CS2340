package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.EditText;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.cs2340.cs2340application.R;

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

    /**
     * When the Submit button is pressed, the method extracts the entered username in
     * usernameTextField and  password in passwordTextField to pass to fire base to sign in.
     * If sign in is unsuccessful, an error message is shown. If sign in is successful, then the
     * user is taken to the Home Screen.
     *
     * @param view The current screen of the Login Screen from activity_login.xml
     */

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

    /**
     * Go back to the Welcome Screen using onBackPressed() when the cancel button is pressed
     *
     * @param view The current view of Login Screen from activity_login.xml
     */

    protected void onCancelPressed(View view) {
        onBackPressed();
    }

    /**
     * Go to the Registration screen when the Register button is pressed
     *
     * @param view The current screen of Login Screen from activity_login.xml
     */

    protected void onRegisterPressed(View view) {
        Intent registration = new Intent(this, RegistrationActivity.class);
        startActivity(registration);
    }

    protected void onRestorePressed(View view) {
        Intent restorePassword = new Intent(this, RestorePasswordActivity.class);
        startActivity(restorePassword);
    }

}
