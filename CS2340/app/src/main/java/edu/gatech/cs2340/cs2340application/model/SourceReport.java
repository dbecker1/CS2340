package edu.gatech.cs2340.cs2340application.model;

import edu.gatech.cs2340.cs2340application.model.Report;

/**
 * Created by dbeckerfl on 2/27/17.
 */

public class SourceReport extends Report {

    private String type;
    private String condition;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
