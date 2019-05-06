package com.example.foodfriendly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    private final String TAG = "In DisplayActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> mRestaurantNames=new ArrayList();
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<String> mMenuItemNames=new ArrayList();
    private ArrayList<Boolean> mAvailable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

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
        Log.d(TAG, restaurants.get(0).getRestaurant_name());
        ArrayList<String> itemNames = new ArrayList<String>();
        for (Restaurant r: restaurants) {
            for( Menu_Item mi : r.getMenu()) {
                if(mi != null) {
                    mRestaurantNames.add(r.getRestaurant_name());
                    mMenuItemNames.add(mi.getItem_name());
                    mAvailable.add(mi.isAvailable());
                }

            }
        }

        initRecyclerView();


    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mRestaurantNames, mMenuItemNames, mAvailable,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
