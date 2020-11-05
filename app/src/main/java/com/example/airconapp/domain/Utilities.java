package com.example.airconapp.domain;

import java.util.ArrayList;

public class Utilities
{
    private static User user;
    private static ArrayList<RegisteredUser> registeredUsers;
    private static ArrayList<RegisteredUser> loggedInUsers;
    private static ArrayList<AirCon> foundAirCons;

    public Utilities() {
        user = new User();
        registeredUsers = new ArrayList<>();
        loggedInUsers = new ArrayList<>();
    }

    public static User getUser() {
        return user;
    }

    public static ArrayList<RegisteredUser> getRegisteredUsers() {
        return registeredUsers;
    }

    public static ArrayList<RegisteredUser> getLoggedInUsers() {
        return loggedInUsers;
    }

    public static void setUser(User user) {
        Utilities.user = user;
    }

    public static void setRegisteredUsers(ArrayList<RegisteredUser> registeredUsers) {
        Utilities.registeredUsers = registeredUsers;
    }

    public static void setLoggedInUsers(ArrayList<RegisteredUser> loggedInUsers) {
        Utilities.loggedInUsers = loggedInUsers;
    }

    public static ArrayList<AirCon> getAirCons() {
        return foundAirCons;
    }
}