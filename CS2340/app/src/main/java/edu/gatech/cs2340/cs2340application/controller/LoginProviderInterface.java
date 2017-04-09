package edu.gatech.cs2340.cs2340application.controller;

/**
 * Created by dbeckerfl on 4/8/17.
 */

public interface LoginProviderInterface {
    public void login(String username, String password, LoginResultInterface handler);
}
