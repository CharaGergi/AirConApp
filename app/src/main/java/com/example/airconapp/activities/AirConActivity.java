package com.example.airconapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.airconapp.R;

public class AirConActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button backBtn;
    private Button settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_con);

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
            Intent intent = new Intent(AirConActivity.this, MenuActivity.class);
            startActivity(intent);
        }
        if (view == settingsBtn) {
            Intent intent = new Intent(AirConActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}