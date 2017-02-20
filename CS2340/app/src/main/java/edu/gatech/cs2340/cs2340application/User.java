package edu.gatech.cs2340.cs2340application;

/**
 * Created by bzhang345 on 2/20/17.
 */

public abstract class User {
    protected String username;
    protected String password;
    protected String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "User: My username is: " + username + "\nmy password is: " + password
                + "\nmy ID is: " + id;
    }
}
