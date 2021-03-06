package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.User;

public class HomeScreenActivity extends AppCompatActivity {

    private Button submitPurity;
    private Button historicalReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        submitPurity = (Button)findViewById(R.id.purity);
        historicalReport = (Button)findViewById(R.id.historical_report);
        String uid = "";

        try {
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users").child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user.getUserType().equals("Worker") || user.getUserType().equals("Manager")) {
                    submitPurity.setVisibility(View.VISIBLE);
                }
                if (user.getUserType().equals("Manager")) {
                    historicalReport.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * When Logout button is pressed, the method sign the user out using Firebase's signOut()
     * method and take the user back to the Welcome Screen
     *
     * @param view The current screen of Home Screen from activity_homescreen.xml
     */
    public void onLogoutPressed(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();

        Intent welcome = new Intent(this, WelcomeActivity.class);
        startActivity(welcome);
        finish();
    }

    /**
     * Go to the Profile Edit screen when the Profile button is pressed.
     *
     * @param view The current screen of the Home Screen from activity_homescreen.xml
     */
    public void onProfilePressed(View view) {
        Intent welcome = new Intent(this, ProfileActivity.class);
        startActivity(welcome);
        finish();
    }

    /**
     * When Submit Purity Report button is pressed, the user is taken to Purity Report Screen from
     * activity_purity_report.xml
     *
     * @param view The current screen of the Home Screen from activity_homescreen.xml
     */
    public void purityReport(View view) {
        Intent welcome = new Intent(this, PurityReportActivity.class);
        startActivity(welcome);
        finish();
    }

    /**
     * When Submit Source Report button is pressed, the user is taken to the Source Report Screen
     * from activity_sourcereport.xml
     *
     * @param view The current screen of the Home Screen from activity_homescreen.xml
     */
    public void sourceReport(View view) {
        Intent welcome = new Intent(this, SourceReportActivity.class);
        startActivity(welcome);
        finish();
    }

    /**
     * When View Reports buttons are pressed, the user is taken to the View Reports page from
     * activity_view_reports.xml
     *
     * @param view The current screen of the Home Screen from activity_homescreen.xml
     */
    public void onViewReportsClicked(View view) {
        Intent welcome = new Intent(this, ViewReportsActivity.class);
        startActivity(welcome);
        finish();
    }

    public void onAvailabilityClicked(View view) {
        Intent availability = new Intent(this, ReportMapsActivity.class);
        startActivity(availability);
        finish();
    }

    public void onHistoricalReportClicked(View view) {
        Intent historical = new Intent(this, HistoryInformationActivity.class);
        startActivity(historical);
        finish();
    }

    @Override
    public void onBackPressed() {
    }
}
