package gr.aueb.arduinowakeup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;


public class MainActivity extends Activity implements OnClickListener {
    public RadioButton local;
    public RadioButton remote;
    public Button Wake;
    public String URL;
    public Button Exit;
    public Button Close;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        local = (RadioButton) findViewById(R.id.LocalRadioButton);
        remote = (RadioButton) findViewById(R.id.RemoteRadioButton);
        Wake = (Button) findViewById(R.id.WakeUpButton);
        Wake.setOnClickListener(this);
        local.setOnClickListener(this);
        remote.setOnClickListener(this);
        Close = (Button) findViewById(R.id.ShutDownButton);
        Close.setOnClickListener(this);
        Exit = (Button) findViewById(R.id.ExitAppButton);
        Exit.setOnClickListener((OnClickListener) this);
    }

    public void onClick(View v) {
        if (v == local) {
            URL = "http://192.168.1.9:2988";
            Toast message1 = Toast.makeText(this, "Local Wakeup is selected", Toast.LENGTH_LONG);
            message1.setGravity(Gravity.CENTER, message1.getXOffset() / 2, message1.getYOffset() / 2);
            message1.show();
        }
        if (v == remote) {
            URL = "http://yourDomain";
            Toast message1 = Toast.makeText(this, "Remote Wakeup is selected", Toast.LENGTH_LONG);
            message1.setGravity(Gravity.CENTER, message1.getXOffset() / 2, message1.getYOffset() / 2);
            message1.show();
        }
        if (v == Wake) {
            InputStream is = null;
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(URL);
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                is.close();
                Toast message1 = Toast.makeText(this, "WakeUp was successful!!", Toast.LENGTH_LONG);
                message1.setGravity(Gravity.CENTER, message1.getXOffset() / 2, message1.getYOffset() / 2);
                message1.show();

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
                Toast message3 = Toast.makeText(this, "Error in http connection", Toast.LENGTH_LONG);
                message3.setGravity(Gravity.CENTER, message3.getXOffset() / 2, message3.getYOffset() / 2);
                message3.show();
            }
        }
        if (v == Close) {
            InputStream is = null;
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(URL);
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                is.close();
                Toast message1 = Toast.makeText(this, "Emergency ShutDown was successful!!", Toast.LENGTH_LONG);
                message1.setGravity(Gravity.CENTER, message1.getXOffset() / 2, message1.getYOffset() / 2);
                message1.show();

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
                Toast message3 = Toast.makeText(this, "Error in http connection", Toast.LENGTH_LONG);
                message3.setGravity(Gravity.CENTER, message3.getXOffset() / 2, message3.getYOffset() / 2);
                message3.show();
            }
        }
        if (v == Exit) {
            finish();
        }

    }
}