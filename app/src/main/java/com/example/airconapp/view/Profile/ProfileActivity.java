package com.example.airconapp.view.Profile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.domain.Profile;
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
    private ProfilePresenter  profilePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilePresenter = new ProfilePresenter(this);

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);
    }

    public void applyFontSize(){
        Configuration configuration = getResources().getConfiguration();
         //0.85 small size, 1 normal size, 1,15 big etc
        if(MenuActivity.profile.getFontSize()==0) {
            configuration.fontScale=(float) 0.85;
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
        else if (MenuActivity.profile.getFontSize()==1){
            configuration.fontScale=(float) 1;
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }

        else {
            configuration.fontScale=(float) 1.20;
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
            //must save previous activity
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        if (view == smallBtn){
            MenuActivity.profile.setFontSize(0);
        }

        if (view == mediumBtn){
            MenuActivity.profile.setFontSize(1);
        }

        if (view == largeBtn){
            MenuActivity.profile.setFontSize(2);
        }

        if (view == speakerSwitch){
            speakerSwitch.toggle();
            profilePresenter.onToggleSpeaker();
        }

        if (view == micSwitch){
            micSwitch.toggle();
            profilePresenter.onToggleMic();
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
    public Switch getSpeech() {
        return speakerSwitch;
    }
}