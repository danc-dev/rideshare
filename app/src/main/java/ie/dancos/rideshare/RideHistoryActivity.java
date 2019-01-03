package ie.dancos.rideshare;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RideHistoryActivity extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView RecyclerView;
    private android.support.v7.widget.RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<RideObject> rideObjectArrayList = new ArrayList<RideObject>();

    private String userID;
    private FirebaseAuth mAuth;
    private DatabaseReference mRideshareDatabase;
     private String pick_up,drop_off,time,date, carType, phone,customerName,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_history);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mRideshareDatabase = FirebaseDatabase.getInstance().getReference().child("cabs").child("bookings").child(userID);

         getCustomerBookings2();

        //test
       // mAdapter= new RideHistoryAdapter(getRideHistoryTextData());

        RecyclerView = findViewById(R.id.recycleView);
        mLayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(mLayoutManager);


    }



    public ArrayList<RideObject>  getCustomerBookings2() {
        mRideshareDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        customerName = child.child("name").getValue().toString();
                        carType = child.child("carType").getValue().toString();
                        pick_up = child.child("pick_up").getValue().toString();
                        drop_off = child.child("drop_off").getValue().toString();
                        date = child.child("date").getValue().toString();
                        time = child.child("time").getValue().toString();

                        Log.i("ridedata", carType);
                        Log.i("ridedata", pick_up);
                        Log.i("ridedata", time);
                        Log.i("ridedata", date);
                        Log.i("ridedata", drop_off);

                    RideObject rideObject = new RideObject();
                    rideObject.setPickupLocation(pick_up);
                    rideObject.setName(customerName);
                    rideObject.setDropoffLocation(drop_off);
                    rideObject.setPickupDate(date);
                    rideObject.setRide(carType);
                    rideObject.setPickupTime(time);

                    rideObjectArrayList.add(rideObject);
                }
                    mAdapter= new RideHistoryAdapter( rideObjectArrayList);
                    RecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return rideObjectArrayList;
    }


    private ArrayList<RideObject> getRideHistoryTextData(){
        //test info
        RideObject rideObject = new RideObject();
        String pu1="17 The Cresent Bishopstown";
        String pu2="199 Douglas Road Cork";
        String pu3="Pacelli Brokeshire Lawn, Cork";
        String pu4="17 The Cresent Bishopstown";
        String do1="The city Centre";
        String do2="Paticks Street Cork";
        String do3="CIT , Bishopstown";
        String do4="The OmniPlex Cinema.";
        String put1="11:25";
        String put2="14:30";
        String put3="16:00";
        String put4="19:15";
        String pud1="15 Dec";
        String pud2="11 Dec";
        String pud3="3 Dec";
        String pud4="15 Nov";
        String ride1="Mini Cab";
        String ride2="Mini Bus";
        String ride3="Porsche";
        String ride4="Limo";

        rideObject.setPickupLocation(pu1);
        rideObject.setDropoffLocation(do1);
        rideObject.setPickupTime(put1);
        rideObject.setPickupDate(pud1);
        rideObject.setRide(ride1);
        rideObjectArrayList.add(rideObject);
        RideObject rideObject2 = new RideObject();
        rideObject2.setPickupLocation(pu2);
        rideObject2.setDropoffLocation(do2);
        rideObject2.setPickupTime(put2);
        rideObject2.setPickupDate(pud2);
        rideObject2.setRide(ride2);
        rideObjectArrayList.add(rideObject2);
        RideObject rideObject3 = new RideObject();
        rideObject3.setPickupLocation(pu3);
        rideObject3.setDropoffLocation(do3);
        rideObject3.setPickupTime(put3);
        rideObject3.setPickupDate(pud3);
        rideObject3.setRide(ride3);
        rideObjectArrayList.add(rideObject3);
        RideObject rideObject4 = new RideObject();
        rideObject4.setPickupLocation(pu4);
        rideObject4.setDropoffLocation(do4);
        rideObject4.setPickupTime(put4);
        rideObject4.setPickupDate(pud4);
        rideObject4.setRide(ride4);
        rideObjectArrayList.add(rideObject4);

        return rideObjectArrayList;
    }

}
