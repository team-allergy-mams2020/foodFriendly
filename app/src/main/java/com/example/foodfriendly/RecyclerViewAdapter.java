package com.example.foodfriendly;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mRestaurantNames;
    private ArrayList<String> mMenuItemNames;
    private ArrayList<Boolean> mAvailable;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> restaurantNames, ArrayList<String> itemNames, ArrayList<Boolean> available, Context context){
        mRestaurantNames = restaurantNames;
        mMenuItemNames = itemNames;
        mAvailable = available;

        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.restaurantName.setText(mRestaurantNames.get(position));
        holder.itemName.setText(mMenuItemNames.get(position));
        if(mAvailable.get(position) == true) {
            holder.available.setText("Edible!");
        } else {
            holder.available.setText("Not edible");
        }

        final int pos = position;

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: "+mRestaurantNames.get(pos));

                Toast.makeText(mContext, mRestaurantNames.get(pos), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRestaurantNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView restaurantName, itemName, available;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            restaurantName=itemView.findViewById(R.id.restaurant_name);
            itemName = itemView.findViewById(R.id.item_name);
            available = itemView.findViewById(R.id.availability);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
