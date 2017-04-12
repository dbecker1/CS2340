package edu.gatech.cs2340.cs2340application.controller;

/**
 *
 * @version 1.0
 * @author Daniel Becker
 */

public interface LoginResultInterface {
    void success();
    void failure(String failureMessage);
}
