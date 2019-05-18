///////////////////////////////////////////////////////////////////////////////
// Application:        Food Friendly
// Class:              MenuDisplayActivity - Controller that displays the list of a restaurant's menu items.
// Course:             Computer Science (Apps for Good - D Term)
//
// Authors:            Alan Chen, Esther Ng, Andrew Youssef
///////////////////////////////////////////////////////////////////////////////
package com.example.foodfriendly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuDisplayActivity extends AppCompatActivity {

    //Log Tag - MenuDisplayActivity
    private final String TAG = "in MenuDisplayActivity";

    //Name of restaurant
    private String restaurantName;

    //List of menu items
    private ArrayList<Menu_Item> menuItems;

    //List of menu items and their availability - used by RecyclerView
    private ArrayList<Menu_Item> mItems = new ArrayList<>();
    private ArrayList<Boolean> mAvailable = new ArrayList<>();

    //OnCreate method
    protected void onCreate(Bundle savedInstanceState) {
        //Creates view after receiving intent from RestaurantRecyclerViewAdapter
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudisplay);

        //Unpacks the restaurant name and menu items from intent and reads them into variables
        Intent intent = getIntent();
        restaurantName = intent.getStringExtra("restaurantName");
        Bundle bundle = intent.getExtras();
        menuItems = bundle.getParcelableArrayList("restaurantItems");

        //Displays restaurant name at top of screen
        TextView restaurant_name = findViewById(R.id.item_name);
        restaurant_name.setText(restaurantName);

        //Reads menuItems into mItems and mAvailable such that they are ordered by availability
        //mAvailable indices are not linked to mItems, but they are read in a manner such that
        //each item is still aligned with its availability value.
        for(Menu_Item mi:menuItems) {
            if(mi != null && mi.isAvailable()) {
                mItems.add(mi);
                mAvailable.add(mi.isAvailable());
            }
        }
        for(Menu_Item mi : menuItems) {
            if (mi != null && !mi.isAvailable()) {
                mItems.add(mi);
                mAvailable.add(mi.isAvailable());

            }
        }

        //Initialize RecyclerView for MenuItemRecyclerViewAdapter and display
        initRecyclerView();
    }

    /**
     * Used to initialize recycler view.
     */
    private void initRecyclerView(){
        //Instantiate adapter and display list of menu items, ingredients, and availability.
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.itemrecyclerv_view);
        MenuItemRecyclerViewAdapter adapter = new MenuItemRecyclerViewAdapter(mItems,mAvailable,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
