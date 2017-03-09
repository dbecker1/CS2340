package edu.gatech.cs2340.cs2340application.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.PurityReport;
import edu.gatech.cs2340.cs2340application.model.Report;

/**
 * Created by dbeckerfl on 3/5/17.
 */

public class ReportInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Report> mDataSource;

    /**
     * Generates the data source for the map.
     *
     * @param context the state of the application
     * @param items the array list of reports that is to be represented on the map.
     */
    public ReportInfoWindowAdapter(Context context, ArrayList<Report> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getInfoWindow(Marker marker) {
        // Get view for row item
        View infoView = mInflater.inflate(R.layout.report_info_layout, null);

        Report report = null;
        for (Report r : mDataSource) {
            if(r.getLatlng().equals(marker.getPosition())) {
                report = r;
            }
        }

        ((TextView) infoView.findViewById(R.id.location)).setText(report.getLocation());
        ((TextView) infoView.findViewById(R.id.submittedBy)).setText(report.getUserId());
        ((TextView) infoView.findViewById(R.id.date)).setText(report.getDateTime().toString());
        boolean isPurity = report.getClass().equals(PurityReport.class);
        ((TextView) infoView.findViewById(R.id.reportType)).setText((isPurity) ? "Purity" : "Source");
        return infoView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
