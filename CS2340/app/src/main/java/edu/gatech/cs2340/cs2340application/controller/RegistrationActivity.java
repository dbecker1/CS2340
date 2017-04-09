package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.support.annotation.NonNull;
import android.util.Log;

import edu.gatech.cs2340.cs2340application.R;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private EditText name;
    private EditText password;
    private EditText confirmPassword;
    private EditText id;
    //private RadioGroup userType;
    private TextView errorText;
    //Firebase
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = (EditText) findViewById(R.id.nameTextField);
        password = (EditText) findViewById(R.id.passwordTextField);
        confirmPassword = (EditText) findViewById(R.id.retypePasswordTextField);
        id = (EditText) findViewById(R.id.idTextField);
        //userType = (RadioGroup) findViewById(R.id.radio_group);
        errorText = (TextView) findViewById(R.id.errorText);
        name.setText("");
        password.setText("");
        confirmPassword.setText("");
        id.setText("");
        //userType.check(R.id.userRadioButton);
        errorText.setText("");
        mAuth = FirebaseAuth.getInstance();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


    }

    /**
     * When the Cancel button is pressed, the user is taken back to the Welcome Screen using default
     * method of onBackPressed();
     *
     * @param view The current screen of the Registration Screen from activity_registration.xml
     */
    protected void onCancelPressed(View view) {
        onBackPressed();
    }

    /**
     * When the Register button is pressed, the entered email and password are verified and then
     * is passed on to Firebase. Then the user will be taken to the Edit Profile Screen to submit
     * their profile information. If the entered email or password does not fit criteria, then the
     * user is notified of unsuccessful registration.
     *
     * @param view The current screen of the Registration Screen from activity_registration.xml
     */
    protected void onRegisterPressed(View view) {
        int typeOfError = checkValidInputs();
        if (typeOfError == 0) {
            mAuth.createUserWithEmailAndPassword(name.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                errorText.setText(getResources().getString(R.string.unable_error));
                            } else {
                                Intent next = new Intent(RegistrationActivity.this, ProfileActivity.class);
                                startActivity(next);
                                finish();
                            }

                            // ...
                        }
                    });
        } else if (typeOfError == 1) {
            new AlertDialog.Builder(this)
                    .setTitle("Error: Invalid Username")
                    .setMessage("Please enter a valid username")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (typeOfError == 2) {
            new AlertDialog.Builder(this)
                    .setTitle("Error: Passwords do not match")
                    .setMessage("Please enter passwords again")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (typeOfError == 3) {
            new AlertDialog.Builder(this)
                    .setTitle("Error: Invalid Password")
                    .setMessage("Please enter a password")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (typeOfError == 4) {
            new AlertDialog.Builder(this)
                    .setTitle("Error: Invalid ID")
                    .setMessage("Please enter a valid ID")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    /**
     * Check if the input field of name, password, and email are valid inputs.
     *
     * @return the number that determines if the all the inputs are valid or if certain input are
     * valid and other ones are not.
     */
    private int checkValidInputs() {
        if (name.getText().toString().equals("")) {
            return 1;
        }
        if (!password.getText().toString().equals(confirmPassword.getText().toString())
                && !password.getText().toString().equals("")) {
            return 2;
        } else if (password.getText().toString().equals("")) {
            return 3;
        }
        if (id.getText().toString().equals("")) {
            return 4;
        }
        return 0;
    }

}
