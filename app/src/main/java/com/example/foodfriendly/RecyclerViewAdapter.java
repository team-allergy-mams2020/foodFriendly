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

    private ArrayList<String> mRestaurantNames=new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> RestaurantNames, Context context){
        mRestaurantNames = RestaurantNames;
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

        TextView restaurantName;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            restaurantName=itemView.findViewById(R.id.restaurant_name);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
