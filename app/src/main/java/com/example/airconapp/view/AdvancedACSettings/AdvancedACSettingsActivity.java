package com.example.airconapp.view.AdvancedACSettings;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.AirConDetails.AirConDetailsActivity;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.Profile.ProfileActivity;

public class AdvancedACSettingsActivity extends UtilitiesActivity implements View.OnClickListener, AdvancedACSettingsView
{
    private AirCon airCon;
    private Button backBtn;
    private Button settingsBtn;
    private Button homeBtn;
    private Button soundCommBtn;
    private Button speechCommBtn;
    private Button detailsBtn;
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
    private int menuFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_ac_settings);

        Intent intent = getIntent();
        menuFont = intent.getIntExtra("FONT", 1);
        airCon = (AirCon) intent.getSerializableExtra("AC");

        MenuActivity.profile.setFontSize(menuFont);

        applyFontSize(getResources().getConfiguration());

        advancedACSettingsPresenter = new AdvancedACSettingsPresenter(this);

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);

        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);

        detailsBtn = findViewById(R.id.detailsBtn);
        detailsBtn.setOnClickListener(this);

        soundCommBtn = findViewById(R.id.soundCommandsBtn);
        if (!MenuActivity.profile.isSoundCommands())
        {
            soundCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
        }
        soundCommBtn.setOnClickListener(this);

        speechCommBtn = findViewById(R.id.speechCommandsBtn);
        if (!MenuActivity.profile.isSpeechCommands())
        {
            speechCommBtn.setBackgroundResource(R.drawable.mic_icon_muted);
        }
        speechCommBtn.setOnClickListener(this);

        sleepSwitch = findViewById(R.id.sleepSwitch);
        sleepSwitch.setOnClickListener(this);

        silentSwitch = findViewById(R.id.silentSwitch);
        silentSwitch.setOnClickListener(this);

        airSeekbar = findViewById(R.id.airIntensityseekBar);
        //airSeekbar.setOnSeekBarChangeListener(); // need to check this
        airSeekbar.setMax(4);

        airSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            int seekBarProgress = airSeekbar.getProgress();

            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    seekBarProgress = progress;
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override public void onStopTrackingTouch(SeekBar seekBar) {
                    airSeekbar.setProgress(seekBarProgress);
            }
        });







            /*@Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(ToggleButtonActivity.this,
                        "Seekbar vale "+i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(ToggleButtonActivity.this,
                        "Seekbar touch started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(ToggleButtonActivity.this,
                        "Seekbar touch stopped", Toast.LENGTH_SHORT).show();
            }
        });*/

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
            handleBackBtn(AdvancedACSettingsActivity.this, AirConActivity.class);
        }
        if (view == settingsBtn)
        {
            handleSettingsBtn(AdvancedACSettingsActivity.this);
        }
        if (view == homeBtn)
        {
            Intent intent = new Intent(AdvancedACSettingsActivity.this, MenuActivity.class);
            intent.putExtra("FONT", menuFont);
            startActivity(intent);
        }
        if (view == soundCommBtn) {
            toggleSoundBtn(soundCommBtn);
        }
        if (view == speechCommBtn) {
            toggleSpeechBtn(speechCommBtn);
        }
        if (view == detailsBtn){
            Intent intent = new Intent(AdvancedACSettingsActivity.this, AirConDetailsActivity.class);
            intent.putExtra("FONT", menuFont);
            startActivity(intent);
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