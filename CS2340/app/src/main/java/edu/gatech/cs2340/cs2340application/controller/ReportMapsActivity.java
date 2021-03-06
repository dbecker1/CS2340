package edu.gatech.cs2340.cs2340application.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class ReportMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final ArrayList<Marker> markers = new ArrayList<>();
    private final ArrayList<Report> reports = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ReportInfoWindowAdapter mAdapter = new ReportInfoWindowAdapter(this, reports);

        mMap.setInfoWindowAdapter(mAdapter);

        ref.child("sourceReports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    SourceReport report = postSnapshot.getValue(SourceReport.class);
                    reports.add(report);
                    addMapMarker(report);
                }
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
                            if(user.getUserType().equals("Worker")
                                    || user.getUserType().equals("Manager")) {
                                ref.child("purityReports").addValueEventListener(
                                        new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot postSnapshot: dataSnapshot
                                                .getChildren()) {
                                            PurityReport report = postSnapshot.getValue(
                                                    PurityReport.class);
                                            reports.add(report);
                                            addMapMarker(report);
                                        }
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

    /**
     * Add the Marker onto the Map
     *
     * @param report the report that is used to construct the pin
     */
    private void addMapMarker(Report report) {
        int comma = report.getLocation().indexOf(',');
        if (comma >= 0) {
            double lat = Double.parseDouble(report.getLocation().substring(0, comma));
            double lng = Double.parseDouble(report.getLocation().substring(comma + 1, report.getLocation().length()));
            LatLng location = new LatLng(lat, lng);
            report.setLatlng(location);
            String title = (report.getClass().equals(PurityReport.class)) ? "Purity Report" : "Source Report";
            Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(title));
            markers.add(marker);
            updateMap();
        }
    }

    /**
     * Update the view of the map when the user shifts the view
     */
    private void updateMap() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int padding = 200; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.moveCamera(cu);
    }

    @Override
    public void onBackPressed() {
        Intent next = new Intent(this, HomeScreenActivity.class);
        startActivity(next);
        finish();
    }
}
