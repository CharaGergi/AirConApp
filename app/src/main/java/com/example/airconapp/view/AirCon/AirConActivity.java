package com.example.airconapp.view.AirCon;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.view.AdvancedACSettings.AdvancedACSettingsActivity;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.Profile.ProfileActivity;

public class AirConActivity extends AppCompatActivity implements View.OnClickListener, AirConView {
    private AirCon airCon;
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
    private AirConPresenter airConPresenter;
    private int mainMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_con);
        Intent intent = getIntent();

        airCon = (AirCon) intent.getSerializableExtra("airCon");
        ACName.setText(airCon.getName());
        mainMode = airCon.getMainMode();

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // if the attribute in the manifest doesn't work, use this

        // need to implement the way that the activity will get the AC name (maybe MVP)

        airConPresenter = new AirConPresenter(this);

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view) {
        if (view == backBtn) {
            Intent intent = new Intent(AirConActivity.this, MenuActivity.class);
            startActivity(intent);
        }
        if (view == settingsBtn) {
            Intent intent = new Intent(AirConActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        if (view == editNameBtn) {
            ACName.setCursorVisible(true);
            ACName.setFocusableInTouchMode(true);
            ACName.setInputType(InputType.TYPE_CLASS_TEXT);
            ACName.requestFocus();
            editNameBtn.setBackgroundResource(R.drawable.checkmark);
            editNameBtn.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                    if (view == editNameBtn || (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == keyEvent.KEYCODE_ENTER)) {
                        ACName.clearFocus();
                        ACName.setFocusableInTouchMode(false);
                        ACName.setCursorVisible(false);
                        editNameBtn.setBackgroundResource(R.drawable.pencil_icon);

                        return true;
                    }
                    return false;
                }
            });

        }
        if (view == heatBtn && 0 != mainMode) {
            coldBtn.setBackgroundResource(R.drawable.cold_icon);
            fanBtn.setBackgroundResource(R.drawable.fan_icon);
            humidityBtn.setBackgroundResource(R.drawable.humidity_icon);
            //to implement autoBtn

            heatBtn.setBackgroundResource(R.drawable.heat_icon_selected);
            airConPresenter.onSetMode(0);
        }
        if (view == coldBtn && 1 != mainMode) {
            heatBtn.setBackgroundResource(R.drawable.heat_icon);
            fanBtn.setBackgroundResource(R.drawable.fan_icon);
            humidityBtn.setBackgroundResource(R.drawable.humidity_icon);
            //to implement autoBtn

            coldBtn.setBackgroundResource(R.drawable.cold_icon_selected);
            airConPresenter.onSetMode(1);
        }
        if (view == autoBtn && 2 != mainMode) {
            heatBtn.setBackgroundResource(R.drawable.heat_icon);
            coldBtn.setBackgroundResource(R.drawable.cold_icon);
            fanBtn.setBackgroundResource(R.drawable.fan_icon);
            humidityBtn.setBackgroundResource(R.drawable.humidity_icon);

            // to be implemented
            airConPresenter.onSetMode(2);
        }
        if (view == fanBtn && 3 != mainMode) {
            heatBtn.setBackgroundResource(R.drawable.heat_icon);
            coldBtn.setBackgroundResource(R.drawable.cold_icon);
            humidityBtn.setBackgroundResource(R.drawable.humidity_icon);
            //to implement autoBtn

            fanBtn.setBackgroundResource(R.drawable.fan_icon_selected);
            airConPresenter.onSetMode(3);
        }
        if (view == humidityBtn && 4 != mainMode) {
            heatBtn.setBackgroundResource(R.drawable.heat_icon);
            fanBtn.setBackgroundResource(R.drawable.fan_icon);
            coldBtn.setBackgroundResource(R.drawable.cold_icon);
            //to implement autoBtn

            humidityBtn.setBackgroundResource(R.drawable.humidity_icon_selected);
            airConPresenter.onSetMode(4);
        }
        if (view == increaseTempBtn) {
            airConPresenter.onIncreaseTempBtn();
        }
        if (view == decreaseTempBtn) {
            airConPresenter.onDecreaseTempBtn();
        }
        if (view == increaseAngleBtn) {
            airConPresenter.onIncreaseTilt();
        }
        if (view == decreaseAngleBtn) {
            airConPresenter.onDecreaseTilt();
        }
        if (view == advancedSettingsBtn) {
            Intent intent = new Intent(AirConActivity.this, AdvancedACSettingsActivity.class);
            startActivity(intent);
        }

        if (view == soundCommBtn) {
            if (MenuActivity.profile.isSoundCommands()) {
                soundCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
                MenuActivity.profile.setSoundCommands(false);
            } else {
                soundCommBtn.setBackgroundResource(R.drawable.speaker_icon);
                MenuActivity.profile.setSoundCommands(true);
            }
        }
        if (view == speechCommBtn) {
            if (MenuActivity.profile.isSpeechCommands()) {
                speechCommBtn.setBackgroundResource(R.drawable.mic_icon_muted);
                MenuActivity.profile.setSpeechCommands(false);
            } else {
                speechCommBtn.setBackgroundResource(R.drawable.mic_icon);
                MenuActivity.profile.setSpeechCommands(true);
            }
        }
        if (view == powerBtn) {
            if (airCon.isPower()) {
                powerBtn.setBackgroundTintList(getResources().getColorStateList(R.color.red));
                airCon.setPower(true);
            } else {
                powerBtn.setBackgroundTintList(getResources().getColorStateList(R.color.green));
                airCon.setPower(false);
            }
        }
    }

    @Override //to be implemented
    public String getAirConName() {
        return ACName.getText().toString();
    }

    @Override
    public String getAirConTemp() {
        return temperatureEditTxt.getText().toString();
    }

    @Override
    public Button getHeat() {
        return heatBtn;
    }

    @Override
    public Button getCold() {
        return coldBtn;
    }

    @Override
    public Button getAuto() {
        return autoBtn;
    }

    @Override
    public Button getHumid() {
        return humidityBtn;
    }

    @Override
    public Button getFan() {
        return fanBtn;
    }

    @Override
    public void setAirConName(String value) {
        ACName.setText(value);
    }

    @Override
    public void setAirConTemp(int value) {
        temperatureEditTxt.setText(value);
    }

    @Override
    public void setMode(int value) {
        airConPresenter.onSetMode(value);
    }
}