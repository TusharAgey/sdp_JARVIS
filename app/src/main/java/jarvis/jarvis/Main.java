package jarvis.jarvis;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tts = new TextToSpeech(this, this);
        Button buttonOne = (Button) findViewById(R.id.exitButton);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
    public void onInit(int status) {
        /*
         * Retrieve isFirstTime flag from the DB
         * If yes, then do following.
         * else directly go to the Login page
         */
        tts.speak("Welcome to the World of JARVIS", TextToSpeech.QUEUE_FLUSH, null);
        Intent intent = new Intent(context, OneTimeHelp.class);
        startActivity(intent);
    }
}
