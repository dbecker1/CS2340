package edu.gatech.cs2340.cs2340application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeScreenActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        mDrawerList = (ListView) findViewById(R.id.navList);
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

    private void addDrawerItems() {
        String[] osArray = { "Logout"};
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
