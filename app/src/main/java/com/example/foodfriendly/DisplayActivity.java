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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mRestaurantNames.add("you e");
        mRestaurantNames.add("you e");
        mRestaurantNames.add("you e");
        mRestaurantNames.add("you e");
        mRestaurantNames.add("you f");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<String> restaurants = bundle.getStringArrayList("restaurants");
        Log.d(TAG, restaurants.toString());

        initRecyclerView();


    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mRestaurantNames, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
