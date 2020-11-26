package com.example.airconapp.domain;

import java.util.ArrayList;

public class Utilities
{
    private static User user;
    private static ArrayList<AirCon> foundAirCons;

    public Utilities() {
        user = new User();
        foundAirCons = new ArrayList<>();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Utilities.user = user;
    }

    public static ArrayList<AirCon> getAirCons() {
        return foundAirCons;
    }
}