package edu.gatech.cs2340.cs2340application;

import edu.gatech.cs2340.cs2340application.controller.LoginProviderInterface;
import edu.gatech.cs2340.cs2340application.controller.LoginResultInterface;

/**
 * Created by dbeckerfl on 4/8/17.
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
