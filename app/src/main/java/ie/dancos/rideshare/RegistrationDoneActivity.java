package ie.dancos.rideshare;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegistrationDoneActivity extends AppCompatActivity {
    private static int TIME_OUT = 3000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_done);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(RegistrationDoneActivity.this, UserNavigationActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
