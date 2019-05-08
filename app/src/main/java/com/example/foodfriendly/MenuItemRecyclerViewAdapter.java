package com.example.foodfriendly;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuItemRecyclerViewAdapter extends RecyclerView.Adapter<MenuItemRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<String> mItemNames = new ArrayList<>();
    private ArrayList<Boolean> mAvailable = new ArrayList<>();

    public MenuItemRecyclerViewAdapter(ArrayList<String> menu_items, ArrayList<Boolean> available, Context context){
        mItemNames = menu_items;
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
        holder.itemName.setText(mItemNames.get(position));

        if(mAvailable.get(position)) {
            holder.itemAvailability.setText("Edible!");
            holder.itemAvailability.setTextColor(Color.rgb(0,255,0));

        } else {
           holder.itemAvailability.setText("Not edible");
           holder.itemAvailability.setTextColor(Color.rgb(255,0,0));

        }

        final int pos = position;

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: "+mItemNames.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName, itemAvailability;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.item_name);
            itemAvailability=itemView.findViewById(R.id.availability);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }


}
