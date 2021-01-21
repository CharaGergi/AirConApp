package com.example.airconapp.view.ActivityUtilities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.DisplayMetrics;
import android.widget.Button;
import com.example.airconapp.R;
import com.example.airconapp.domain.AirCon;
import com.example.airconapp.domain.Utilities;
import com.example.airconapp.view.AdvancedACSettings.AdvancedACSettingsActivity;
import com.example.airconapp.view.AirCon.AirConActivity;
import com.example.airconapp.view.AirConDetails.AirConDetailsActivity;
import com.example.airconapp.view.Help.HelpActivity;
import com.example.airconapp.view.Menu.MenuActivity;
import com.example.airconapp.view.Profile.ProfileActivity;
import com.example.airconapp.view.SearchResults.SearchResultsActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class UtilitiesActivity extends AppCompatActivity {

    private SpeechRecognizer speechRecognizer;
    final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    private final int RECORD_AUDIO_REQUEST_CODE = 1;
    private ArrayList<String> data;
    private Class contextToGoBackTo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askVoicePermission();
    }


    //abstract public void handleSpeechRecognizer();

    private void askVoicePermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_REQUEST_CODE);
    }

    private void SpeechRecognizer(final Activity context, final AirCon airCon, final int menuFont) {
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
                data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                handleSpeechCommands(data, context, airCon, menuFont);
                //data.get(0) einai ola osa katalave, opote ta vgazei se toast
                //Toast.makeText(GPSActivity.this, data.get(0), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleSpeechCommands(ArrayList<String> data, Activity context, AirCon airCon, int menuFont)
    {
        StringTokenizer tokenizer = new StringTokenizer(data.get(0));
        while(tokenizer.hasMoreTokens())
        {
            if (tokenizer.nextToken().equalsIgnoreCase("προφίλ"))
            {
                handleSettingsBtn(context, airCon);
            }
            else if (tokenizer.nextToken().equalsIgnoreCase("μενού"))
            {
                handleBackBtn(context, MenuActivity.class);
            }
            else if (tokenizer.nextToken().equalsIgnoreCase("λεπτομέρειες"))
            {
                String acName = tokenizer.nextToken(tokenizer.nextToken());
                findPreviousActivity(context.toString(), context);
                searchSelectedAndStartActivity(acName, context, AirConDetailsActivity.class, menuFont);
            }
            else if (tokenizer.nextToken().equalsIgnoreCase("πρόσθετες"))
            {
                String acName = tokenizer.nextToken(tokenizer.nextToken());
                findPreviousActivity(context.toString(), context);
                searchSelectedAndStartActivity(acName, context, AdvancedACSettingsActivity.class, menuFont);
            }
            else if (tokenizer.nextToken().equalsIgnoreCase("θερμοκρασία"))
            {
                int temp = Integer.parseInt(tokenizer.nextToken(tokenizer.nextToken()));
                String acName = tokenizer.nextToken(String.valueOf(temp));

                for (AirCon ac : Utilities.getSelectedAirCons())
                {
                    if (acName.equalsIgnoreCase(ac.getName()))
                    {
                        ac.setTemperature(temp);
                    }
                }
            }
            else if (tokenizer.nextToken().equalsIgnoreCase("πίσω"))
            {
                handleBackBtn(context, contextToGoBackTo);
            }
        }
    }

    private void findPreviousActivity(String prev_activity, Activity context)
    {
        if (prev_activity.equalsIgnoreCase(AirConActivity.class.toString()))
        {
            contextToGoBackTo =  AirConActivity.class;
        }
        else if (prev_activity.equalsIgnoreCase(AdvancedACSettingsActivity.class.toString()))
        {
            contextToGoBackTo =  AdvancedACSettingsActivity.class;
        }
        else if (prev_activity.equalsIgnoreCase(AirConDetailsActivity.class.toString()))
        {
            contextToGoBackTo =  AirConDetailsActivity.class;
        }
        else if (prev_activity.equalsIgnoreCase(SearchResultsActivity.class.toString()))
        {
            contextToGoBackTo =  SearchResultsActivity.class;
        }
        else
        {
            contextToGoBackTo =  MenuActivity.class;
        }
    }

    private void searchSelectedAndStartActivity(String acName, Activity context, Class destination, int menuFont)
    {
        for (AirCon ac : Utilities.getSelectedAirCons())
        {
            if (acName.equalsIgnoreCase(ac.getName()))
            {
                Intent intent = new Intent(context, destination);
                intent.putExtra("FONT", menuFont);
                intent.putExtra("AC", (Serializable) ac);
                startActivity(intent);
            }
        }
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

    public void handleSettingsBtn(Activity context, AirCon airCon)
    {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("PREVIOUS_ACTIVITY", context.toString());
        intent.putExtra("AC", (Serializable) airCon);
        startActivity(intent);
    }

    public void handleHelpBtn(Activity context, int font)
    {
        Intent intent = new Intent(context, HelpActivity.class);
        intent.putExtra("PREVIOUS_ACTIVITY", context.toString());
        intent.putExtra("FONT", font);
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