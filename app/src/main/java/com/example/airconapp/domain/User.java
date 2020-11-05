package com.example.airconapp.domain;

import java.util.HashSet;

public class User
{
    User() {}

    public void register(String name, String password1, String password2) {
        /* Searches if the name already exists (it must be unique tο be accepted) */
        for (RegisteredUser rU : Utilities.getRegisteredUsers()) {
            if (rU.getName().equals(name)) {
                //System.out.println("This name already exists.");
                return;
            }
        }

        if (password1.equals(password2))
            registerUser(name, password1);
    }

    public void registerUser(String name, String password) {
        Utilities.getRegisteredUsers().add(new RegisteredUser(name, password));
    }

    public User login(String name, String password) {
        for (RegisteredUser ru : Utilities.getRegisteredUsers()) {
            if (ru.getName().equals(name) && ru.getPassword().equals(password)) {
                Utilities.getLoggedInUsers().add(ru);
                return ru;
            }
        }

        //System.out.println("Login failed due to wrong credentials.");
        return null;
    }

    public void logout() {
        logoutUser();
        User newUser = new User();
        Utilities.setUser(newUser);
        //System.out.println("You have logged out.");
    }

    /* Is used for overriding */
    protected void logoutUser() {}

    public HashSet<AirCon> search() {
        //HashSet<AirCon> rtemp = new HashSet<>();
        HashSet<AirCon> result = new HashSet<>(Utilities.getAirCons());

        return result;
    }
}