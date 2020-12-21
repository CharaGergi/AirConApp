package com.example.airconapp.view.Profile;

import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

public interface ProfileView
{
    public Button getSmallFontButton();
    public Button getMediumFontButton();
    public Button getBigFontButton();

    public Switch getMicSwitch();
    public Switch getSpeech();
}
