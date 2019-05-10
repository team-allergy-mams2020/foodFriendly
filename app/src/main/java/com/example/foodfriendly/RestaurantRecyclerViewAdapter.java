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

    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    public RestaurantRecyclerViewAdapter( ArrayList<Restaurant> restaurants, Context context){
        mRestaurants = restaurants;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listrestaurant, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.restaurantName.setText(mRestaurants.get(position).getRestaurant_name());

        final int pos = position;

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: " + mRestaurants.get(pos).getRestaurant_name());


                Intent intent = new Intent(view.getContext(), MenuDisplayActivity.class);
                intent.putExtra("restaurantName",mRestaurants.get(pos).getRestaurant_name());
                intent.putParcelableArrayListExtra("restaurantItems", mRestaurants.get(pos).getMenu());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

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
