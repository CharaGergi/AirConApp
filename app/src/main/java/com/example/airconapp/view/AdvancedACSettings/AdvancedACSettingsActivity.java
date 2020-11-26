package com.example.airconapp.view.AdvancedACSettings;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import com.example.airconapp.R;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.Profile.ProfileActivity;

public class AdvancedACSettingsActivity extends AppCompatActivity implements View.OnClickListener
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_ac_settings);

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
            // to be implemented
        }
        if (view == speechCommBtn)
        {
            // to be implemented
        }
        if (view == sleepSwitch)
        {
            // to be implemented
        }
        if (view == silentSwitch)
        {
            // to be implemented
        }
        if (view == applySwitch)
        {
            // to be implemented
        }
    }
}