package edu.gatech.cs2340.cs2340application;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Context;
import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private EditText confirmPassword;
    private EditText id;
    private RadioGroup userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = (EditText) findViewById(R.id.nameTextField);
        password = (EditText) findViewById(R.id.passwordTextField);
        confirmPassword = (EditText) findViewById(R.id.retypePasswordTextField);
        id = (EditText) findViewById(R.id.idTextField);
        userType = (RadioGroup) findViewById(R.id.radio_group);
        name.setText("");
        password.setText("");
        confirmPassword.setText("");
        id.setText("");
        userType.check(R.id.userRadioButton);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


    }

    protected void onCancelPressed(View view) {
        onBackPressed();
    }

    protected void onRegisterPressed(View view) {
        int typeOfError = checkValidInputs();
        if (typeOfError == 0) {

        } else if (typeOfError == 1) {
            new AlertDialog.Builder(this)
                    .setTitle("ERROR: INVALID USERNAME")
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
                    .setTitle("ERROR: Passwords do not match")
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
                    .setTitle("ERROR: INVALID PASSWORD")
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
                    .setTitle("ERROR: INVALID ID")
                    .setMessage("Please enter a valid ID")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            HashMap<String, User> userHashMap;
        }
    }

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
