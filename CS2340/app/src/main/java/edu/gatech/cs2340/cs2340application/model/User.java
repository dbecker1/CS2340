package edu.gatech.cs2340.cs2340application.model;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.HashMap;

import edu.gatech.cs2340.cs2340application.R;

/**
 * Created by bzhang345 on 2/20/17.
 */

public class User {
    protected String emailAddress;
    protected String userType;
    protected String address;

    /**
     * Overloading with a constructor of the User class with no assignment to the instance
     * variables
     */
    public User() {
    }

    /**
     * Creates a user class with the user's email address, account type, and address
     *
     * @param emailAddress a String representation of the user's email address
     * @param userType a String representation of the user account type
     * @param address a String representation of the user's address
     */
    public User(String emailAddress, String userType, String address) {
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.address = address;
    }

    /**
     * Returns the user's email address
     *
     * @return a String representation of the user's email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the user's email address
     *
     * @param emailAddress a String representation of the user's email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the account type of the user
     *
     * @return A String representation of the the user account type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the account type of the user
     *
     * @param userType a String representation of the the user account type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Returns the address of the user
     *
     * @return a String representation of the user's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the user
     *
     * @param address a String representation of the user's address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
