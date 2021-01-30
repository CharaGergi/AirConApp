package com.example.airconapp.view.AirConDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.AdvancedACSettings.AdvancedACSettingsActivity;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.Menu.MenuActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class AirConDetailsActivity extends UtilitiesActivity implements View.OnClickListener, AirConDetailsView
{
    private Button backBtn;
    private Button settingsBtn;
    private Button homeBtn;
    private Button helpBtn;
    private AirCon airCon;
    private TextView airConName;
    private TextView airConSerial;
    private TextView airConCP;
    private TextView airConHP;
    private TextView airConEC;
    private TextView airConNP;
    private TextView airConID;
    private TextView airConED;
    private AirConDetailsPresenter airConDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_con_details);

        Intent intent = getIntent();
        airCon = (AirCon) intent.getSerializableExtra("AC");

        MenuActivity.profile.setFontSize(intent.getIntExtra("FONT", 1));
        applyFontSize(getResources().getConfiguration());

        airConDetailsPresenter = new AirConDetailsPresenter(this);

        airConName = findViewById(R.id.airConNameTxtView);
        airConName.setText(airCon.getName());

        airConSerial = findViewById(R.id.airConSerialTxtView);
        airConSerial.setText(String.valueOf(airCon.getAirConDetails().getSerialNo()));

        airConCP = findViewById(R.id.airConCP);
        airConCP.setText(String.valueOf(airCon.getAirConDetails().getCoolingPower()));

        airConHP = findViewById(R.id.airConHP);
        airConHP.setText(String.valueOf(airCon.getAirConDetails().getHeatingPower()));

        airConEC = findViewById(R.id.airConEC);
        airConEC.setText(airCon.getAirConDetails().getEnergyClass());

        airConNP = findViewById(R.id.airConNP);
        airConNP.setText(String.valueOf(airCon.getAirConDetails().getNoisePower()));

        airConID = findViewById(R.id.airConID);
        airConID.setText(airCon.getAirConDetails().getInteriorUnitDimensions());

        airConED = findViewById(R.id.airConED);
        airConED.setText(airCon.getAirConDetails().getExteriorUnitDimensions());

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);

        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);

        helpBtn = findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(this);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        SpeechRecognizer(AirConDetailsActivity.this, airCon, MenuActivity.profile.getFontSize());
        if (MenuActivity.profile.isSpeechCommands())
        {
            speechRecognizer.startListening(speechRecognizerIntent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view)
    {
        if (view == backBtn) {
            Intent intent = new Intent(AirConDetailsActivity.this, AdvancedACSettingsActivity.class);
            intent.putExtra("FONT", MenuActivity.profile.getFontSize());
            intent.putExtra("AC", (Serializable) airCon);
            startActivity(intent);
        }
        if (view == settingsBtn) {
            handleSettingsBtn(AirConDetailsActivity.this, airCon);
        }
        if (view == homeBtn)
        {
            Intent intent = new Intent(AirConDetailsActivity.this, MenuActivity.class);
            intent.putExtra("FONT", MenuActivity.profile.getFontSize());
            startActivity(intent);
        }
        if (view == helpBtn){
            handleHelpBtn(AirConDetailsActivity.this);
        }
    }

  /*  @Override
    public void handleSpeechCommands(ArrayList<String> data, Activity context, AirCon airCon, int menuFont)
    {
        super.handleSpeechCommands(data, context, airCon, menuFont);

        // stuff to do
    }
*/
    @Override
    public String getAirConName() {
        return airConName.getText().toString();
    }

    @Override
    public String getSerialNo() {
        return airConSerial.getText().toString();
    }

    @Override
    public String getCoolingPower() {
        return airConCP.getText().toString();
    }

    @Override
    public String getHeatingPower() {
        return airConHP.getText().toString();
    }

    @Override
    public String getEnergyClass() {
        return airConEC.getText().toString();
    }

    @Override
    public String getNoisePower() {
        return airConNP.getText().toString();
    }

    @Override
    public String getInteriorUnitDimensions() {
        return airConID.getText().toString();
    }

    @Override
    public String getExteriorUnitDimensions() {
        return airConED.getText().toString();
    }
}