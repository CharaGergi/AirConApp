package com.example.airconapp.view.AdvancedACSettings;

import com.example.airconapp.domain.AirCon;
import com.example.airconapp.domain.Utilities;

public class AdvancedACSettingsPresenter
{
    private AdvancedACSettingsView view;
    private AirCon airCon;

    public AdvancedACSettingsPresenter(AdvancedACSettingsView view)
    {
        this.view = view;
    }

    public int onChangeIntensity()
    {
        airCon.setAirIntensity(view.getAirConAirDens().getProgress());

        return view.getAirConAirDens().getProgress();
    }

    public int onSetTimer()
    {
        int mins = Integer.parseInt(view.getAirConHTimer().getText().toString());
        int hours = Integer.parseInt(view.getAirConMTimer().getText().toString());

        int total = (hours*60) + mins;

        airCon.setTimer(total);

        return total;
    }

    public int onSetTimerOff()
        {
        int mins = Integer.parseInt(view.getAirConMTimerOff().getText().toString());
        int hours = Integer.parseInt(view.getAirConHTimerOff().getText().toString());

        int total = (hours*60) + mins;

        airCon.setTimerOff(total);

        return total;
    }

    public boolean onToggleSleep()
    {
        if (view.getSleepSwitch().isChecked())
        {
            airCon.setSleepMode(true);
            return true;
        }
        else
        {
            airCon.setSleepMode(false);
            return false;
        }
    }

    public boolean onToggleSilent()
    {
        if (view.getSilentSwitch().isChecked())
        {
            airCon.setSilentMode(true);
            return true;
        }
        else
        {
            airCon.setSilentMode(false);
            return false;
        }
    }

    public void onToggleApplyToAll()
    {
        if (view.getApplyAll().isChecked())
        {
            for (AirCon airCon : Utilities.getAirCons())
            {
                airCon.setAirIntensity(onChangeIntensity());
                airCon.setTimer(onSetTimer());
                airCon.setTimerOff(onSetTimerOff());
                airCon.setSleepMode(onToggleSleep());
                airCon.setSilentMode(onToggleSilent());
            }
        }
    }
}
