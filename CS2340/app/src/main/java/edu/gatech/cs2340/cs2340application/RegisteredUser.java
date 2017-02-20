package edu.gatech.cs2340.cs2340application;

/**
 * Created by bzhang345 on 2/20/17.
 */

public class RegisteredUser extends User {

    public RegisteredUser(String username, String password, String id) {
        super();
        this.username = username;
        this.password = password;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Registered User: My username is: " + username + "\nmy password is: " + password
                + "\nmy ID is: " + id;
    }
}


