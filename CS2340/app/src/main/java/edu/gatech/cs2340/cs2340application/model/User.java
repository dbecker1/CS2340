package edu.gatech.cs2340.cs2340application.model;

/**
 * Created by bzhang345 on 2/20/17.
 */

public class User {
    protected String emailAddress;
    protected String userType;
    protected String address;

    public User() {

    }

    public User(String emailAddress, String userType, String address) {
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
