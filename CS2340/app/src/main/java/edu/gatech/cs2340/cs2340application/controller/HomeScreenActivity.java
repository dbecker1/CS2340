package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.SourceReport;

public class HomeScreenActivity extends AppCompatActivity {
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
    }

    protected void onLogoutPressed(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();

        Intent welcome = new Intent(this, WelcomeActivity.class);
        startActivity(welcome);
        finish();
    }

    protected void onProfilePressed(View view) {
        Intent welcome = new Intent(this, ProfileActivity.class);
        startActivity(welcome);
        finish();
    }

    protected void purityReport(View view) {
        Intent welcome = new Intent(this, PurityReportActivity.class);
        startActivity(welcome);
        finish();
    }

    protected void sourceReport(View view) {
        Intent welcome = new Intent(this, SourceReportActivity.class);
        startActivity(welcome);
        finish();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
