package edu.gatech.cs2340.cs2340application.model;

import edu.gatech.cs2340.cs2340application.model.Report;

/**
 * Created by dbeckerfl on 2/27/17.
 */

public class PurityReport extends Report {
    private String condition;
    private Double virusPPM;
    private Double contaminantPPM;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getVirusPPM() {
        return virusPPM;
    }

    public void setVirusPPM(Double virusPPM) {
        this.virusPPM = virusPPM;
    }

    public Double getContainmentPPM() {
        return contaminantPPM;
    }

    public void setContainmentPPM(Double containmentPPM) {
        this.contaminantPPM = containmentPPM;
    }
}
