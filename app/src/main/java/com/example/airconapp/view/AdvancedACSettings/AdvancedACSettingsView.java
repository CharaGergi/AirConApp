package com.example.airconapp.view.AdvancedACSettings;

import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;

public interface AdvancedACSettingsView
{
    public ScrollView getAirCons();
    public void setAirCons(ScrollView value);

    public String getAirConHTimer();
    public void setAirConHTimer(String value);

    public String getAirConMTimer();
    public void setAirConMTimer(String value);

    public String getAirConHTimerOff();
    public void setAirConHTimerOff(String value);

    public String getAirConMTimerOff();
    public void setAirConMTimerOff(String value);

    public SeekBar getAirConAirDens();
    public void setAirConAirDens(SeekBar value);

    public Switch getSleepSwitch();
    public void setSleepSwitch(Switch value);

    public Switch getSilentSwitch();
    public void setSilentSwitch(Switch value);

    public Switch getApplyAll();
    public void setApplyAll(Switch value);
}
