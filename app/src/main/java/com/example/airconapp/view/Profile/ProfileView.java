package com.example.airconapp.view.Profile;

import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

public interface ProfileView
{
    public ListView getAirCons();
    public void setAirCons(ListView value);

    public String getAirConName();
    public void setAirConName(String value);

    public Button getSmallFontButton();
    public Button getMediumFontButton();
    public Button getBigFontButton();

    public Switch getMicSwitch();
    public Switch getSpeech();
}
