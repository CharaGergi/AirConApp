package com.example.airconapp.domain;

import java.util.HashSet;

public class Profile
{
    private int fontSize; // 0 = small, 1 = medium, 2 = large
    private boolean speechCommands = true;
    private boolean soundCommands = true;

    Profile() {}

    public Profile(int fontSize, boolean speechCommands, boolean soundCommands) {
        this.fontSize = fontSize;
        this.speechCommands = speechCommands;
        this.soundCommands = soundCommands;
    }

    public int getFontSize() {
        return fontSize;
    }

    public boolean isSpeechCommands() {
        return speechCommands;
    }

    public boolean isSoundCommands() {
        return soundCommands;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setSpeechCommands(boolean speechCommands) {
        this.speechCommands = speechCommands;
    }

    public void setSoundCommands(boolean soundCommands) {
        this.soundCommands = soundCommands;
    }

    public HashSet<AirCon> search() {
        HashSet<AirCon> result = new HashSet<>(Utilities.getAirCons());

        return result;
    }
}