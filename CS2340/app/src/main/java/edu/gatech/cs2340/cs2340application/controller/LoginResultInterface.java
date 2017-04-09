package edu.gatech.cs2340.cs2340application.controller;

/**
 * Created by dbeckerfl on 4/8/17.
 */

public interface LoginResultInterface {
    public void success();
    public void failure(String failureMessage);
}
