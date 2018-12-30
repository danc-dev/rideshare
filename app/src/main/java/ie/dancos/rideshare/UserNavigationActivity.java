package ie.dancos.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_user);


        final Button button_go2_make_booking = findViewById(R.id.button_go2_make_booking);
        button_go2_make_booking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserNavigationActivity.this,BookingActivity.class);                   startActivity(intent);
            }
        });



        final Button button_go2_map = findViewById(R.id.button_go2_map);
        button_go2_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserNavigationActivity.this,MapsActivity.class);                       startActivity(intent);
            }
        });


        final Button button_go2_ride_history = findViewById(R.id.button_go2_ride_history);
        button_go2_ride_history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserNavigationActivity.this,RideHistoryActivity.class);                       startActivity(intent);
            }
        });

        final Button button_go2_calculate_cost = findViewById(R.id.button_go2_calculate_cost);
        button_go2_calculate_cost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserNavigationActivity.this,CalculatorActivity.class);                   startActivity(intent);
            }
        });

    }
}
