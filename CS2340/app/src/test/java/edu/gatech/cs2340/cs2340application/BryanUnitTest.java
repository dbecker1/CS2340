package edu.gatech.cs2340.cs2340application;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.cs2340application.controller.RegistrationActivity;


import static java.security.AccessController.getContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test testing registration activity input
 *
 * @version 1.0
 * @author Bryan Zhang
 */

public class BryanUnitTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testUsername() {
        RegistrationActivity activity = new RegistrationActivity();
        int input = activity.checkValidInputs("", "", "", "");
        assertEquals(1, input);

    }

    @Test
    public void testConfirmPassword() {
        RegistrationActivity activity = new RegistrationActivity();
        int input = activity.checkValidInputs("bzhang57@gmail.com", "BigZ57", "BigZ58", "");
        assertEquals(2, input);
    }

    @Test
    public void testPassword() {
        RegistrationActivity activity = new RegistrationActivity();
        int input = activity.checkValidInputs("bzhang57@gmail.com", "", "", "");
        assertEquals(3, input);
    }

    @Test
    public void testId() {
        RegistrationActivity activity = new RegistrationActivity();
        int input = activity.checkValidInputs("bzhang57@gmail.com", "BigZ57", "BigZ57", "");
        assertEquals(4, input);
    }

    @Test
    public void passTest() {
        RegistrationActivity activity = new RegistrationActivity();
        int input = activity.checkValidInputs("bzhang57@gmail.com", "BigZ57", "BigZ57", "903159173");
        assertEquals(0, input);
    }
}
