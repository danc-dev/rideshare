package ie.dancos.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BookingConfirmActivity extends AppCompatActivity {

    private String userID;
    private FirebaseAuth mAuth;
    private DatabaseReference mRideshareDatabase;
    private String pick_up,drop_off,time,date, carType, phone,customerName,email;
    private TextView textView_drop_off_location,textView_pick_up_location, textView_car_type,textView_date,textView_time;

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
        pick_up = bundle.getString("pick_up");
        drop_off = bundle.getString("drop_off");
        time = bundle.getString("time");
        date = bundle.getString("date");

        textView_pick_up_location = findViewById(R.id.textView_pick_up_location);
        textView_pick_up_location.setText(pick_up);

        textView_drop_off_location = findViewById(R.id.textView_drop_off_location);
        textView_drop_off_location.setText(drop_off);

        TextView textView_pick_up_date_text = findViewById(R.id.textView_pick_up_date_text);
        textView_pick_up_date_text.setVisibility(View.GONE);
        textView_date = findViewById(R.id.textView_date);
        textView_date.setVisibility(View.GONE);


        textView_time = findViewById(R.id.textView_time);
        if(now){
            textView_time.setText("ASAP");}
        if(!now){
            textView_pick_up_date_text.setVisibility(View.VISIBLE);
            textView_time.setText(time);
            textView_date.setVisibility(View.VISIBLE);
            textView_date.setText(date);}


         textView_car_type = findViewById(R.id.textView_car_type);

        ImageView imageView_car_picked = findViewById(R.id.imageView_car_picked);


        if (mini_cab){imageView_car_picked.setImageResource(R.drawable.mini_cab);textView_car_type.setText("Mini Cab");}
        if (mini_bus){imageView_car_picked.setImageResource(R.drawable.mini_bus);textView_car_type.setText("Mini Bus");}
        if (limo){imageView_car_picked.setImageResource(R.drawable.limo);textView_car_type.setText("Limo");}
        if (sports){imageView_car_picked.setImageResource(R.drawable.porsche);textView_car_type.setText("Porsche");}

        carType = textView_car_type.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mRideshareDatabase = FirebaseDatabase.getInstance().getReference().child("cabs").child("customers").child(userID);

        getCustomerNameAndPhone();

        getCustomerBooking_ifNotExist();



        Button button_confirm_and_pay = findViewById(R.id.button_confirm_and_pay);

        button_confirm_and_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookingConfirmActivity.this, "Thanks your booking was saved",Toast.LENGTH_LONG).show();

                saveCustomerBookingInfoToDatabase();
                Intent intent = new Intent(BookingConfirmActivity.this,ThanksActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getCustomerNameAndPhone() {
        mRideshareDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot child:dataSnapshot.getChildren()){
                        if (child.getKey().equals("name")){
                            customerName = child.getValue().toString();
                        }
                        if (child.getKey().equals("phone")){
                            phone = child.getValue().toString();
                        }
                        if (child.getKey().equals("email")){
                            email = child.getValue().toString();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getCustomerBooking_ifNotExist() {

        mRideshareDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("pick_up") != null) {
                        pick_up = map.get("pick_up").toString();
                        textView_pick_up_location.setText(pick_up);
                    }
                    if (map.get("drop_off") != null) {
                        drop_off = map.get("drop_off").toString();
                        textView_drop_off_location.setText(drop_off);
                    }
                    if (map.get("carType") != null) {
                        carType = map.get("phone").toString();
                        textView_car_type.setText(carType);
                    }
                    if (map.get("date") != null) {
                        date = map.get("date").toString();
                        textView_date.setText(date);
                    }
                    if (map.get("time") != null) {
                        time = map.get("time").toString();
                        textView_time.setText(time);
                    }



                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void saveCustomerBookingInfoToDatabase(){
        // get info
        pick_up = textView_pick_up_location.getText().toString();
        drop_off = textView_drop_off_location.getText().toString();
        time = textView_time.getText().toString();
        date = textView_date.getText().toString();
        // add info to hash map
        Map customerBookingInfo = new HashMap();
        customerBookingInfo.put("carType",carType);
        customerBookingInfo.put("pick_up",pick_up);
        customerBookingInfo.put("drop_off",drop_off);
        customerBookingInfo.put("time",time);
        customerBookingInfo.put("date",date);
        customerBookingInfo.put("name",customerName);
        customerBookingInfo.put("phone",phone);
        customerBookingInfo.put("email",email);


        Random random = new Random();
        int randomNumber = random.nextInt(999999) + 100;
        String randomNumberString=String.valueOf(randomNumber);
        String bookingNumber = "booking_"+randomNumberString;
        // add to database
      //  mRideshareDatabase = FirebaseDatabase.getInstance().getReference().child("cabs").child("bookings").child(userID).child(bookingNumber);
        mRideshareDatabase = FirebaseDatabase.getInstance().getReference().child("cabs").child("bookings").child(userID).push();
        mRideshareDatabase.updateChildren(customerBookingInfo);
        Toast.makeText(BookingConfirmActivity.this, "Thanks\n Your booking was saved",Toast.LENGTH_LONG).show();

        finish();
    }

}
