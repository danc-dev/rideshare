package ie.dancos.rideshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BookingConfirmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Boolean mini_cab = bundle.getBoolean("mini_cab");
        Boolean mini_bus = bundle.getBoolean("mini_bus");
        Boolean limo = bundle.getBoolean("limo");
        Boolean sports = bundle.getBoolean("sports");
        String pick_up = bundle.getString("pick_up");
        String drop_off = bundle.getString("drop_off");
        String time = bundle.getString("time");
        String date = bundle.getString("date");

        TextView textView_to_pick_you = findViewById(R.id.textView_to_pick_you);
        textView_to_pick_you.setText(pick_up);

        TextView textView_drop_off = findViewById(R.id.textView_drop_off);
        textView_drop_off.setText(drop_off);

        TextView textView_time = findViewById(R.id.textView_time);
        textView_time.setText(time);

        TextView textView_date = findViewById(R.id.textView_date);
        textView_date.setText(date);


        ImageView imageView_car_picked = findViewById(R.id.imageView_car_picked);

        if (mini_cab){imageView_car_picked.setImageResource(R.drawable.mini_cab);}
        if (mini_bus){imageView_car_picked.setImageResource(R.drawable.mini_bus);}
        if (limo){imageView_car_picked.setImageResource(R.drawable.limo);}
        if (sports){imageView_car_picked.setImageResource(R.drawable.porsche);}





    }




}
