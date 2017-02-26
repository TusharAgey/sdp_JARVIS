package jarvis.jarvis;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    final Context context = this;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tts = new TextToSpeech(this, this);
        db = new DatabaseHandler(this);
        final EditText etx = (EditText) findViewById(R.id.userNameText);
        Button buttonOne = (Button) findViewById(R.id.signupButton);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                db.AddUserName(etx.getText().toString());
                Intent intent = new Intent(context, LoginPage.class);
                startActivity(intent);
            }
        });
    }
    public void onInit(int status) {

        tts.speak("I'm JARVIS, what should I call you?", TextToSpeech.QUEUE_FLUSH, null);
    }
}
