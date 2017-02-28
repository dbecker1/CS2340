package edu.gatech.cs2340.cs2340application;

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

    protected void onSavePressed(View view) {
        int typeId = waterType.getCheckedRadioButtonId();
        RadioButton typeButton = (RadioButton) waterType.findViewById(typeId);
        String waterTypeString = typeButton.getText().toString();

        int conditionID = waterCondition.getCheckedRadioButtonId();
        RadioButton conditionButton = (RadioButton) waterType.findViewById(conditionID);
        String conditionString = conditionButton.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        SourceReport report = new SourceReport();
        report.setDateTime(new Date());
        report.setUserId(auth.getCurrentUser().getUid());
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

}
