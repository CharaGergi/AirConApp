package com.example.airconapp.view.AirCon;

import android.widget.EditText;
import android.widget.ListView;

public interface AirConView {

    public String getAirConName();
    public void setAirConName(String value);

    public EditText getAirConTemp();
    public void setAirConTemp(EditText value);
}
