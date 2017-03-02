package edu.gatech.cs2340.cs2340application.model;

import edu.gatech.cs2340.cs2340application.model.Report;

/**
 * Created by dbeckerfl on 2/27/17.
 */

public class SourceReport extends Report {

    private String type;
    private String condition;

    /**
     * Returns a String representation of the type of water being reported in the water source
     * report
     *
     * @return a String representation of the type of water being reported.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of water being reported in the water source report.
     *
     * @param type a String representation of the type of water reported.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the condition of the water from the water report
     *
     * @return a String representation of the condition of the water from the water report
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the condition of the water for the water report
     *
     * @param condition a String representation of the condition of the water from the water report
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
}
