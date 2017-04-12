package edu.gatech.cs2340.cs2340application.controller;

/*
 * Creating and displaying data points on history report graph
 *
 * @version 1.0
 * @author Daniel Becker
 */
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import edu.gatech.cs2340.cs2340application.model.PurityReport;

class HistoricalReportService {

    void getReportData(final boolean isVirus, final String location, final String year, final ReportDataInterface callback) {
        final LinkedList<Double>[] data = new LinkedList[12];
        for(int i = 0; i < 12; i++) {
            data[i] = new LinkedList<>();
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("purityReports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int yearInt = Integer.parseInt(year);
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    PurityReport report = postSnapshot.getValue(PurityReport.class);
                    Double entry; // = 0.0?

                    String yearString = report.getDateTimeString().substring(report.getDateTimeString().length() - 4);
                    int selectedYear = Integer.parseInt(yearString);


                    if (selectedYear == yearInt && report.getLocation().equals(location)) {
                        if(isVirus) {
                            entry = report.getVirusPPM();
                        } else {
                            entry = report.getContainmentPPM();
                        }

                        try{

                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", new Locale("US"));
                            Date date = sdf.parse(report.getDateTimeString());
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            int month = cal.get(Calendar.MONTH);
                            data[month].add(entry);

                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Number[] averagedData = new Number[12];
                for(int i = 0; i < 12; i++) {
                    LinkedList<Double> list = data[i];
                    if(list.size() == 0) {
                        averagedData[i] = 0;
                    } else {
                        double sum = 0.0;
                        for(Double point : list) {
                            sum += point;
                        }
                        averagedData[i] = sum / list.size();
                    }
                }

                callback.foundData(averagedData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("database error");
            }
        });
    }
}

