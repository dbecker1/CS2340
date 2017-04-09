package edu.gatech.cs2340.cs2340application;

import edu.gatech.cs2340.cs2340application.controller.LoginProviderInterface;
import edu.gatech.cs2340.cs2340application.controller.LoginResultInterface;

/**
 *
 * @version 1.0
 * @author Daniel Becker
 */

public class MockLoginProvider implements LoginProviderInterface {

    @Override
    public void login(String username, String password, LoginResultInterface handler) {
        if (username.equals("test@test.com") && password.equals("password")) {
            handler.success();
        } else {
            handler.failure("Incorrect username / password combination.");
        }
    }
}
