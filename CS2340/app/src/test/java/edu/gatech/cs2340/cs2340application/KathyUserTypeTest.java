package edu.gatech.cs2340.cs2340application;

import edu.gatech.cs2340.cs2340application.model.User;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * JUnit test testing setting user type in User
 *
 * @version 1.0
 * @author Kathy Tran
 */

public class KathyUserTypeTest {
    private static final int TIMEOUT = 200;
    private User mockUser;

    @Before
    public void setUp() {
        mockUser = new User();
    }

    @Test (timeout = TIMEOUT)
    public void testingNullUserType() {
        assertFalse(mockUser.setUserType(null));
        assertNull(mockUser.getUserType());
    }

    @Test (timeout = TIMEOUT)
    public void testingEmptyInput() {
        assertFalse(mockUser.setUserType(""));
        assertNull(mockUser.getUserType());
    }

    @Test (timeout = TIMEOUT)
    public void testingInvalidType() {
        assertFalse(mockUser.setUserType("Admin"));
        assertNull(mockUser.getUserType());

        assertFalse(mockUser.setUserType("Student"));
        assertNull(mockUser.getUserType());

    }

    @Test (timeout = TIMEOUT)
    public void testingValidInput() {
        assertTrue(mockUser.setUserType("Administrator"));
        assertEquals("Administrator", mockUser.getUserType());

        assertTrue(mockUser.setUserType("uSer"));

        assertTrue(mockUser.setUserType("manager"));
        assertEquals("manager", mockUser.getUserType());

        assertTrue(mockUser.setUserType("worker"));
        assertEquals("worker", mockUser.getUserType());
    }
}
