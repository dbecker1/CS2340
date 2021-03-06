package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.PurityReport;
import edu.gatech.cs2340.cs2340application.model.Report;
import edu.gatech.cs2340.cs2340application.model.SourceReport;
import edu.gatech.cs2340.cs2340application.model.User;

public class ViewReportsActivity extends AppCompatActivity {

    private final ArrayList<Report> reports = new ArrayList<>();
    private ReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        ListView reportsView = (ListView) findViewById(R.id.reportsView);

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();


        adapter = new ReportAdapter(this, reports);
        reportsView.setAdapter(adapter);

        ref.child("sourceReports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    SourceReport report = postSnapshot.getValue(SourceReport.class);
                    reports.add(report);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("database error");
            }
        });

        try {
            ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            if(user.getUserType().equals("Worker") || user.getUserType().equals("Manager")) {
                                ref.child("purityReports").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                                            PurityReport report = postSnapshot.getValue(PurityReport.class);
                                            reports.add(report);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        System.out.println("database error");
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {
        Intent next = new Intent(this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }
}
