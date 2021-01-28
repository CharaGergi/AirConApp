package com.example.airconapp.view.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;

import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.domain.Profile;
import com.example.airconapp.domain.Utilities;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.AdvancedACSettings.AdvancedACSettingsActivity;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.SearchResults.SearchResultsActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MenuActivity extends UtilitiesActivity implements View.OnClickListener
{
    private Button settingsBtn;
    private Button soundCommBtn;
    private Button speechCommBtn;
    private Button searchBtn;
    private Button helpBtn;
    private ListView selectedAirCons;
    private String[] selectedAirConsNames;
    static public Profile profile = new Profile(0, true, true);
    static public Utilities utilities = new Utilities();
    static public UtilitiesActivity utilitiesActivity = new UtilitiesActivity();
    static private AirCon airCon;

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
        System.out.println("sound:"+profile.isSoundCommands());
        if (!profile.isSoundCommands())
        {
            soundCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
        }
        soundCommBtn.setOnClickListener(this);

        speechCommBtn = findViewById(R.id.speechCommandsBtn);
        System.out.println("speech:"+profile.isSpeechCommands());
        if (!profile.isSpeechCommands())
        {
            speechCommBtn.setBackgroundResource(R.drawable.mic_icon_muted);
        }
        speechCommBtn.setOnClickListener(this);
        //utilitiesActivity.SpeechRecognizer(MenuActivity.this, null, profile.getFontSize());
        //utilitiesActivity.speechRecognizer.startListening(utilitiesActivity.speechRecognizerIntent);


        selectedAirCons = (ListView) findViewById(R.id.selectedAirConsList);

        if (Utilities.getSelectedAirCons() != null)
        {
            AirCon[] airConArray = new AirCon[Utilities.getSelectedAirCons().size()];
            Utilities.getSelectedAirCons().toArray(airConArray);

            selectedAirConsNames = new String[airConArray.length];
            for (int i = 0; i < airConArray.length; i++)
            {
                selectedAirConsNames[i] = airConArray[i].getName();
            }

            final ArrayAdapter<String> myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedAirConsNames);
            selectedAirCons.setAdapter(myAdapter);

            selectedAirCons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String airCon = myAdapter.getItem(position);
                    if (airCon == null) return;

                    Intent intent = new Intent(MenuActivity.this, AirConActivity.class);
                    intent.putExtra("AC_NAME", airCon);
                    intent.putExtra("FONT", profile.getFontSize());
                    startActivity(intent);
                }
            });
        }

        airCon = new AirCon("Test", 1 , 25, 2, 2, false, false, 0 , 3, true);

        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);

        helpBtn = findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(this);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        SpeechRecognizer(MenuActivity.this,airCon , profile.getFontSize());
        System.out.println("speech :" + SpeechRecognizer.isRecognitionAvailable(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void handleSpeechCommands(ArrayList<String> data, Activity context, AirCon airCon, int menuFont) {
        super.handleSpeechCommands(data, context, airCon, menuFont);
        StringTokenizer tokenizer = new StringTokenizer(data.get(0));
        while(tokenizer.hasMoreTokens()) {
            if(tokenizer.nextToken().equalsIgnoreCase("επίλεξε")){
                String name = tokenizer.nextToken(tokenizer.nextToken());
                utilitiesActivity.searchSelectedAndStartActivity(name, MenuActivity.this, AirConActivity.class , profile.getFontSize());
            }
        }
    }

    public void onClick(View view)
    {
        if (view == settingsBtn) {
            handleSettingsBtn(MenuActivity.this, null);
        }
        if (view == soundCommBtn) {
            toggleSoundBtn(soundCommBtn);
        }
        if (view == speechCommBtn) {
            toggleSpeechBtn(speechCommBtn);
            if(profile.isSpeechCommands()) {
                speechRecognizer.startListening(speechRecognizerIntent);
                System.out.println("speech :" + SpeechRecognizer.isRecognitionAvailable(this));
            }

        }
        if (view == searchBtn)
        {
            Intent intent = new Intent(MenuActivity.this, SearchResultsActivity.class);
            intent.putExtra("FONT", profile.getFontSize());
            startActivity(intent);
        }
        if (view == helpBtn){
            handleHelpBtn(MenuActivity.this, MenuActivity.profile.getFontSize());
        }
    }
}