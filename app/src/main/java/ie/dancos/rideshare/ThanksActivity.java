package ie.dancos.rideshare;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class ThanksActivity extends AppCompatActivity {
    private static int TIME_OUT = 3000; //Time to launch the another activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(ThanksActivity.this, UserNavigationActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
