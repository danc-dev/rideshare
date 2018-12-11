package ie.dancos.rideshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class BookingActivity extends Activity {
    private boolean mini_cab, mini_bus,sports, limo;
    private EditText editText_pick_up,editText_drop_off,editText_date,editText_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Button booking = findViewById(R.id.button_make_booking);
        editText_pick_up = findViewById(R.id.editText_pick_up);
         editText_drop_off = findViewById(R.id.editText_drop_off);
         editText_date= findViewById(R.id.editText_date);
       editText_time = findViewById(R.id.editText_time);


        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BookingActivity.this,BookingConfirmActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("pick_up", String.valueOf(editText_pick_up.getText()));
                bundle.putString("drop_off",String.valueOf(editText_drop_off.getText()));
                bundle.putString("time",String.valueOf(editText_time.getText()));
                bundle.putString("date",String.valueOf(editText_date.getText()));
                bundle.putBoolean("mini_cab",mini_cab);
                bundle.putBoolean("mini_bus",mini_bus);
                bundle.putBoolean("limo",limo);
                bundle.putBoolean("sports",sports);
                intent.putExtras(bundle);
                startActivity(intent);
             }
        });






    }


    public void onClicked_pick_ride(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_mini_cab:
                if (checked)
                    mini_cab = true;
                break;
            case R.id.rb_mini_bus:
                if (checked)
                    mini_bus = true;
                break;
            case R.id.rb_limo:
                if (checked)
                    limo = true;
                break;
            case R.id.rb_sports:
                if (checked)
                    sports = true;
                break;
        }
    }



}
