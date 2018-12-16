package ie.dancos.rideshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class BookingActivity extends Activity {
    private boolean mini_cab, mini_bus,sports, limo, now;
    private EditText editText_pick_up,editText_drop_off,editText_date,editText_time;
    private TextView textView_pick_up_time,textView_pick_up_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Button booking = findViewById(R.id.button_make_booking);
        editText_pick_up = findViewById(R.id.editText_pick_up);
        editText_drop_off = findViewById(R.id.editText_drop_off);
        editText_date= findViewById(R.id.editText_date);
        editText_date.setVisibility(View.GONE);
         textView_pick_up_time = findViewById(R.id.textView_pick_up_time);
         textView_pick_up_time.setVisibility(View.GONE);
        textView_pick_up_date = findViewById(R.id.textView_pick_up_date);
        textView_pick_up_date.setVisibility(View.GONE);

        editText_time = findViewById(R.id.editText_time);
        editText_time.setVisibility(View.GONE);

        //set deflaut of now
        now=true;


        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BookingActivity.this,BookingConfirmActivity.class);

                // default of min-cab - set min-cab to true in the case where no option was selected by the customer
                if (!sports & !limo & !mini_bus) { mini_cab=true;}
                Bundle bundle = new Bundle();
              //  if(later) {
              //      bundle.putString("time", String.valueOf(editText_time.getText()));
              //      bundle.putString("date", String.valueOf(editText_date.getText()));
              //  }
             //   if(now){
                bundle.putBoolean("now",now);
                bundle.putString("pick_up", String.valueOf(editText_pick_up.getText()));
                bundle.putString("drop_off", String.valueOf(editText_drop_off.getText()));
                bundle.putString("date", String.valueOf(editText_date.getText()));
                bundle.putString("time", String.valueOf(editText_time.getText()));
                bundle.putBoolean("mini_cab",mini_cab);
                bundle.putBoolean("mini_bus",mini_bus);
                bundle.putBoolean("limo",limo);
                bundle.putBoolean("sports",sports);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });





    }

    public void onClicked_pick_time(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_now:
                if (checked)
                    now = true;
                textView_pick_up_time.setVisibility(View.GONE);
                editText_time.setVisibility(View.GONE);
                textView_pick_up_date.setVisibility(View.GONE);
                editText_date.setVisibility(View.GONE);
                break;
            case R.id.rb_set_a_time:
                if (checked)
                    now=false;
                textView_pick_up_time.setVisibility(View.VISIBLE);
                editText_time.setVisibility(View.VISIBLE);
                textView_pick_up_date.setVisibility(View.VISIBLE);
                editText_date.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void onClicked_pick_ride(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_mini_cab:
                if (checked)
                    mini_cab = true;mini_bus=false;limo=false;sports=false;
                break;
            case R.id.rb_mini_bus:
                if (checked)
                    mini_bus = true;mini_cab=false;limo=false;sports=false;
                break;
            case R.id.rb_limo:
                if (checked)
                    limo = true;mini_cab=false;mini_bus=false;sports=false;
                break;
            case R.id.rb_sports:
                if (checked)
                    sports = true;mini_cab=false;mini_bus=false;limo=false;
                break;
        }
    }



}

