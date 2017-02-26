package jarvis.jarvis;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class LoginPage extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        tts = new TextToSpeech(this, this);
        Button buttonOne = (Button) findViewById(R.id.loginButton);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Date d = new Date();
                int x = 1 + 5 + d.getDate() + d.getMonth();
                String s = Integer.valueOf(x).toString();
                TextView p = (TextView)findViewById(R.id.passwordTextBox);
                if(p.getText().toString().equals(s)){
                    Intent intent = new Intent(context, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Log.e("LG", s);
                    tts.speak("Wrong Password, please try again.", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
    public void onInit(int status) {
        tts.speak("Please fill in the password.", TextToSpeech.QUEUE_FLUSH, null);
    }
}
