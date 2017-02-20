package jarvis.jarvis;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class HomePage extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    Managers mngr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        txtSpeechInput = (TextView) findViewById(R.id.listenedText);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        mngr = new Managers();

        tts = new TextToSpeech(this, this);

        // hide the action bar
       // getActionBar().hide();

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();

            }
        });
    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    mngr.analyse(result.get(0), this);
                    /*
                     * Here, we get the result of the speech input.
                     * Now create a class with methods accordingly and then make the application do the required actions.
                     * 1)Chat with you
                     * 2)Manage Contacts
                     * 3)Manage Passwords
                     * 4)Manage Study
                     * 5)Introduce Someone
                     */
                }
                break;
            }
        }
    }
    public void onInit(int status) {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String greet = "";
        if(timeOfDay >= 0 && timeOfDay < 12)
            greet = "Good Morning";
        else if(timeOfDay >= 12 && timeOfDay < 16)
            greet = "Good Afternoon";
        else if(timeOfDay >= 16 && timeOfDay < 21)
            greet = "Good Evening";
        else if(timeOfDay >= 21 && timeOfDay < 24)
            greet = "Good Night";
        speakOut(greet);
        Log.e("TTS", "whatever");
    }
    void speakOut(String spk){
        tts.speak(spk, TextToSpeech.QUEUE_FLUSH, null);
    }

}

class Managers{
   private  HomePage pag;
    private Context context;
    void analyse(String cmd, HomePage p) {
        pag = p;
        context = pag;
        if(cmd.contains("manage my contact"))
            contactManager();
        else if(cmd.contains("manage my password"))
            passwordManager();
        else if(cmd.contains("manage my study"))
            studyManager();
        else if (cmd.contains("introduce someone"))
            introductionManager();
        else{
            //Now, here is a statement that is not hardcoded.
            //So analyse this statement & it possibly can be a tricky statement.
            //Its upto you now! ;-) //decision tree might help

        }
    }
    private void contactManager(){
        //Intent intent = new Intent(context, SignUp.class);
        //pag.startActivity(intent);
        Log.e("CTM", "managing contacts");
    }

    private void passwordManager(){

    }

    private void studyManager(){

    }
    private void introductionManager(){

    }

}

