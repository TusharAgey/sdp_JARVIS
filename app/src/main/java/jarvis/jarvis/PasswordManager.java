package jarvis.jarvis;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

public class PasswordManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manager);
        final Button clr = (Button)findViewById(R.id.buttonClear);
        final Button sav = (Button)findViewById(R.id.buttonSave);
        final Button show = (Button)findViewById(R.id.buttonShowPass);
        final TextView accName = (TextView) findViewById(R.id.accName);
        final TextView accuName = (TextView)findViewById(R.id.accUsername);
        final TextView accpass  = (TextView)findViewById(R.id.accPassword);
        final TextView accpass2 = (TextView)findViewById(R.id.accPassword2);
        final ListView lv = (ListView)findViewById(R.id.accList);
        final DatabaseHandler db = new DatabaseHandler(this);
        List<Account_Pass> lis = db.getAllAccounts();
        final ArrayAdapter<Account_Pass> adapter = new ArrayAdapter<Account_Pass>(this,android.R.layout.simple_list_item_1,lis);
        clr.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                accName.setText("");
                accuName.setText("");
                accpass.setText("");
                accpass2.setText("");
            }
        });


        sav.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(accpass.getText().toString().equals(accpass2.getText().toString())){
                    Account_Pass ct = new Account_Pass(accuName.getText().toString(), accpass.getText().toString(), accName.getText().toString());
                    db.addPass(ct);
                }
                else{
                    accName.setText("passwords doesn't match!");
                }

            }
        });

        show.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                lv.setVisibility(View.VISIBLE);
                lv.setAdapter(adapter);
            }
        });
    }
}