package com.example.airconapp.view.AirCon;

import android.widget.Button;
import android.widget.EditText;

public interface AirConView
{
    public String getAirConName();

    public EditText getAirConTemp();

    public Button getHeat();

    public Button getCold();

    public Button getAuto();

    public Button getHumid();

    public Button getFan();

    public Button getMode();

    public void setAirConName(String value);

    public void setAirConTemp(EditText value);

    public void setMode();
}
