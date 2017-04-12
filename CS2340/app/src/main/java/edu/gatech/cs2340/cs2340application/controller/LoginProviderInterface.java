package edu.gatech.cs2340.cs2340application.controller;


/**
 *
 * @version 1.0
 * @author Daniel Becker
 */

public interface LoginProviderInterface {
    void login(String username, String password, LoginResultInterface handler);
}
