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
import edu.gatech.cs2340.cs2340application.model.PurityReport;

public class PurityReportActivity extends AppCompatActivity {

    private EditText location;
    private EditText contaminant;
    private EditText virus;
    private RadioGroup condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_report);

        location = (EditText) findViewById(R.id.latitudeLongitude);
        contaminant = (EditText) findViewById(R.id.contaminant);
        virus = (EditText) findViewById(R.id.virus);
        condition = (RadioGroup) findViewById(R.id.condition);
    }

    protected void onSavePressed(View view) {
        int conditionID = condition.getCheckedRadioButtonId();
        RadioButton conditionButton = (RadioButton) condition.findViewById(conditionID);
        String conditionString = conditionButton.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        PurityReport report = new PurityReport();
        report.setDateTime(new Date());
        report.setUserId(auth.getCurrentUser().getEmail());
        report.setReportNumber(UUID.randomUUID().toString());
        report.setCondition(conditionString);
        report.setLocation(location.getText().toString());
        report.setContainmentPPM(Double.parseDouble(contaminant.getText().toString()));
        report.setVirusPPM(Double.parseDouble(virus.getText().toString()));

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("purityReports").child(report.getReportNumber()).setValue(report);

        Context context = getApplicationContext();
        CharSequence text = "Water Purity Report Saved.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent next = new Intent(PurityReportActivity.this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }

    protected void onCancelPressed(View view){
        Intent next = new Intent(PurityReportActivity.this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent next = new Intent(this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }
}
