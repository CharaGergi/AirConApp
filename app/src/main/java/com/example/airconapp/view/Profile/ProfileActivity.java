package com.example.airconapp.view.Profile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import com.example.airconapp.R;
import com.example.airconapp.view.Menu.MenuActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, ProfileView
{
    private Button backBtn;
    private Button smallBtn;
    private Button mediumBtn;
    private Button largeBtn;
    private Button homeBtn;
    private Switch speakerSwitch;
    private Switch micSwitch;
    private ProfilePresenter profilePresenter;
    private SharedPreferences speakerSettings;
    private SharedPreferences micSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePresenter = new ProfilePresenter(this);

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        smallBtn = findViewById(R.id.smallFontBtn);
        smallBtn.setOnClickListener(this);

        mediumBtn = findViewById(R.id.mediumFontBtn);
        mediumBtn.setOnClickListener(this);

        largeBtn = findViewById(R.id.bigFontBtn);
        largeBtn.setOnClickListener(this);

        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);

        speakerSwitch = findViewById(R.id.speakerSwitch);
        speakerSwitch.setOnClickListener(this);

        micSwitch = findViewById(R.id.micSwitch);
        micSwitch.setOnClickListener(this);

        speakerSettings = getSharedPreferences("speaker", 0);
        boolean spkrPref = speakerSettings.getBoolean("speaker", true);
        speakerSwitch.setChecked(spkrPref);

        micSettings = getSharedPreferences("mic", 0);
        boolean micPref = micSettings.getBoolean("mic", true);
        micSwitch.setChecked(micPref);
    }

    public void applyFontSize(){
        Configuration configuration = getResources().getConfiguration();
         //0.85 small size, 1 normal size, 1.15 big etc
        if(MenuActivity.profile.getFontSize() == 0)
        {
            configuration.fontScale=(float) 0.25;
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
        else if (MenuActivity.profile.getFontSize() == 1)
        {
            configuration.fontScale=(float) 1;
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
        else {
            configuration.fontScale=(float) 2.5;
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view)
    {
        if (view == backBtn) {
            super.onBackPressed();
        }
        if (view == smallBtn){
            MenuActivity.profile.setFontSize(0);
            applyFontSize();
        }
        if (view == mediumBtn){
            MenuActivity.profile.setFontSize(1);
            applyFontSize();
        }
        if (view == largeBtn){
            MenuActivity.profile.setFontSize(2);
            applyFontSize();
        }
        if (view == speakerSwitch){
            SharedPreferences.Editor editor = speakerSettings.edit();
            editor.putBoolean("speaker", speakerSwitch.isChecked());
            editor.commit();

            MenuActivity.profile.setSoundCommands(!MenuActivity.profile.isSoundCommands());
            //speakerSwitch.toggle();
            //profilePresenter.onToggleSpeaker();
        }
        if (view == micSwitch){
            SharedPreferences.Editor editor = micSettings.edit();
            editor.putBoolean("mic", micSwitch.isChecked());
            editor.commit();

            MenuActivity.profile.setSpeechCommands(!MenuActivity.profile.isSpeechCommands());
            //micSwitch.toggle();
            //profilePresenter.onToggleMic();
        }
        if (view == homeBtn){
            Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
            startActivity(intent);
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
}