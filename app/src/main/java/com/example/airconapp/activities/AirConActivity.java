package com.example.airconapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.airconapp.R;

public class AirConActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView ACName;
    private Button backBtn;
    private Button settingsBtn;
    private Button editNameBtn;
    private Button heatBtn;
    private Button coldBtn;
    private Button autoBtn;
    private Button fanBtn;
    private Button humidityBtn;
    private Button increaseTempBtn;
    private Button decreaseTempBtn;
    private Button increaseAngleBtn;
    private Button decreaseAngleBtn;
    private Button advancedSettingsBtn;
    private Button soundCommBtn;
    private Button speechCommBtn;
    private Button powerBtn;
    private EditText temperatureEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_con);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // if the attribute in the manifest doesn't work, use this

        // need to implement the way that the activity will get the AC name (maybe MVP)

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);

        editNameBtn = findViewById(R.id.editNameBtn);
        editNameBtn.setOnClickListener(this);

        heatBtn = findViewById(R.id.heatBtn);
        heatBtn.setOnClickListener(this);

        coldBtn = findViewById(R.id.coldBtn);
        coldBtn.setOnClickListener(this);

        autoBtn = findViewById(R.id.autoBtn);
        autoBtn.setOnClickListener(this);

        fanBtn = findViewById(R.id.fanBtn);
        fanBtn.setOnClickListener(this);

        humidityBtn = findViewById(R.id.humidityBtn);
        humidityBtn.setOnClickListener(this);

        increaseTempBtn = findViewById(R.id.increaseTempBtn);
        increaseTempBtn.setOnClickListener(this);

        decreaseTempBtn = findViewById(R.id.decreaseTempBtn);
        decreaseTempBtn.setOnClickListener(this);

        increaseAngleBtn = findViewById(R.id.increaseAngleBtn);
        increaseAngleBtn.setOnClickListener(this);

        decreaseAngleBtn = findViewById(R.id.decreaseAngleBtn);
        decreaseAngleBtn.setOnClickListener(this);

        advancedSettingsBtn = findViewById(R.id.advancedSettingsBtn);
        advancedSettingsBtn.setOnClickListener(this);

        soundCommBtn = findViewById(R.id.soundCommandsBtn);
        soundCommBtn.setOnClickListener(this);

        speechCommBtn = findViewById(R.id.speechCommandsBtn);
        speechCommBtn.setOnClickListener(this);

        powerBtn = findViewById(R.id.powerBtn);
        powerBtn.setOnClickListener(this);

        temperatureEditTxt = findViewById(R.id.tempEditTxt);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view)
    {
        if (view == backBtn)
        {
            Intent intent = new Intent(AirConActivity.this, MenuActivity.class);
            startActivity(intent);
        }
        if (view == settingsBtn)
        {
            Intent intent = new Intent(AirConActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        if (view == editNameBtn)
        {
            // to be implemented
        }
        if (view == heatBtn)
        {
            // to be implemented
        }
        if (view == coldBtn)
        {
            // to be implemented
        }
        if (view == autoBtn)
        {
            // to be implemented
        }
        if (view == fanBtn)
        {
            // to be implemented
        }
        if (view == humidityBtn)
        {
            // to be implemented
        }
        if (view == increaseTempBtn)
        {
            // to be implemented
        }
        if (view == decreaseTempBtn)
        {
            // to be implemented
        }
        if (view == increaseAngleBtn)
        {
            // to be implemented
        }
        if (view == decreaseAngleBtn)
        {
            // to be implemented
        }
        if (view == advancedSettingsBtn)
        {
            Intent intent = new Intent(AirConActivity.this, AdvancedACSettingsActivity.class);
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
        if (view == powerBtn)
        {
            // to be implemented
        }
    }
}