package edu.gatech.cs2340.cs2340application.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.PurityReport;
import edu.gatech.cs2340.cs2340application.model.Report;

/**
 * Displaying reports
 *
 * @version 1.0
 * @author Daniel Becker
 */

class ReportAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final ArrayList<Report> mDataSource;

    /**
     * Generates the data source for the list view.
     *
     * @param context The current state of the application 
     * @param items ArrayList containing all the reports submitted
     */
     ReportAdapter(Context context, ArrayList<Report> items) {
        mDataSource = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = convertView;
        if (rowView == null) {
            rowView = mInflater.inflate(R.layout.report_info_layout, parent, false);
        }

        Report report = mDataSource.get(position);

        ((TextView) rowView.findViewById(R.id.location)).setText(report.getLocation());
        ((TextView) rowView.findViewById(R.id.submittedBy)).setText(report.getUserId());
        ((TextView) rowView.findViewById(R.id.date)).setText(report.getDateTimeString());
        boolean isPurity = report.getClass().equals(PurityReport.class);
        ((TextView) rowView.findViewById(R.id.reportType)).setText((isPurity) ? "Purity" : "Source");
        return rowView;
    }
}
