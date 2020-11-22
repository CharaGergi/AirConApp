package com.example.airconapp.view.AdvancedACSettings;

import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar;

public interface AdvancedACSettingsView {

    public ScrollView getAirCons();
    public void setAirCons(ScrollView value);

    public EditText getAirConHTimer();
    public void setAirConHTimer(EditText value);

    public EditText getAirConMTimer();
    public void setAirConMTimer(EditText value);

    public EditText getAirConHTimerOff();
    public void setAirConHTimerOff(EditText value);

    public EditText getAirConMTimerOff();
    public void setAirConMTimerOff(EditText value);

    public SeekBar getAirConAirDens();
    public void setAirConAirDens(SeekBar value);
}
