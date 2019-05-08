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

    private final String TAG = "in MenuDisplayActivity";

    private String restaurantName;
    private ArrayList<Menu_Item> menuItems;
    private ArrayList<Menu_Item> mItems = new ArrayList<>();
    private ArrayList<Boolean> mAvailable = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudisplay);

        Intent intent = getIntent();
        restaurantName = intent.getStringExtra("restaurantName");
        Bundle bundle = intent.getExtras();
        menuItems = bundle.getParcelableArrayList("restaurantItems");


        TextView restaurant_name = findViewById(R.id.item_name);
        restaurant_name.setText(restaurantName);

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

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.itemrecyclerv_view);
        MenuItemRecyclerViewAdapter adapter = new MenuItemRecyclerViewAdapter(mItems,mAvailable,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
