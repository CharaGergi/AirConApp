package com.example.airconapp.domain;

import java.util.ArrayList;

public class Utilities
{
    private static Profile profile;
    private static ArrayList<AirCon> foundAirCons;

    public Utilities() {
        profile = new Profile();
        foundAirCons = new ArrayList<>();
    }

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        Utilities.profile = profile;
    }

    public static ArrayList<AirCon> getAirCons() {
        return foundAirCons;
    }
}