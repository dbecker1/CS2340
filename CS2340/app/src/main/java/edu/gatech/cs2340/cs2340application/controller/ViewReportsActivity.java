package edu.gatech.cs2340.cs2340application.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.xml.transform.Source;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.PurityReport;
import edu.gatech.cs2340.cs2340application.model.Report;
import edu.gatech.cs2340.cs2340application.model.SourceReport;

public class ViewReportsActivity extends AppCompatActivity {

    ArrayList<Report> reports = new ArrayList<Report>();
    ReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        ListView reportsView = (ListView) findViewById(R.id.reportsView);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();


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
