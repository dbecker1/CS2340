package edu.gatech.cs2340.cs2340application;

import org.junit.Test;
import org.junit.Before;

import edu.gatech.cs2340.cs2340application.model.PurityReport;

import static org.junit.Assert.assertEquals;

/**
 *
 * @version 1.0
 * @author Anthony D'achille
 */

public class PurityReportTests {
    private static final int TIMEOUT = 500;
    private PurityReport pr;

    @Before
    public void setUp() {
        pr = new PurityReport();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void negativePPMThrowsException() {
        pr.setContainmentPPM(-1.0);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void PPMLargerThanMAXPPMThrowsException() {
        pr.setContainmentPPM(1000001.0);
    }
    @Test(timeout = TIMEOUT)
    public void setContainmentPPM() {
        pr.setContainmentPPM(0.0);
        assertEquals((Double) 0.0, pr.getContainmentPPM());
        pr.setContainmentPPM(100000.0);
        assertEquals((Double) 100000.0, pr.getContainmentPPM());
    }
}
