package com.example.airconapp.view.AirCon;

import android.widget.Button;

public interface AirConView
{
    public String getAirConName();

    public String getAirConTemp();

    public Button getHeat();

    public Button getCold();

    public Button getAuto();

    public Button getHumid();

    public Button getFan();

    public void setAirConName(String value);

    public void setAirConTemp(int value);

    public void setMode(int value);
}
