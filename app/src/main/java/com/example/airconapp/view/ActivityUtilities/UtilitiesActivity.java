package com.example.airconapp.view.ActivityUtilities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.DisplayMetrics;
import android.widget.Button;

import com.example.airconapp.R;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.Profile.ProfileActivity;
import com.example.airconapp.view.SearchResults.SearchResultsActivity;

import java.util.ArrayList;

public abstract class UtilitiesActivity extends AppCompatActivity {

    private SpeechRecognizer speechRecognizer;
    final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    private final int RECORD_AUDIO_REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askVoicePermission();
    }


    //abstract public void handleSpeechRecognizer();

    private void askVoicePermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_REQUEST_CODE);
    }

    private void SpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //katalavainei ellinika. uparxei erwthsh sto stackoverflow gia language codes
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "el_GR");
        // to apo katw sxolio katalavainei to default tou kinitou:
        //speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float rmsdB) {
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
            }

            @Override
            public void onEndOfSpeech() {
                speechRecognizer.stopListening();
            }

            @Override
            public void onError(int error) {
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                //data.get(0) einai ola osa katalave, opote ta vgazei se toast
                //Toast.makeText(GPSActivity.this, data.get(0), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void applyFontSize(Configuration configuration){
        //0.85 small size, 1 normal size, 1.15 big etc
        if(MenuActivity.profile.getFontSize() == 0)
        {
            configuration.fontScale = (float) 0.85;
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
        else if (MenuActivity.profile.getFontSize() == 1)
        {
            configuration.fontScale = (float) 1;
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
        else {
            configuration.fontScale = (float) 1.15;
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
    }

    public void toggleSoundBtn(Button soundCommBtn)
    {
        if (MenuActivity.profile.isSoundCommands()) {
            soundCommBtn.setBackgroundResource(R.drawable.speaker_icon_muted);
        } else {
            soundCommBtn.setBackgroundResource(R.drawable.speaker_icon);
        }
        MenuActivity.profile.setSoundCommands(!MenuActivity.profile.isSoundCommands());
    }

    public void toggleSpeechBtn(Button speechCommBtn)
    {
        if (MenuActivity.profile.isSpeechCommands()) {
            speechCommBtn.setBackgroundResource(R.drawable.mic_icon_muted);
        } else {
            speechCommBtn.setBackgroundResource(R.drawable.mic_icon);
        }
        MenuActivity.profile.setSpeechCommands(!MenuActivity.profile.isSpeechCommands());
    }

    public void handleBackBtn(Activity context, Class destination)
    {
        Intent intent = new Intent(context, destination);
        intent.putExtra("FONT", MenuActivity.profile.getFontSize());
        startActivity(intent);
    }

    public void handleSettingsBtn(Activity context)
    {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("PREVIOUS_ACTIVITY", context.toString());
        startActivity(intent);
    }

    public String stringManipulation(String value)
    {
        int pos = value.indexOf("@");
        value = value.substring(0, pos);
        value = "class " + value;
        return value;
    }
}