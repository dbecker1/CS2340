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

    /**
     * Returns the report number of a water report
     *
     * @return the report number of a water report
     */
    public String getReportNumber() {
        return reportNumber;
    }

    /**
     * Set the report number for a water report
     * @param reportNumber String representation of a water report number
     */
    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    /**
     * Return the date and time of a water report
     *
     * @return the date and time attributes of a water report
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Set the date and time attribute of a water report
     *
     * @param dateTime the date and time that of when the report is written/created
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Return the User ID of the User who submitted/written the report
     *
     * @return the String representation of the User ID of the user that submitted the report
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set the User ID of the user who submitted the report
     *
     * @param userId a String containing the User ID of the User who submitted the report
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Return the location of the water that was being reported on in the water reported
     *
     * @return a String representation of the location of the water being reported.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the location of the water that was being reported on in the water reported
     *
     * @param location a String representation of the location of the water being reported.
     */
    public void setLocation(String location) {
        this.location = location;
    }

}
