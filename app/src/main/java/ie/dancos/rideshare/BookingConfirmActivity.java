package ie.dancos.rideshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BookingConfirmActivity extends AppCompatActivity {

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
        Boolean now = bundle.getBoolean("now");
        String pick_up = bundle.getString("pick_up");
        String drop_off = bundle.getString("drop_off");
        String time = bundle.getString("time");
        String date = bundle.getString("date");

        TextView textView_pick_up_location = findViewById(R.id.textView_pick_up_location);
        textView_pick_up_location.setText(pick_up);

        TextView textView_drop_off_location = findViewById(R.id.textView_drop_off_location);
        textView_drop_off_location.setText(drop_off);

        TextView textView_pick_up_date_text = findViewById(R.id.textView_pick_up_date_text);
        textView_pick_up_date_text.setVisibility(View.GONE);
        TextView textView_date = findViewById(R.id.textView_date);
        textView_date.setVisibility(View.GONE);


        TextView textView_time = findViewById(R.id.textView_time);
        if(now){
            textView_time.setText("ASAP");}
        if(!now){
            textView_pick_up_date_text.setVisibility(View.VISIBLE);
            textView_time.setText(time);
            textView_date.setVisibility(View.VISIBLE);
            textView_date.setText(date);}


        TextView textView_car_type = findViewById(R.id.textView_car_type);

        ImageView imageView_car_picked = findViewById(R.id.imageView_car_picked);

        if (mini_cab){imageView_car_picked.setImageResource(R.drawable.mini_cab);textView_car_type.setText("Mini Cab");}
        if (mini_bus){imageView_car_picked.setImageResource(R.drawable.mini_bus);textView_car_type.setText("Mini Bus");}
        if (limo){imageView_car_picked.setImageResource(R.drawable.limo);textView_car_type.setText("Limo");}
        if (sports){imageView_car_picked.setImageResource(R.drawable.porsche);textView_car_type.setText("Porsche");}


        Button button_confirm_and_pay = findViewById(R.id.button_confirm_and_pay);

        button_confirm_and_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingConfirmActivity.this,ThanksActivity.class);
                startActivity(intent);
            }
        });


    }




}
