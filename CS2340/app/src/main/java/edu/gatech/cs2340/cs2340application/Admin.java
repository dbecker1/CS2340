package edu.gatech.cs2340.cs2340application;

/**
 * Created by bzhang345 on 2/20/17.
 */

public class Admin extends User {
    public Admin(String username, String password, String id) {
        super();
        this.username = username;
        this.password = password;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Admin: My username is: " + username + "\nmy password is: " + password
                + "\nmy ID is: " + id;
    }
}
