package ie.dancos.rideshare;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegistrationDoneActivity extends AppCompatActivity {
    private static int TIME_OUT = 3000; //Time to launch the another activity
    private EditText editText_phone, editText_name;
    private TextView textView_email;
    private Button button_save_customer_info, button_no_save;

    private String userID;
    private FirebaseAuth mAuth;
    private DatabaseReference mRideshareDatabase;

    private String Email, Name, Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_done);
        // get email and password from last screen
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Email = bundle.getString("email");
        Toast.makeText(RegistrationDoneActivity.this, "email " + Email, Toast.LENGTH_LONG).show();

        textView_email = findViewById(R.id.textView_email);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello ");
        stringBuilder.append(Email);
        stringBuilder.toString();
        textView_email.setText(stringBuilder.toString());

        editText_phone = findViewById(R.id.editText_phone);
        editText_name = findViewById(R.id.editText_name);

        button_save_customer_info = findViewById(R.id.button_save_customer_info);
        button_no_save = findViewById(R.id.button_no_save);


        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mRideshareDatabase = FirebaseDatabase.getInstance().getReference().child("cabs").child("customers").child(userID);

        getCustomerInfo_ifNotExist();

        button_save_customer_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveCustomerInfoToDatabase();

                Intent intent = new Intent(RegistrationDoneActivity.this, UserNavigationActivity.class);
                startActivity(intent);
            }
        });


        button_no_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegistrationDoneActivity.this, UserNavigationActivity.class);
                startActivity(intent);
            }
        });









/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(RegistrationDoneActivity.this, UserNavigationActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);

        */

    }


    private void getCustomerInfo_ifNotExist() {

        mRideshareDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("email") != null) {
                        Name = map.get("email").toString();
                    }
                    if (map.get("name") != null) {
                        Name = map.get("name").toString();
                        editText_name.setText(Name);
                    }
                    if (map.get("phone") != null) {
                        Phone = map.get("phone").toString();
                        editText_phone.setText(Phone);
                    }


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void saveCustomerInfoToDatabase(){
        // get info
        Name = editText_name.getText().toString();
        Phone = editText_phone.getText().toString();
        // add info to hash map
        Map customerInfo = new HashMap();
        customerInfo.put("email",Email);
        customerInfo.put("name",Name);
        customerInfo.put("phone",Phone);
        // add to database
        mRideshareDatabase.updateChildren(customerInfo);
        Toast.makeText(RegistrationDoneActivity.this, "Thanks "+Name+" your name and phone number was saved",Toast.LENGTH_LONG).show();

        // end activity
        finish();




    }

}
