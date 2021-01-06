package com.example.airconapp.view.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.airconapp.R;
import com.example.airconapp.domain.Profile;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.Profile.ProfileActivity;

public class MenuActivity extends UtilitiesActivity implements View.OnClickListener
{
    private Button settingsBtn;
    private Button soundCommBtn;
    private Button speechCommBtn;
    static public Profile profile = new Profile(0, false, false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        profile.setFontSize(intent.getIntExtra("FONT", 1));

        applyFontSize(getResources().getConfiguration());

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);

        soundCommBtn = findViewById(R.id.soundCommandsBtn);
        if (!profile.isSoundCommands())
        {
            soundCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
        }
        soundCommBtn.setOnClickListener(this);

        speechCommBtn = findViewById(R.id.speechCommandsBtn);
        if (!profile.isSpeechCommands())
        {
            speechCommBtn.setBackgroundResource(R.drawable.mic_icon_muted);
        }
        speechCommBtn.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view)
    {
        if (view == settingsBtn) {
            Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
            intent.putExtra("PREVIOUS_ACTIVITY", MenuActivity.class.toString());
            startActivity(intent);
        }
        if (view == soundCommBtn) {
            if (profile.isSoundCommands()) {
                soundCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
            } else {
                soundCommBtn.setBackgroundResource(R.drawable.speaker_icon);
            }
            profile.setSoundCommands(!profile.isSoundCommands());
        }
        if (view == speechCommBtn) {
            if (profile.isSpeechCommands()) {
                speechCommBtn.setBackgroundResource(R.drawable.mic_icon_muted);
            } else {
                speechCommBtn.setBackgroundResource(R.drawable.mic_icon);
            }
            profile.setSpeechCommands(!profile.isSpeechCommands());
        }
    }
}