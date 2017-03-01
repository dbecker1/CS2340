package edu.gatech.cs2340.cs2340application.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.UUID;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.SourceReport;

public class SourceReportActivity extends AppCompatActivity {

    private EditText location;
    private RadioGroup waterType;
    private RadioGroup waterCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sourcereport);

        location = (EditText) findViewById(R.id.latitudeLongitude);
        waterType = (RadioGroup) findViewById(R.id.waterType);
        waterCondition = (RadioGroup) findViewById(R.id.waterCondition);
    }

    /**
     * When Save button is pressed, the method extracts all the information entered by the user
     * and create a new Source Report object. Then the user is taken back to the Home Screen.
     *
     * @param view The current screen of the Source Report Screen from activity_sourcereport.xml
     */
    protected void onSavePressed(View view) {
        int typeId = waterType.getCheckedRadioButtonId();
        RadioButton typeButton = (RadioButton) waterType.findViewById(typeId);
        String waterTypeString = typeButton.getText().toString();

        int conditionID = waterCondition.getCheckedRadioButtonId();
        RadioButton conditionButton = (RadioButton) waterCondition.findViewById(conditionID);
        String conditionString = conditionButton.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        SourceReport report = new SourceReport();
        report.setDateTime(new Date());
        report.setUserId(auth.getCurrentUser().getEmail());
        report.setReportNumber(UUID.randomUUID().toString());
        report.setCondition(conditionString);
        report.setType(waterTypeString);
        report.setLocation(location.getText().toString());

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("sourceReports").child(report.getReportNumber()).setValue(report);

        Context context = getApplicationContext();
        CharSequence text = "Water Source Report Saved.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent next = new Intent(SourceReportActivity.this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }

    /**
     * When the Cancel button is pressed, no information entered is saved, and the user is taken
     * back to the Home Screen
     *
     * @param view The current screen of the Source Report Screen from activity_sourcereport.xml
     */
    protected void onCancelPressed(View view){
        Intent next = new Intent(SourceReportActivity.this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }
}
