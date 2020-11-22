package com.example.airconapp.domain;

import java.util.HashSet;

public class User
{
    private int fontSize; // 0 = small, 1 = medium, 2 = large
    private boolean speechCommands;

    User() {}

    public User(int fontSize, boolean speechCommands) {
        this.fontSize = fontSize;
        this.speechCommands = speechCommands;
    }

    public int getFontSize() {
        return fontSize;
    }

    public boolean isSpeechCommands() {
        return speechCommands;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setSpeechCommands(boolean speechCommands) {
        this.speechCommands = speechCommands;
    }

    public HashSet<AirCon> search() {
        //HashSet<AirCon> rtemp = new HashSet<>();
        HashSet<AirCon> result = new HashSet<>(Utilities.getAirCons());

        return result;
    }
}