package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.RadioButton;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioGroup;


import edu.gatech.cs2340.cs2340application.R;

public class HistoryInformationActivity extends AppCompatActivity {

    private EditText location;
    private EditText year;
    private RadioGroup contaminantType;
    private RadioButton typeSelected;
    private RadioButton virus;
    private RadioButton contaminant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_report_setup);
        location = (EditText) findViewById(R.id.location);
        year = (EditText) findViewById(R.id.year);
        contaminantType = (RadioGroup) findViewById(R.id.contaminantType);
        virus = (RadioButton) findViewById(R.id.virusButton);
        contaminant = (RadioButton) findViewById(R.id.contaminantButton);
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
        Intent reportScreen = new Intent(this, HistoricalReportsActivity.class);


        String locationString = location.getText().toString();
        String yearString = year.getText().toString();
        int typeSelectedId = contaminantType.getCheckedRadioButtonId();

        // find whether virus or contaminant is selected
        String typeOfContaminant;
        typeSelected = (RadioButton) findViewById(typeSelectedId);
        if(typeSelected == virus)  {
            typeOfContaminant = "virus";
        } else {
            typeOfContaminant = "contaminant";
        }


        //Create the bundle
        Bundle bundle = new Bundle();

        //Add your data to bundle
        bundle.putString("location", locationString);
        bundle.putString("year", yearString);
        bundle.putString("typeOfContaminant", typeOfContaminant);

        //Add the bundle to the intent
        reportScreen.putExtras(bundle);

        //Fire that second activity
        startActivity(reportScreen);


    }

    /**
     * Go back to the Welcome Screen using onBackPressed() when the cancel button is pressed
     *
     * @param view The current view of Login Screen from activity_login.xml
     */

    protected void onCancelPressed(View view) {
        onBackPressed();
    }


    @Override
    public void onBackPressed() {
        Intent home = new Intent(this, HomeScreenActivity.class);
        startActivity(home);
        finish();
    }
}