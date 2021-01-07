package com.example.airconapp.view.AirConDetails;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.airconapp.R;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.Profile.ProfileActivity;

public class AirConDetailsActivity extends UtilitiesActivity implements View.OnClickListener, AirConDetailsView
{
    private Button backBtn;
    private Button settingsBtn;
    private TextView airConName;
    private TextView airConSerial;
    private TextView airConCP;
    private TextView airConHP;
    private TextView airConEC;
    private TextView airConNP;
    private TextView airConID;
    private TextView airConOD;
    private AirConDetailsPresenter airConDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyFontSize(getResources().getConfiguration());
        setContentView(R.layout.activity_air_con_details);

        airConDetailsPresenter = new AirConDetailsPresenter(this);

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view)
    {
        if (view == backBtn) {
            handleBackBtn(AirConDetailsActivity.this, AirConActivity.class);
        }
        if (view == settingsBtn) {
            handleSettingsBtn(AirConDetailsActivity.this);
        }
    }

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
        return airConOD.getText().toString();
    }
}