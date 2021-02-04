package com.example.airconapp.view.ActivityUtilities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
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

public class UtilitiesActivity extends AppCompatActivity
{
    public SpeechRecognizer speechRecognizer;
    final public Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    private final int RECORD_AUDIO_REQUEST_CODE = 1;
    private ArrayList<String> data;
    private static Class contextToGoBackTo;
    private static AirCon airCon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askVoicePermission();
        hideUI();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void askVoicePermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_REQUEST_CODE);
    }

    public void SpeechRecognizer(final Activity context, final int menuFont) {
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "el_GR");
        //speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); // device default
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {
                speechRecognizer.stopListening();
            }

            @Override
            public void onError(int error) {}

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}

            @Override
            public void onResults(Bundle results) {
                data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                handleSpeechCommands(data, context, airCon, menuFont);
            }

        });
    }

    public void handleSpeechCommands(ArrayList<String> data, Activity context, AirCon airCon, int menuFont)
    {
        StringTokenizer tokenizer = new StringTokenizer(data.get(0));
        while(tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            if (token.equalsIgnoreCase("ρυθμίσεις"))
            {
                contextToGoBackTo = findPreviousActivity(context.toString());
                handleSettingsBtn(context, airCon);
            }
            else if (token.equalsIgnoreCase("εύρεση"))
            {
                if (tokenizer.nextToken().equalsIgnoreCase("κλιματιστικών"))
                {
                    contextToGoBackTo = findPreviousActivity(context.toString());
                    searchSelectedAndStartActivity(new AirCon().getName(), context, SearchResultsActivity.class, menuFont);
                }
            }
            else if (token.equalsIgnoreCase("βασικές"))
            {
                String acName = tokenizer.nextToken();
                contextToGoBackTo = findPreviousActivity(context.toString());
                searchSelectedAndStartActivity(acName, context, AirConActivity.class, menuFont);
            }
            else if (token.equalsIgnoreCase("λεπτομέρειες"))
            {
                String acName = tokenizer.nextToken();
                contextToGoBackTo = findPreviousActivity(context.toString());
                searchSelectedAndStartActivity(acName, context, AirConDetailsActivity.class, menuFont);
            }
            else if (token.equalsIgnoreCase("πρόσθετες"))
            {
                String acName = tokenizer.nextToken();
                contextToGoBackTo = findPreviousActivity(context.toString());
                searchSelectedAndStartActivity(acName, context, AdvancedACSettingsActivity.class, menuFont);
            }
            else if (token.equalsIgnoreCase("θερμοκρασία"))
            {
                int temp = Integer.parseInt(tokenizer.nextToken());
                String acName = tokenizer.nextToken();

                System.out.println("temp given : " +temp+ " ac given : " +acName);

                for (AirCon ac : Utilities.getSelectedAirCons())
                {
                    if (acName.equalsIgnoreCase(ac.getName()))
                    {
                        ac.setTemperature(temp);
                    }
                }
            }
            else if (token.equalsIgnoreCase("άνοιξε"))
            {
                String acName = tokenizer.nextToken();

                System.out.println("ac given : " +acName);

                for (AirCon ac : Utilities.getSelectedAirCons())
                {
                    if (acName.equalsIgnoreCase(ac.getName()))
                    {
                        if (!ac.isPower())
                        {
                            ac.setPower(true);
                            System.out.println("air con : " +ac.getName()+ " power : " + ac.isPower());
                        }
                        else return;
                    }
                }
            }
            else if (token.equalsIgnoreCase("κλείσε"))
            {
                String acName = tokenizer.nextToken();

                System.out.println("ac given : " +acName);

                for (AirCon ac : Utilities.getSelectedAirCons())
                {
                    if (acName.equalsIgnoreCase(ac.getName()))
                    {
                        if (ac.isPower())
                        {
                            ac.setPower(false);
                            System.out.println("air con : " +ac.getName()+ " power : " + ac.isPower());
                        }
                        else return;
                    }
                }
            }
            else if (token.equalsIgnoreCase("πίσω"))
            {
                if (airCon == null) airCon = new AirCon();
                handleBackBtn(context, contextToGoBackTo, airCon);
            }
            else if (token.equalsIgnoreCase("αρχική"))
            {
                handleBackBtn(context, MenuActivity.class, airCon);
            }
            else if (token.equalsIgnoreCase("βοήθεια"))
            {
                contextToGoBackTo = findPreviousActivity(context.toString());
                handleHelpBtn(context, airCon);
            }
            else
            {
                return;
            }
        }
    }

    private Class findPreviousActivity(String prev_activity)
    {
        prev_activity = stringManipulation(prev_activity);
        if (prev_activity.equalsIgnoreCase(AirConActivity.class.toString()))
        {
            return AirConActivity.class;
        }
        else if (prev_activity.equalsIgnoreCase(AdvancedACSettingsActivity.class.toString()))
        {
            return AdvancedACSettingsActivity.class;
        }
        else if (prev_activity.equalsIgnoreCase(AirConDetailsActivity.class.toString()))
        {
            return AirConDetailsActivity.class;
        }
        else if (prev_activity.equalsIgnoreCase(SearchResultsActivity.class.toString()))
        {
            return SearchResultsActivity.class;
        }
        else
        {
            return MenuActivity.class;
        }
    }

    private void searchSelectedAndStartActivity(String acName, Activity context, Class destination, int menuFont)
    {
        Intent intent = new Intent(context, destination);
        intent.putExtra("FONT", menuFont);
        
        if (acName != null)
        {
            for (AirCon ac : Utilities.getSelectedAirCons())
            {
                if (acName.equalsIgnoreCase(ac.getName()))
                {
                    intent.putExtra("AC", (Serializable) ac);
                }
            }
        }
        else
        {
            intent.putExtra("AC", (Serializable) new AirCon());
        }

        startActivity(intent);
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

    public void handleBackBtn(Activity context, Class destination, AirCon airCon)
    {
        if (airCon == null) airCon = new AirCon();
        Intent intent = new Intent(context, destination);
        intent.putExtra("FONT", MenuActivity.profile.getFontSize());
        intent.putExtra("AC", (Serializable) airCon);
        startActivity(intent);
    }

    public void handleSettingsBtn(Activity context, AirCon airCon)
    {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("PREVIOUS_ACTIVITY", context.toString());
        intent.putExtra("AC", (Serializable) airCon);
        startActivity(intent);
    }

    public void handleHelpBtn(Activity context, AirCon airCon)
    {
        Intent intent = new Intent(context, HelpActivity.class);
        intent.putExtra("PREVIOUS_ACTIVITY", context.toString());
        intent.putExtra("FONT", MenuActivity.profile.getFontSize());
        intent.putExtra("AC", (Serializable) airCon);
        startActivity(intent);
    }

    public String stringManipulation(String value)
    {
        int pos = value.indexOf("@");
        value = value.substring(0, pos);
        value = "class " + value;
        return value;
    }

    private void hideUI(){
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;

        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            System.out.println("Turning immersive mode mode off. ");
        } else {
            System.out.println("Turning immersive mode mode on.");
        }

        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // has white space above logo/when status bar isn't shown
        /*if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }*/

        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }
}