import edu.gatech.cs2340.cs2340application.model.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        user = new User();
    }


    //JUnit @Test cases below: ---------------------------------------------- //


    @Test(timeout = TIMEOUT)
    public void testConstructedProperly() {

        assertEquals(null, user.getEmailAddress());

        User newUser = new User("email@address.com", "userType", "address");
        assertEquals("email@address.com", newUser.getEmailAddress());

    }


    @Test(timeout = TIMEOUT)
    public void testNullEmail() {
        assertFalse(user.setEmailAddress(null));
    }

    @Test(timeout = TIMEOUT)
    public void testEmptyEmail() {
        assertFalse(user.setEmailAddress(""));
    }


    @Test(timeout = TIMEOUT)
    public void testValidEmailAddresses() {

        String[] validEmails
                = {"cliffpanos1@gmail.com", "adachille34@gatech.edu",
                "weppy@yahoo.com", "123@w3.edu", "kathytrangatech@cfl.rr.com"};
        for (String email : validEmails) {
            assertTrue(user.setEmailAddress(email));
        }

    }

    @Test(timeout = TIMEOUT)
    public void testNoEmailAtSymbol() {

        String[] noAtSymbolEmails = {"cliffpanosGmail.com",
                "adachille34.gatech.edu", "weppyYahoo.com", "1.2.3.w3.edu",
                "kathyTranGatech.cfl.rr.com"};
        for (String email : noAtSymbolEmails) {
            assertFalse(user.setEmailAddress(email));
        }

    }

    @Test(timeout = TIMEOUT)
    public void testMisplacedAtSymbols() {

        String[] misplacedAtSymolEmails = {"@cliffpanosGmail.com",
                "wepp@y@yahoo@y.com", "clifpanos@gmail.com@",
                "kathyTran.gmail.com@", "123@@w3schools.com"};
        for (String email : misplacedAtSymolEmails) {
            assertFalse(user.setEmailAddress(email));
        }

    }

    @Test(timeout = TIMEOUT)
    public void testOnlyAnAtSymbol() {
        assertFalse(user.setEmailAddress("@"));
    }

    @Test(timeout = TIMEOUT)
    public void testAllNumbers() {
        assertFalse(user.setEmailAddress("1234567890"));
    }

    @Test(timeout = TIMEOUT)
    public void testNoDomainName() {

        String[] noDomainNames = {"cliffpanos@gmailcom", "adachille3@gatechEdu",
                "weppy@yahooCom", "kTran@gmailCom"};

        for (String email: noDomainNames) {
            assertFalse(user.setEmailAddress(email));
        }

    }

    @Test(timeout = TIMEOUT)
    public void testMoreDomainDots() {

        String[] doubleDotEmails = {"cliffpanos@gmail..com",
                "adachille3@.gatech.edu", "ktran44@yahoo.com..",
                "weppy@cfl...rr...com"};

        for (String email : doubleDotEmails) {
            assertFalse(user.setEmailAddress(email));
        }

    }

    @Test(timeout = TIMEOUT)
    public void testSpecialCharacters() {

        String[] specialEmails = {"cliffü@gmail.com", "ktran44@yahoö.com",
                "wepperson@gátech.edu", "ΔX@gtdeltachi.com"};

        for (String email: specialEmails) {
            assertFalse(user.setEmailAddress(email));
        }

    }


}
