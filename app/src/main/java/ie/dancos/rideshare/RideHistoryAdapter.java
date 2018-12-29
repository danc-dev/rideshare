package ie.dancos.rideshare;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RideHistoryAdapter extends RecyclerView.Adapter<RideHistoryAdapter.ViewHolder> {


    private ArrayList<RideObject> rideObjectArrayList = new ArrayList<RideObject>();
    private RideObject rideObject = new RideObject();



    //private RideHistoryAdapter

    public RideHistoryAdapter(ArrayList<RideObject> rideObjectArrayList){
        this.rideObjectArrayList = rideObjectArrayList;
    }

   // @NonNull
    @Override
    public RideHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_ride_history_row,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //each data item is  a string
        public TextView textView_ride;
        public TextView textView_pickup_location;
        public TextView textView_dropoff_location;
        public TextView textView_pickup_date;
        public TextView textView_pickup_time;

        public ViewHolder(View view){
            super(view);
            textView_ride = (TextView)view.findViewById(R.id.textView_ride);
            textView_pickup_location = (TextView)view.findViewById(R.id.textView_pickup_location);
            textView_dropoff_location = (TextView)view.findViewById(R.id.textView_dropoff_location);
            textView_pickup_date = (TextView)view.findViewById(R.id.textView_pickup_date);
            textView_pickup_time = (TextView)view.findViewById(R.id.textView_pickup_time);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");

                }
            });

        }
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {



        rideObject = rideObjectArrayList.get(position);

        String ride = rideObject.getRide();
        String pickupLocation = rideObject.getPickupLocation();
        String dropoffLocation = rideObject.getDropoffLocation();
        String pickupTime = rideObject.getPickupTime();
        String pickupDate = rideObject.getPickupDate();

        viewHolder.textView_ride.setText(ride);
        viewHolder.textView_pickup_location.setText(pickupLocation);
        viewHolder.textView_dropoff_location.setText(dropoffLocation);
        viewHolder.textView_pickup_time.setText(pickupTime);
        viewHolder.textView_pickup_date.setText(pickupDate);


    }

    @Override
    public int getItemCount() {
        int sizeOfArray = rideObjectArrayList.size();
        return sizeOfArray;
    }

    public ArrayList<RideObject> getRideObjectArrayList(){
        return rideObjectArrayList;
    }
}
