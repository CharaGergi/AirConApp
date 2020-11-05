package com.example.airconapp.domain;

public class RegisteredUser extends User
{
    private String name;
    private String password;
    private String priority;
    private int fontSize; // 0 = small, 1 = medium, 2 = large
    private boolean speechCommands;

    RegisteredUser(String name, String password, String priority, int fontSize, boolean speechCommands) {
        this.name = name;
        this.password = password;
        this.priority = priority;
        this.fontSize = fontSize;
        this.speechCommands = speechCommands;
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

    public int getFontSize() {
        return fontSize;
    }

    public boolean isSpeechCommands() {
        return speechCommands;
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

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setSpeechCommands(boolean speechCommands) {
        this.speechCommands = speechCommands;
    }

    @Override
    protected void logoutUser() {
        Utilities.getLoggedInUsers().remove(this);
    }
}