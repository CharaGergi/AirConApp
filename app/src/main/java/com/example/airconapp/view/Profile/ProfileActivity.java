package com.example.airconapp.view.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.AdvancedACSettings.AdvancedACSettingsActivity;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.AirConDetails.AirConDetailsActivity;
import com.example.airconapp.view.Help.HelpActivity;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.SearchResults.SearchResultsActivity;
import java.io.Serializable;

public class ProfileActivity extends UtilitiesActivity implements View.OnClickListener, ProfileView
{
    AirCon airCon;
    private Button backBtn;
    private Button smallBtn;
    private Button mediumBtn;
    private Button largeBtn;
    private Button homeBtn;
    private Button helpBtn;
    private Switch speakerSwitch;
    private Switch micSwitch;
    private SharedPreferences speakerSettings;
    private SharedPreferences micSettings;
    private String prev_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        System.out.println(MenuActivity.profile.isSoundCommands() );
        System.out.println(MenuActivity.profile.isSpeechCommands() );



        Intent intent = getIntent();
        prev_activity = intent.getStringExtra("PREVIOUS_ACTIVITY");
        prev_activity = stringManipulation(prev_activity);

        airCon = (AirCon) intent.getSerializableExtra("AC");

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        smallBtn = findViewById(R.id.smallFontBtn);
        smallBtn.setOnClickListener(this);

        mediumBtn = findViewById(R.id.mediumFontBtn);
        mediumBtn.setOnClickListener(this);

        largeBtn = findViewById(R.id.bigFontBtn);
        largeBtn.setOnClickListener(this);

        handleButtonFonts();

        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);

        speakerSwitch = findViewById(R.id.speakerSwitch);
        speakerSwitch.setOnClickListener(this);

        micSwitch = findViewById(R.id.micSwitch);
        micSwitch.setOnClickListener(this);

        helpBtn = findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(this);

        speakerSettings = getSharedPreferences("speaker", MODE_MULTI_PROCESS);
        boolean spkrPref = speakerSettings.getBoolean("speaker", MenuActivity.profile.isSoundCommands());
        System.out.println("speaker:"+spkrPref);
        speakerSwitch.setChecked(spkrPref);

        micSettings = getSharedPreferences("mic", MODE_MULTI_PROCESS);
        boolean micPref = micSettings.getBoolean("mic", MenuActivity.profile.isSpeechCommands());
        System.out.println("mic:"+micPref);
        micSwitch.setChecked(micPref);
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearPreferences(speakerSettings);
        clearPreferences(micSettings);
    }

    public void onClick(View view)
    {
        if (view == backBtn) {
            Intent intent;
            if (prev_activity.equalsIgnoreCase(AirConActivity.class.toString()))
            {
                intent = new Intent(ProfileActivity.this, AirConActivity.class);
            }
            else if (prev_activity.equalsIgnoreCase(AdvancedACSettingsActivity.class.toString()))
            {
                intent = new Intent(ProfileActivity.this, AdvancedACSettingsActivity.class);
            }
            else if (prev_activity.equalsIgnoreCase(AirConDetailsActivity.class.toString()))
            {
                intent = new Intent(ProfileActivity.this, AirConDetailsActivity.class);
            }
            else if (prev_activity.equalsIgnoreCase(SearchResultsActivity.class.toString()))
            {
                intent = new Intent(ProfileActivity.this, SearchResultsActivity.class);
            }
            else if (prev_activity.equalsIgnoreCase(MenuActivity.class.toString()))
            {
                intent = new Intent(ProfileActivity.this, MenuActivity.class);
            }
            else {
                intent = new Intent(ProfileActivity.this, HelpActivity.class);
            }

            intent.putExtra("FONT", MenuActivity.profile.getFontSize());
            intent.putExtra("AC", (Serializable) airCon);
            startActivity(intent);
        }
        if (view == smallBtn){
            MenuActivity.profile.setFontSize(0);
            applyFontSize(getResources().getConfiguration());
            this.recreate();
        }
        if (view == mediumBtn){
            MenuActivity.profile.setFontSize(1);
            applyFontSize(getResources().getConfiguration());
            this.recreate();
        }
        if (view == largeBtn){
            MenuActivity.profile.setFontSize(2);
            applyFontSize(getResources().getConfiguration());
            this.recreate();
        }
        if (view == speakerSwitch){
            if (MenuActivity.profile.isSoundCommands())
            {
                savePreferences("speaker", speakerSwitch.isChecked(), speakerSettings);
            }
            else
            {
                savePreferences("speaker", !speakerSwitch.isChecked(), speakerSettings);
            }
            MenuActivity.profile.setSoundCommands(!MenuActivity.profile.isSoundCommands());
        }
        if (view == micSwitch){
            if (MenuActivity.profile.isSpeechCommands())
            {
                savePreferences("mic", micSwitch.isChecked(), micSettings);
            }
            else
            {
                savePreferences("mic", !micSwitch.isChecked(), micSettings);
            }
            MenuActivity.profile.setSpeechCommands(!MenuActivity.profile.isSpeechCommands());
        }
        if (view == homeBtn){
            Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
            intent.putExtra("FONT", MenuActivity.profile.getFontSize());
            startActivity(intent);
        }
        if (view == helpBtn){
            handleHelpBtn(ProfileActivity.this, MenuActivity.profile.getFontSize());
        }
    }

    @Override
    public Button getSmallFontButton() {
        return smallBtn;
    }

    @Override
    public Button getMediumFontButton() {
        return mediumBtn;
    }

    @Override
    public Button getBigFontButton() {
        return largeBtn;
    }

    @Override
    public Switch getMicSwitch() {
        return micSwitch;
    }

    @Override
    public Switch getSpeakerSwitch() {
        return speakerSwitch;
    }

    public SharedPreferences getSpeakerSettings()
    {
        return speakerSettings;
    }

    public SharedPreferences getMicSettings() {
        return micSettings;
    }

    private void savePreferences(String key, boolean value, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private void clearPreferences(SharedPreferences sharedPreferences)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private void handleButtonFonts()
    {
        if (MenuActivity.profile.getFontSize() == 0)
        {
            smallBtn.setBackgroundResource(R.drawable.small_font_selected);
        }
        else if (MenuActivity.profile.getFontSize() == 1)
        {
            mediumBtn.setBackgroundResource(R.drawable.medium_font_selected);
        }
        else
        {
            largeBtn.setBackgroundResource(R.drawable.big_font_selected);
        }
    }
}