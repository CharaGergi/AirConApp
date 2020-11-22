package com.example.airconapp.view.AirConDetails;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.airconapp.R;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.Profile.ProfileActivity;

public class AirConDetailsActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button backBtn;
    private Button settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_con_details);

        backBtn = findViewById(R.id.back_button);
        backBtn.setOnClickListener(this);

        settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view)
    {
        if (view == backBtn) {
            Intent intent = new Intent(AirConDetailsActivity.this, AirConActivity.class);
            startActivity(intent);
        }
        if (view == settingsBtn) {
            Intent intent = new Intent(AirConDetailsActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}