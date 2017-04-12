package edu.gatech.cs2340.cs2340application.model;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bryan Zhang and modified for email verification by Cliff Panos
 *
 * @version 1.0
 * @author Bryan Zhang
 * @author Cliff Panos
 */

public class User {

    private String emailAddress;
    private String userType;
    private String address;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
     * @return whether or not the email was valid and was then properly set
     */
    public boolean setEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.length() == 0) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailAddress);
        if (matcher.matches()) {
            this.emailAddress = emailAddress;
            return true;
        }
        return false;
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
    public boolean setUserType(String userType) {

        if (userType == null || userType.length() == 0) {
            return false;
        }
        if (userType.compareToIgnoreCase("User") == 0
                || userType.compareToIgnoreCase("Worker") == 0
                || userType.compareToIgnoreCase("Manager") == 0
                || userType.compareToIgnoreCase("Administrator") == 0) {
            this.userType = userType;
            return true;
        }
        return false;
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
