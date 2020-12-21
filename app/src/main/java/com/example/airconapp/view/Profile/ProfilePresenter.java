package com.example.airconapp.view.Profile;

import com.example.airconapp.view.Menu.MenuActivity;

public class ProfilePresenter
{
    ProfileView view;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
    }

    public boolean onToggleMic() {
        if (view.getMicSwitch().isChecked())
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
        if (view.getSpeakerSwitch().isChecked())
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