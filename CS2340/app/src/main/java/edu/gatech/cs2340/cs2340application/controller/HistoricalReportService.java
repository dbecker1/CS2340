package edu.gatech.cs2340.cs2340application.controller;

/**
 * Created by dbeckerfl on 3/27/17.
 */

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.LinkedList;

import edu.gatech.cs2340.cs2340application.model.PurityReport;

public class HistoricalReportService {

    public void getReportData(final boolean isVirus, final String location, final String year, final ReportDataInterface callback) {
        final LinkedList<Double>[] data = new LinkedList[12];

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("purityReports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int yearInt = Integer.parseInt(year);
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    PurityReport report = postSnapshot.getValue(PurityReport.class);
                    Double entry = 0.0;
                    if (report.getDateTime().getYear() == yearInt && report.getLocation().equals(location)) {
                        if(isVirus) {
                            entry = report.getVirusPPM();
                        } else {
                            entry = report.getContainmentPPM();
                        }
                        data[report.getDateTime().getMonth()].add(entry);
                    }
                }

                Number[] averagedData = new Number[12];
                for(int i = 0; i < 12; i++) {
                    LinkedList<Double> list = data[i];
                    double sum = 0.0;
                    for(Double point : list) {
                        sum += point;
                    }
                    averagedData[i] = sum / list.size();
                }

                String label;
                if(isVirus) {
                    label = "Virus";
                } else {
                    label = "Contaminant";
                }

                XYSeries series = new SimpleXYSeries(
                        Arrays.asList(averagedData), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, label);

                callback.foundData(series);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("database error");
            }
        });
    }
}

