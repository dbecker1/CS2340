package edu.gatech.cs2340.cs2340application;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class ProfileActivity extends AppCompatActivity {

    private EditText email;
    private EditText address;
    private RadioGroup userType;
    private DatabaseReference database;
    private User existingProfile;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        database = FirebaseDatabase.getInstance().getReference();
        email = (EditText) findViewById(R.id.emailEditText);
        address = (EditText) findViewById(R.id.addressEditText);
        userType = (RadioGroup) findViewById(R.id.radio_group);
        userType.check(R.id.userRadioButton);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userID = auth.getCurrentUser().getUid();
        //User existingProfile = (User) database.child("users").child(userID)

        database.child("users").orderByChild(userID).limitToFirst(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                HashMap<String, String> userMap =  (HashMap<String, String>)dataSnapshot.getValue();
                existingProfile = new User(userMap.get("emailAddress"), userMap.get("userType"), userMap.get("address"));
                email.setText(existingProfile.emailAddress);
                address.setText(existingProfile.address);
                int radioButton = -1;
                switch(existingProfile.userType) {
                    case "User":
                        radioButton = R.id.userRadioButton;
                        break;
                    case "Worker":
                        radioButton = R.id.workerRadioButton;
                        break;
                    case "Manager":
                        radioButton = R.id.managerRadioButton;
                        break;
                    case "Admin":
                        radioButton = R.id.adminRadioButton;
                        break;
                    default:
                        radioButton = R.id.userRadioButton;
                        break;
                }
                userType.check(radioButton);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    protected void onSavePressed(View view) {
        int radioId = userType.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) userType.findViewById(radioId);
        String userTypeString = radioButton.getText().toString();

        User user = new User(email.getText().toString(), userTypeString, address.getText().toString());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userID = auth.getCurrentUser().getUid();
        if (existingProfile != null) {
            auth.getCurrentUser().updateEmail(user.getEmailAddress());
        }
        database.child("users").child(userID).setValue(user);

        Intent next = new Intent(ProfileActivity.this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }

    protected void onCancelPressed(View view) {
        Intent next = new Intent(ProfileActivity.this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
