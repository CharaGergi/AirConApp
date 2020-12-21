package com.example.airconapp.view.Profile;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.airconapp.view.Menu.MenuActivity;

public class ProfilePresenter
{
    ProfileView profileView;

    public ProfilePresenter(ProfileView pView) {
        this.profileView = pView;
    }

    public boolean onToggleMic() {
        if (profileView.getMicSwitch().isChecked())
        {
            MenuActivity.profile.setSpeechCommands(true);
            return true;
        }
        else
        {
            MenuActivity.profile.setSpeechCommands(false);
            return false;
        }
    }

    public boolean onToggleSpeaker() {
        if (profileView.getSpeech().isChecked())
        {
            MenuActivity.profile.setSoundCommands(true);
            return true;
        }
        else
        {
            MenuActivity.profile.setSoundCommands(false);
            return false;
        }
    }
}
