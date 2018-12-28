package ie.dancos.rideshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

     private boolean mini_cab, mini_bus,sports, limo, day;
    private int mini_cab_int=1, mini_bus_int=3,sports_int=6, limo_int=5;
    private double night=1.6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);



        Button button_calculate = findViewById(R.id.button_calculate);
        final TextView textView_cost = findViewById(R.id.textView_cost);
        final EditText editText_distance = findViewById(R.id.editText_distance);

        day=true;



        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String distanceString = (String.valueOf(editText_distance.getText()));

                //error check

                if (distanceString.equals("")) {
                    Toast.makeText(CalculatorActivity.this, "Please input the journey distance",
                            Toast.LENGTH_LONG).show();
                } else {
                    int distance = 0;
                    distance = Integer.parseInt(distanceString);

                    //error check
                    if (distance == 0 | distance < 0)
                        Toast.makeText(CalculatorActivity.this, "Please input a real journey distance",
                                Toast.LENGTH_LONG).show();
                    else {

                        double cost = 1;

                        if (mini_cab) {
                            cost = distance * mini_cab_int;
                        } else if (mini_bus) {
                            cost = distance * mini_bus_int;
                        } else if (sports) {
                            cost = distance * sports_int;
                        } else if (limo) {
                            cost = distance * limo_int;
                        }
                        if (!day) {
                            cost = cost * night;
                        }

                        String costString =String.format("%.2f", cost);
                        StringBuilder stringBuilder =new StringBuilder();
                        stringBuilder.append(costString);
                        stringBuilder.append(" Euros");
                        textView_cost.setText(String.valueOf(stringBuilder));
                    }

                }

            }
        });


    }





    public void onClicked_pick_time(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_day:
                if (checked)
                    day = true;
                break;
            case R.id.rb_night:
                if (checked)
                    day=false;
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
