package com.example.airconapp.domain;

import java.util.HashSet;

public class RegisteredUser extends User
{
    private String name;
    private String password;

    public RegisteredUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void logoutUser() {
        Utilities.getLoggedInUsers().remove(this);
    }

    public HashSet<AirCon> manualSearch()
    {
        HashSet<AirCon> result = new HashSet<>(Utilities.getAirCons());

        return result;
    }
}