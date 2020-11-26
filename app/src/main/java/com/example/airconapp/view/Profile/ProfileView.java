package com.example.airconapp.view.Profile;

import android.widget.ListView;

public interface ProfileView
{
    public ListView getAirCons();
    public void setAirCons(ListView value);

    public String getAirConName();
    public void setAirConName(String value);
}
