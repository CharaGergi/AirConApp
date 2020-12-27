package com.example.airconapp.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.airconapp.R;
import com.example.airconapp.domain.Profile;
import com.example.airconapp.view.Profile.ProfileActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button settingsBtn;
    private Button soundCommBtn;
    private Button speechCommBtn;
    static public Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);

        soundCommBtn = findViewById(R.id.soundCommandsBtn);
        soundCommBtn.setOnClickListener(this);

        speechCommBtn = findViewById(R.id.speechCommandsBtn);
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