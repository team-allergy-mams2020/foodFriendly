///////////////////////////////////////////////////////////////////////////////
// Application:        Food Friendly
// Class:              DisplayActivity - Controller that displays the list of user's restaurants.
// Course:             Computer Science (Apps for Good - D Term)
//
// Authors:            Alan Chen, Esther Ng, Andrew Youssef
///////////////////////////////////////////////////////////////////////////////

package com.example.foodfriendly;

//Import Statements
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    //Log Tag - DisplayActivity
    private final String TAG = "In DisplayActivity";

    //List of restaurants to be obtained from MainActivity Intent
    ArrayList<Restaurant> restaurants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create view after receiving intent from MainActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //Read hashmap data from intent.
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                Log.d(TAG, String.format("%s %s (%s)", key,
                        value.toString(), value.getClass().getName()));
            }
        }

        restaurants = bundle.getParcelableArrayList("restaurants");

        //Initialize RecyclerView and display
        initRecyclerView();

    }

    private void initRecyclerView(){
        //Instantiate instance of adapter and display list of restaurants.
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.restaurant_list);
        RestaurantRecyclerViewAdapter adapter = new RestaurantRecyclerViewAdapter(restaurants,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
