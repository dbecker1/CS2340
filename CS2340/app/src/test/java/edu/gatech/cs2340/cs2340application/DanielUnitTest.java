package edu.gatech.cs2340.cs2340application;

import android.test.mock.MockApplication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.cs2340application.controller.HistoricalReportService;
import edu.gatech.cs2340.cs2340application.controller.LoginActivity;
import edu.gatech.cs2340.cs2340application.controller.LoginResultInterface;
import edu.gatech.cs2340.cs2340application.controller.ReportDataInterface;

import static java.security.AccessController.getContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DanielUnitTest {

    MockLoginProvider provider;

    @Before
    public void setUp() {
        provider = new MockLoginProvider();
    }

    @Test
    public void testLogin() {
        LoginActivity activity = new LoginActivity();
        LoginResultInterface result = new LoginResultInterface() {
            @Override
            public void success() {

            }

            @Override
            public void failure(String failureMessage) {
                Assert.fail("Login test failed: " + failureMessage);
            }
        };
        activity.login("test@test.com", "password", provider, result);
    }

    @Test
    public void testInvalidEmail() {
        LoginActivity activity = new LoginActivity();
        LoginResultInterface result = new LoginResultInterface() {
            @Override
            public void success() {
                Assert.fail("Login test failed. Gave successful login result.");
            }

            @Override
            public void failure(String failureMessage) {
                if (!failureMessage.equals("Username must be valid email.")) {
                    Assert.fail("Login test failed. Gave wrong reason: " + failureMessage);
                }
            }
        };
        activity.login("test", "password", provider, result);
    }

    @Test
    public void testEmptyUsername() {
        LoginActivity activity = new LoginActivity();
        LoginResultInterface result = new LoginResultInterface() {
            @Override
            public void success() {
                Assert.fail("Login test failed. Gave successful login result.");
            }

            @Override
            public void failure(String failureMessage) {
                if (!failureMessage.equals("Username cannot be empty.")) {
                    Assert.fail("Login test failed. Gave wrong reason: " + failureMessage);
                }
            }
        };
        activity.login("", "password", provider, result);
    }

    @Test
    public void testEmptyPassword() {
        LoginActivity activity = new LoginActivity();
        LoginResultInterface result = new LoginResultInterface() {
            @Override
            public void success() {
                Assert.fail("Login test failed. Gave successful login result.");
            }

            @Override
            public void failure(String failureMessage) {
                if (!failureMessage.equals("Password cannot be empty.")) {
                    Assert.fail("Login test failed. Gave wrong reason: " + failureMessage);
                }
            }
        };
        activity.login("test@test.com", "", provider, result);
    }
}