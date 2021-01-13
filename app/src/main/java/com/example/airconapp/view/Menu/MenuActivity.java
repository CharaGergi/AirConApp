package com.example.airconapp.view.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.airconapp.R;
import com.example.airconapp.domain.Profile;
import com.example.airconapp.domain.Utilities;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.Profile.ProfileActivity;
import com.example.airconapp.view.SearchResults.SearchResultsActivity;

public class MenuActivity extends UtilitiesActivity implements View.OnClickListener
{
    private Button settingsBtn;
    private Button soundCommBtn;
    private Button speechCommBtn;
    private Button searchBtn;
    private ListView selectedAirCons;
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

        selectedAirCons = findViewById(R.id.selectedAirConsList);

        final ArrayAdapter myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Utilities.getSelectedAirCons().toArray());
        selectedAirCons.setAdapter(myAdapter);

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

        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view)
    {
        if (view == settingsBtn) {
            handleSettingsBtn(MenuActivity.this);
        }
        if (view == soundCommBtn) {
            toggleSoundBtn(soundCommBtn);
        }
        if (view == speechCommBtn) {
            toggleSpeechBtn(speechCommBtn);
        }
        if (view == searchBtn)
        {
            Intent intent = new Intent(MenuActivity.this, SearchResultsActivity.class);
            intent.putExtra("FONT", profile.getFontSize());
            startActivity(intent);
        }
    }
}