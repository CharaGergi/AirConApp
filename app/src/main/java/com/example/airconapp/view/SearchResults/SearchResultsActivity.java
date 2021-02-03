package com.example.airconapp.view.SearchResults;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.domain.Utilities;
import com.example.airconapp.view.AdvancedACSettings.AdvancedACSettingsActivity;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.Profile.ProfileActivity;

public class SearchResultsActivity extends UtilitiesActivity implements View.OnClickListener {
    private Button backBtn;
    private Button soundCommBtn;
    private Button speechCommBtn;
    private Button settingsBtn;
    private Button homeBtn;
    private Button helpBtn;
    private ListView foundAirCons;
    private String[] airConNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();
        MenuActivity.profile.setFontSize(intent.getIntExtra("FONT", 1));

        applyFontSize(getResources().getConfiguration());

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);

        helpBtn = findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(this);

        foundAirCons = (ListView)findViewById(R.id.foundAirConsList);

        soundCommBtn = findViewById(R.id.soundCommandsBtn);
        if (!MenuActivity.profile.isSoundCommands())
        {
            soundCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
        }
        soundCommBtn.setOnClickListener(this);

        speechCommBtn = findViewById(R.id.speechCommandsBtn);
        if (!MenuActivity.profile.isSpeechCommands())
        {
            speechCommBtn.setBackgroundResource(R.drawable.mic_icon_muted);
        }
        speechCommBtn.setOnClickListener(this);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        SpeechRecognizer(SearchResultsActivity.this, new AirCon(), MenuActivity.profile.getFontSize());
        if (MenuActivity.profile.isSpeechCommands())
        {
            speechRecognizer.startListening(speechRecognizerIntent);
        }

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);

        AirCon[] airConArray = new AirCon[Utilities.getAirCons().size()];
        Utilities.getAirCons().toArray(airConArray);

        airConNames = new String[Utilities.getAirCons().size()];
        for (int i = 0; i < airConArray.length; i++)
        {
            airConNames[i] = airConArray[i].getName();
        }

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, airConNames);
        foundAirCons.setAdapter(myAdapter);

        foundAirCons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String airCon = myAdapter.getItem(position);
                if (airCon == null) return;
                for (AirCon ac : Utilities.getAirCons())
                {
                    if (ac.getName().equalsIgnoreCase(airCon))
                    {
                        Utilities.getSelectedAirCons().add(ac);
                        Toast.makeText(SearchResultsActivity.this, "The AC was added to the selected list.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view) {
        if (view == backBtn)
        {
            handleBackBtn(SearchResultsActivity.this, MenuActivity.class);
        }
        if (view == soundCommBtn) {
            toggleSoundBtn(soundCommBtn);
        }
        if (view == speechCommBtn) {
            toggleSpeechBtn(speechCommBtn);
        }
        if (view == settingsBtn) {
            handleSettingsBtn(SearchResultsActivity.this, null);
        }
        if (view == homeBtn){
            System.out.println("Home Button in Search Results pressed");
            Intent intent = new Intent(SearchResultsActivity.this, MenuActivity.class);
            intent.putExtra("FONT", MenuActivity.profile.getFontSize());
            startActivity(intent);
        }
        if(view == helpBtn){
            handleHelpBtn(SearchResultsActivity.this, null);
        }
    }
}