package com.example.airconapp.domain;

public class RegisteredUser extends User
{
    private String name;
    private String password;
    private String priority;

    public RegisteredUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    RegisteredUser(String name, String password, String priority) {
        this.name = name;
        this.password = password;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPriority() {
        return priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    protected void logoutUser() {
        Utilities.getLoggedInUsers().remove(this);
    }
}