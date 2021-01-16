package com.example.airconapp.view.AirCon;

import com.example.airconapp.domain.AirCon;

public class AirConPresenter
{
    private AirConView view;
    private AirCon airCon;

    public AirConPresenter(AirConView view, AirCon airCon) {
        this.view = view;
        this.airCon = airCon;
    }

    public void onSetMode(int num)
    {
        if (num == 0)
        {
            airCon.setMainMode(0);
        }
        if (num == 1)
        {
            airCon.setMainMode(1);
        }
        if (num == 2)
        {
            airCon.setMainMode(2);
        }
        if (num == 3)
        {
            airCon.setMainMode(3);
        }
        if (num == 4)
        {
            airCon.setMainMode(4);
        }
    }

    public void onChangeTemp()
    {
        airCon.setTemperature(Integer.parseInt(view.getAirConTemp()));
    }

    public void onIncreaseTempBtn() { airCon.setTemperature(airCon.getTemperature() + 1); }

    public void onDecreaseTempBtn()
    {
        airCon.setTemperature(airCon.getTemperature() - 1);
    }

    public void onIncreaseTilt()
    {
        airCon.setTilt(airCon.getTilt()+1);
    }

    public void onDecreaseTilt()
    {
        airCon.setTilt(airCon.getTilt()-1);
    }
}
