///////////////////////////////////////////////////////////////////////////////
// Application:        Food Friendly
// Class:              RestaurantRecyclerViewAdapter - RecyclerViewAdapter for listing restaurants
// Course:             Computer Science (Apps for Good - D Term)
//
// Authors:            Alan Chen, Esther Ng, Andrew Youssef
///////////////////////////////////////////////////////////////////////////////
package com.example.foodfriendly;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>{

    //Log Tag - RestaurantRecyclerViewAdapter (Full name too long)
    private static final String TAG = "in RRecyclerViewAdapter";

    //RecyclerViewAdapter MUST receive a Context
    private Context mContext;

    //List of restaurants
    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    //Constructor
    public RestaurantRecyclerViewAdapter( ArrayList<Restaurant> restaurants, Context context){
        mRestaurants = restaurants;
        mContext = context;
    }

    //Creates a ViewHolder based on layout_listrestaurant, which is for listing various restaurants.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listrestaurant, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Displays the name of the restaurant
        Log.d(TAG, "onBindViewHolder: called.");
        holder.restaurantName.setText(mRestaurants.get(position).getRestaurant_name());

        //Position in list for iterating through the parameter lists
        final int pos = position;

        //Opens up a menu for the restaurant that is clicked
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: " + mRestaurants.get(pos).getRestaurant_name());

                //Passes current restaurant and its menu to MenuDisplayActivity
                Intent intent = new Intent(view.getContext(), MenuDisplayActivity.class);
                intent.putExtra("restaurantName",mRestaurants.get(pos).getRestaurant_name());
                intent.putParcelableArrayListExtra("restaurantItems", mRestaurants.get(pos).getMenu());
                view.getContext().startActivity(intent);
            }
        });
    }

    //Gets number of restaurants
    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    //Links parts of the ViewHolder to proper data
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView restaurantName;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.item_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


}
