package com.example.airconapp.domain;

import java.util.HashSet;

public class Utilities
{
    private static HashSet<AirCon> foundAirCons;
    private static HashSet<AirCon> selectedAirCons;
    private static AirCon airCon1, airCon2, airCon3, airCon4;

    public Utilities() {
        foundAirCons = new HashSet<>();
        selectedAirCons = new HashSet<>();
        airCon1 = new AirCon("ToyotomiBed", 1 , 25, 2, 2, false, false, 0 , 3, true);
        airCon2 = new AirCon("ToyotomiKit", 3, 20, 1, 3, false, true, 4 , 0, true);
        airCon3 = new AirCon("ToyotomiLiv", 1, 14, 0, 1, true, false, 4 , 0, false);
        airCon4 = new AirCon("ToyotomiBed2", 4, 19, 3, 0, false, false, 4 , 0, false);
        foundAirCons.add(airCon1);
        foundAirCons.add(airCon2);
        foundAirCons.add(airCon3);
        foundAirCons.add(airCon4);
    }

    public static HashSet<AirCon> getAirCons() {
        return foundAirCons;
    }

    public static void setAirCons(HashSet foundAirCons) { Utilities.foundAirCons = foundAirCons;}

    public static HashSet<AirCon> getSelectedAirCons() { return selectedAirCons; }

    public static void setSelectedAirCons(HashSet<AirCon> selectedAirCons) { Utilities.selectedAirCons = selectedAirCons;}
}