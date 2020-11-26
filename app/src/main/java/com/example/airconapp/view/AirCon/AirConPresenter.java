package com.example.airconapp.view.AirCon;

import com.example.airconapp.domain.AirCon;

public class AirConPresenter
{
    private AirConView view;
    private AirCon airCon;

    public AirConPresenter(AirConView view) {
        this.view = view;
    }

    public void onSetMode()
    {
        if (view.getHeat().isPressed())
        {
            airCon.setMainMode(0);
        }
        if (view.getCold().isPressed())
        {
            airCon.setMainMode(1);
        }
        if (view.getAuto().isPressed())
        {
            airCon.setMainMode(2);
        }
        if (view.getHumid().isPressed())
        {
            airCon.setMainMode(3);
        }
        if (view.getFan().isPressed())
        {
            airCon.setMainMode(4);
        }
    }

    public void onChangeTemp()
    {
        airCon.setTemperature(Integer.parseInt(view.getAirConTemp().getText().toString()));
    }

    public void onIncreaseTempBtn()
    {
        airCon.setTemperature(airCon.getTemperature()+1);
    }

    public void onDecreaseTempBtn()
    {
        airCon.setTemperature(airCon.getTemperature()-1);
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
