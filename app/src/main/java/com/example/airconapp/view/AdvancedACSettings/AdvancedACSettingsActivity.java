package com.example.airconapp.view.AdvancedACSettings;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import com.example.airconapp.R;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.Profile.ProfileActivity;

public class AdvancedACSettingsActivity extends AppCompatActivity implements View.OnClickListener, AdvancedACSettingsView
{
    private Button backBtn;
    private Button settingsBtn;
    private Button homeBtn;
    private Button soundCommBtn;
    private Button speechCommBtn;
    private Switch sleepSwitch;
    private Switch silentSwitch;
    private SeekBar airSeekbar;
    private Switch applySwitch;
    private EditText timerHourEditTxt;
    private EditText timerMinsEditTxt;
    private EditText timerOffHourEditTxt;
    private EditText timerOffMinsEditTxt;
    private ScrollView airCons;
    private AdvancedACSettingsPresenter advancedACSettingsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_ac_settings);

        advancedACSettingsPresenter = new AdvancedACSettingsPresenter(this);

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);

        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);

        soundCommBtn = findViewById(R.id.soundCommandsBtn);
        soundCommBtn.setOnClickListener(this);

        speechCommBtn = findViewById(R.id.speechCommandsBtn);
        speechCommBtn.setOnClickListener(this);

        sleepSwitch = findViewById(R.id.sleepSwitch);
        sleepSwitch.setOnClickListener(this);

        silentSwitch = findViewById(R.id.silentSwitch);
        silentSwitch.setOnClickListener(this);

        airSeekbar = findViewById(R.id.airIntensityseekBar);
        //airSeekbar.setOnSeekBarChangeListener(); // need to check this

        applySwitch = findViewById(R.id.applyToAllSwitch);
        applySwitch.setOnClickListener(this);

        timerHourEditTxt = findViewById(R.id.hourTimerEditTxt);
        timerMinsEditTxt = findViewById(R.id.minsTimerEditTxt);
        timerOffHourEditTxt = findViewById(R.id.hoursTimerOffEditTxt);
        timerOffMinsEditTxt = findViewById(R.id.minsTimerOffEditTxt);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view)
    {
        if (view == backBtn)
        {
            Intent intent = new Intent(AdvancedACSettingsActivity.this, AirConActivity.class);
            startActivity(intent);
        }
        if (view == settingsBtn)
        {
            Intent intent = new Intent(AdvancedACSettingsActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        if (view == homeBtn)
        {
            Intent intent = new Intent(AdvancedACSettingsActivity.this, MenuActivity.class);
            startActivity(intent);
        }
        if (view == soundCommBtn)
        {
            soundCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
        }
        if (view == speechCommBtn)
        {
            speechCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
        }
        if (view == sleepSwitch)
        {
            sleepSwitch.toggle();
            advancedACSettingsPresenter.onToggleSleep();
        }
        if (view == silentSwitch)
        {
            silentSwitch.toggle();
            advancedACSettingsPresenter.onToggleSilent();
        }
        if (view == applySwitch)
        {
            applySwitch.toggle();
            advancedACSettingsPresenter.onToggleApplyToAll();
        }
    }

    @Override
    public ScrollView getAirCons() {
        return airCons;
    }

    @Override
    public void setAirCons(ScrollView value) {
        airCons = value;
    }

    @Override
    public String getAirConHTimer() {
        return timerHourEditTxt.getText().toString();
    }

    @Override
    public void setAirConHTimer(String value) {
        timerHourEditTxt.setText(value);
    }

    @Override
    public String getAirConMTimer() {
        return timerMinsEditTxt.getText().toString();
    }

    @Override
    public void setAirConMTimer(String value) {
        timerMinsEditTxt.setText(value);
    }

    @Override
    public String getAirConHTimerOff() {
        return timerOffHourEditTxt.getText().toString();
    }

    @Override
    public void setAirConHTimerOff(String value) {
        timerOffHourEditTxt.setText(value);
    }

    @Override
    public String getAirConMTimerOff() {
        return timerOffMinsEditTxt.getText().toString();
    }

    @Override
    public void setAirConMTimerOff(String value) {
        timerOffMinsEditTxt.setText(value);
    }

    @Override
    public SeekBar getAirConAirDens() {
        return airSeekbar;
    }

    @Override
    public void setAirConAirDens(SeekBar value) {
        airSeekbar = value;
    }

    @Override
    public Switch getSleepSwitch() {
        return sleepSwitch;
    }

    @Override
    public void setSleepSwitch(Switch value) {
        sleepSwitch = value;
    }

    @Override
    public Switch getSilentSwitch() {
        return silentSwitch;
    }

    @Override
    public void setSilentSwitch(Switch value) {
        silentSwitch = value;
    }

    @Override
    public Switch getApplyAll() {
        return applySwitch;
    }

    @Override
    public void setApplyAll(Switch value) {
        applySwitch = value;
    }
}