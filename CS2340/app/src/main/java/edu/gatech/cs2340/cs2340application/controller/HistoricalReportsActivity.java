package edu.gatech.cs2340.cs2340application.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.*;

import edu.gatech.cs2340.cs2340application.R;

public class HistoricalReportsActivity extends AppCompatActivity {

    private boolean isVirus;
    private XYPlot plot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_reports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String location = getIntent().getExtras().getString("location");
        String year = getIntent().getExtras().getString("year");
        String type = getIntent().getExtras().getString("typeOfContaminant");
        isVirus = type.equals("virus");
        //String location = "33.77,-84.3963";

        HistoricalReportService service = new HistoricalReportService();
        service.getReportData(isVirus, location, year, new ReportDataInterface() {
            @Override
            public void foundData(Number[] data) {
                createGraph(data);
            }
        });


    }

    protected void createGraph(Number[] data) {
        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.plot);

        // create a couple arrays of y-values to plot:

        final String[] domainLabels = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        //Number[] seriesNumbers = {1, 4, 2, 8, 4, 16, 8, 32, 16, 64};

        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)

        XYSeries series = new SimpleXYSeries(
                Arrays.asList(data), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Data");

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter seriesFormat =
                new LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels);

        // add an "dash" effect to the series2 line:
        seriesFormat.getLinePaint().setPathEffect(new DashPathEffect(new float[]{

                // always use DP when specifying pixel sizes, to keep things consistent across devices:
                PixelUtils.dpToPix(20),
                PixelUtils.dpToPix(15)}, 0));

        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        seriesFormat.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        String title = (isVirus) ? "Virus PPM" : "Contaminant PPM";
        // add a new series' to the xyplot:
        plot.clear();

        plot.setTitle(title);
        plot.addSeries(series, seriesFormat);
        plot.redraw();

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(domainLabels[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });
    }
}
