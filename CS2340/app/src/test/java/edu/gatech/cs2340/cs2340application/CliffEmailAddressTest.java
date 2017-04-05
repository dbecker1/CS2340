import edu.gatech.cs2340.cs2340application.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Basic coverage of JUnit tests for the setEmailAddress(String:) method in User
 *
 * @version 1.0
 * @author Clifford Panos
 */
public class CliffEmailAddressTest {

    private static User user;
    public static final int TIMEOUT = 500;

    @Before
    public void setup() {
        user = new User()
    }

    @Test(timeout = TIMEOUT)
    public void testConstructedProperly() {
        assertEquals("", user.getEmailAddress());
    }

    @Test(timeout = TIMEOUT)
    public void test() {

    }

    @Test(timeout = TIMEOUT)
    public void test() {

    }

    @Test(timeout = TIMEOUT)
    public void test() {

    }

    @Test(timeout = TIMEOUT)
    public void test() {

    }

}
