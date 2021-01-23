package com.example.airconapp.view.Help;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.view.ActivityUtilities.UtilitiesActivity;
import com.example.airconapp.view.AdvancedACSettings.AdvancedACSettingsActivity;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.AirConDetails.AirConDetailsActivity;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.Profile.ProfileActivity;
import com.example.airconapp.view.SearchResults.SearchResultsActivity;

import java.io.Serializable;


public class HelpActivity extends UtilitiesActivity implements View.OnClickListener {
    private Button backBtn;
    private Button homeBtn;
    private String prev_activity;
    private AirCon airCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Intent intent = getIntent();
        prev_activity = intent.getStringExtra("PREVIOUS_ACTIVITY");
        prev_activity = stringManipulation(prev_activity);
        airCon = (AirCon)intent.getSerializableExtra("AC");

        MenuActivity.profile.setFontSize(intent.getIntExtra("FONT", 1));

        applyFontSize(getResources().getConfiguration());

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == backBtn){
            Intent intent;
            if (prev_activity.equalsIgnoreCase(AirConActivity.class.toString()))
            {
                intent = new Intent(HelpActivity.this, AirConActivity.class);
            }
            else if (prev_activity.equalsIgnoreCase(AdvancedACSettingsActivity.class.toString()))
            {
                intent = new Intent(HelpActivity.this, AdvancedACSettingsActivity.class);
            }
            else if (prev_activity.equalsIgnoreCase(AirConDetailsActivity.class.toString()))
            {
                intent = new Intent(HelpActivity.this, AirConDetailsActivity.class);
            }
            else if (prev_activity.equalsIgnoreCase(SearchResultsActivity.class.toString()))
            {
                intent = new Intent(HelpActivity.this, SearchResultsActivity.class);
            }
            else if (prev_activity.equalsIgnoreCase(MenuActivity.class.toString()))
            {
                intent = new Intent(HelpActivity.this, MenuActivity.class);
            }
            else {

                intent = new Intent(HelpActivity.this, ProfileActivity.class);
            }

            intent.putExtra("FONT", MenuActivity.profile.getFontSize());
            intent.putExtra("AC", (Serializable) airCon);
            startActivity(intent);
        }
        if(view == homeBtn){
            Intent intent = new Intent(HelpActivity.this, MenuActivity.class);
            intent.putExtra("FONT", MenuActivity.profile.getFontSize());
            intent.putExtra("AC", (Serializable) airCon);
            startActivity(intent);
        }

    }
}
