package edu.gatech.cs2340.cs2340application.model;


/**
 *
 * @version 1.0
 * @author Daniel Becker
 */

public class PurityReport extends Report {
    private String condition;
    private Double virusPPM;
    private Double contaminantPPM;
    private static final int MAXPPM = 1000000;

    /**
     * Return the condition attribute contained inside a water purity report
     *
     * @return the condition of the water reported in a water purity report
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Set the condition for the water being reported in a water purity report
     *
     * @param condition The condition of water: Waste, Treatable-Clear, Treatable-Muddy, Potable
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Return the Virus PPM data of a water purity report
     * @return The number of virus in parts-per-million measuring how much virus there are in the
     * water
     */
    public Double getVirusPPM() {
        return virusPPM;
    }

    /**
     * Set the virus PPM data for a water purity report
     *
     * @param virusPPM The number of virus in parts-per-million measuring how much virus there are
     * in the water
     */
    public void setVirusPPM(Double virusPPM) {
        this.virusPPM = virusPPM;
    }

    /**
     * Return the contaminant PPM data of a water purity report.
     *
     * @return the number of contaminant in parts-per-million measuring how much contaminant there
     * are in the water
     */
    public Double getContainmentPPM() {
        return contaminantPPM;
    }

    /**
     * Set the contaminant PPM data for a water purity report
     *
     * @param containmentPPM The number of contaminant in parts-per-million measuring how much
     * contaminant there are in the water
     */
    public void setContainmentPPM(Double containmentPPM) {
        if (containmentPPM < 0) {
            throw new IllegalArgumentException("Contaminant PPM cannot be negative");
        } else if (containmentPPM > MAXPPM){
            throw new IllegalArgumentException("Contaminant parts per million cannot " +
                    "be greater than one million");
        }
        this.contaminantPPM = containmentPPM;
    }
}
