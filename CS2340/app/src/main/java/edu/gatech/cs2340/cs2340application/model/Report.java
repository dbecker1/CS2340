package edu.gatech.cs2340.cs2340application.model;

import java.util.Date;

/**
 * Created by dbeckerfl on 2/27/17.
 */

public class Report {
    private Date dateTime;
    private String reportNumber;
    private String userId;
    private String location;


    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
